package models.connector;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by yuri on 24.02.17.
 */
public class ConnectionPool {
    private static Logger logger = Logger.getLogger(ConnectionPool.class);

    private Vector<Connection> availableConns = new Vector<>();
    private Vector<Connection> usedConns = new Vector<>();
    private String url;
    private String username;
    private String pass;

    public ConnectionPool(String url, String username, String pass, String driver) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("В системе отсутствует MySQL JDBC Driver");
        }

        this.url = url;
        this.username = username;
        this.pass = pass;
    }

    private Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, pass);

        return conn;
    }

    public synchronized Connection retrieve() throws SQLException {
        Connection newConn;
        if (availableConns.size() == 0) {
            newConn = getConnection();
        } else {
            newConn = availableConns.lastElement();
            availableConns.removeElement(newConn);
        }
        usedConns.addElement(newConn);
        return newConn;
    }

    public synchronized void putback(Connection c) throws NullPointerException {
        if (c != null) {
            if (usedConns.removeElement(c)) {
                availableConns.addElement(c);
            } else {
                throw new NullPointerException("Connection not in the usedConns array");
            }
        }
    }

    public int getAvailableConnsCnt() {
        return availableConns.size();
    }
}

