package models.connector;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by yuri on 24.02.17.
 */
public class Connector {
    private static final Logger logger = Logger.getLogger(Connector.class);

    private static Connection con;

    public static synchronized Connection getConnection(String url, String username, String password) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (con != null) return con;
            else con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            logger.error("В системе отсутствует MySQL JDBC Driver");
        }

        return con;
    }
}
