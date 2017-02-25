package models.dao;

import common.exceptions.UserDaoException;
import models.connector.ConnectionPool;
import models.connector.Connector;
import models.connector.DBConst;
import models.pojo.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yuri on 24.02.17.
 */
public class UserDAO {
    private static Logger logger = Logger.getLogger(UserDAO.class);

    private static final String SQL_FIND_USER = "SELECT * FROM users where login=? AND password=?";
    private static final String SQL_CREATE_USER = "INSERT INTO users(login, password, role, email) VALUES(?, ?, ?, ?)";

    private static ConnectionPool connectionPool;

    public static User getUserByLoginAndPassword(String login, String password) throws UserDaoException {
        User user = new User();
        logger.trace("Connection to DB in get User method");

        connectionPool = new ConnectionPool(DBConst.URL, DBConst.USERNAME, DBConst.PASS, DBConst.DRIVER);
        try (Connection conn = connectionPool.retrieve();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_USER)){

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                logger.trace(resultSet.getString("login"));

                user.setIdUser(resultSet.getInt("id_user"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }

            connectionPool.putback(conn);

        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        logger.trace("Return user - id = " + user.getIdUser() + " login = " + user.getLogin());

        return user;
    }

    public static boolean registrationUser(String login, String password, String email) throws UserDaoException {
        logger.trace("Connection to DB in registration metod");

        connectionPool = new ConnectionPool(DBConst.URL, DBConst.USERNAME, DBConst.PASS, DBConst.DRIVER);

        try(Connection conn = connectionPool.retrieve();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE_USER)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, "user");
            preparedStatement.setString(4, email);

            int count = preparedStatement.executeUpdate();

            connectionPool.putback(conn);

            if (count > 0) {
                logger.debug("insert " + count);
                return true;
            } else {
                logger.debug(login + " " + password + " not inserted");
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }

        return false;
    }
}
