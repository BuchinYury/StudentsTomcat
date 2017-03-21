package io.buchin.services;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import io.buchin.exception.UserDAOException;
import io.buchin.models.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by fedinskiy on 23.02.17.
 */
@Service
//@Scope(value="session")
public class UserService {
	private static Logger logger = Logger.getLogger(UserService.class);
	@Autowired
	private UserDAO userDAO;
	private int anInt;

	public boolean authorize(String login, String password) throws UserDAOException {
//		showNumber();
		if(userDAO.getUserByLoginAndPassword(login, password).getIdUser() != 0){
			return true;
		} else {
			return false;
		}
	}
	public boolean RegisterUser(String login, String password) throws UserDAOException {
//		showNumber();
		if(userDAO.registrationUser(login, password).getIdUser() != 0){
			return true;
		} else {
			return false;
		}
	}
}
