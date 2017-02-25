package controllers.servlet;

import common.exceptions.UserDaoException;
import models.pojo.Student;
import org.apache.log4j.Logger;
import services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yuri on 25.02.17.
 */
public class ListServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("ListServlet GET");
//        req.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(req, resp);
        List<Student> studentList = StudentService.getAllStudents();
        System.out.println(studentList);
        req.setAttribute("studentList", studentList);
        req.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
