package io.buchin.controllers.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import io.buchin.controllers.servlets.LoginServlet;
import io.buchin.exception.UserDAOException;
import io.buchin.services.StudentDAOService;
import io.buchin.services.UserService;

/**
 * Created by fedinskiy on 06.03.17.
 */
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentDAOService studentDAOService;
	
	private static Logger logger = Logger.getLogger(LoginServlet.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(){
		
//		final Student studentById = studentDAOService.getStudentById(1);
//		logger.info(studentById.getName());
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String authorization(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password){
		try {
			if (userService.authorize(login, password)) {
				logger.trace("authorized");
				return ControllerUtils.redirectTo("list");
			} else {
				logger.trace("not authorized");
				return "login";
			}
		} catch (UserDAOException e) {
			logger.error(e);
			return "error";
		}
	}
	
}
