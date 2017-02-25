package controllers.servlet;

import common.exceptions.UserDaoException;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yuri on 24.02.17.
 */
public class RegistrationServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("RegistrationServlet GET");
        req.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("RegistrationServlet POST");

        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");

        try {
            if (UserService.registration(login, pass, email)) {
                logger.trace("true");
                resp.sendRedirect("/login");
            } else {
                logger.trace("false");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
            }
        } catch (UserDaoException e) {
            logger.error(e);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
