package services;

import common.exceptions.UserDaoException;
import models.dao.UserDAO;
import models.pojo.User;
import org.apache.log4j.Logger;

/**
 * Created by yuri on 24.02.17.
 */
public class UserService {
    static Logger logger = Logger.getLogger(UserService.class);

    public static User authorize(String login, String password) throws UserDaoException {
        User user = UserDAO.getUserByLoginAndPassword(login, password);

        return user;
    }

    public static boolean registration(String login, String password, String email) throws UserDaoException {
        return UserDAO.registrationUser(login, password, email);
    }
}
