package models.dao;

import common.exceptions.UserDaoException;
import models.connector.ConnectionPool;
import models.connector.DBConst;
import models.pojo.Student;
import org.apache.log4j.Logger;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuri on 25.02.17.
 */
public class StudentDao {
    private static Logger logger = Logger.getLogger(StudentDao.class);

    private static ConnectionPool connectionPool;

    private static String SQL_ALL_STUDENTS = "SELECT * FROM students";

    private static String SQL_STUDENTS_GROUP = "SELECT * FROM students WHERE id_group = ?";

    private static String SQL_FIND_STUDENT = "SELECT * FROM students WHERE id = ?";

    private static String SQL_DELETE_STUDENT = "DELETE FROM students WHERE id = ?";

    private static String SQL_UPDATE_STUDENT = "UPDATE students " +
                                                "SET id = ?, name = ?, email = ?, group_id = ? " +
                                                "WHERE id = ?";

    private static String SQL_INSERT_STUDENT = "INSERT INTO students (name, email, group_id) " +
                                                "VALUES (?, ?, ?)";

    public static List<Student> getAllStudents() {
        logger.trace("getAllStudents");

        List<Student> studentsList = new ArrayList<>();
        connectionPool = new ConnectionPool(DBConst.URL, DBConst.USERNAME, DBConst.PASS, DBConst.DRIVER);
        try (Connection conn = connectionPool.retrieve();
             Statement statement = conn.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_ALL_STUDENTS);

            while (resultSet.next()) {
                logger.debug(resultSet.getString("name"));

                Student student = new Student();

                student.setId(resultSet.getInt("id_student"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setGroup_id(resultSet.getInt("group_id"));

                studentsList.add(student);
            }

            connectionPool.putback(conn);
        } catch (SQLException e) {
            logger.error(e);
        }

        System.out.println(studentsList);
        return studentsList;
    }

    public static int deleteStudent(int id) {
        int count = 0;

        connectionPool = new ConnectionPool(DBConst.URL, DBConst.USERNAME, DBConst.PASS, DBConst.DRIVER);
        try (Connection conn = connectionPool.retrieve();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE_STUDENT)) {

            preparedStatement.setInt(1, id);
            count = preparedStatement.executeUpdate();

            connectionPool.putback(conn);
            logger.debug(id + " student was deleted");
        } catch (SQLException e) {
            logger.error(e);
        }
        return count;
    }

    public static int updateStudent(Student student) {
        int count = 0;

        connectionPool = new ConnectionPool(DBConst.URL, DBConst.USERNAME, DBConst.PASS, DBConst.DRIVER);
        try (Connection conn = connectionPool.retrieve();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE_STUDENT)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getGroup_id());

            count = preparedStatement.executeUpdate();

            connectionPool.putback(conn);
            logger.debug(student.getId() + " student was update" + student.getGroup_id());
        } catch (SQLException e) {
            logger.error(e);
        }
        return count;
    }

    public static int insertStudent(Student student) {
        int count = 0;

        connectionPool = new ConnectionPool(DBConst.URL, DBConst.USERNAME, DBConst.PASS, DBConst.DRIVER);
        try (Connection conn = connectionPool.retrieve();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_STUDENT)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getGroup_id());

            count = preparedStatement.executeUpdate();

            connectionPool.putback(conn);
            logger.debug(student.getId() + " student was insert" + student.getGroup_id());
        } catch (SQLException e) {
            logger.error(e);
        }
        return count;
    }

    public static Student getStudentById(int id) throws UserDaoException {

        logger.debug(id);
        Student student = null;

        connectionPool = new ConnectionPool(DBConst.URL, DBConst.USERNAME, DBConst.PASS, DBConst.DRIVER);
        try (Connection conn = connectionPool.retrieve();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_STUDENT)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                logger.debug("find" + id);

                student = new Student();

                student.setId(resultSet.getInt("id_student"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setGroup_id(resultSet.getInt("group_id"));

            } else {
                logger.debug(id + " not found");
            }

            connectionPool.putback(conn);
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return student;
    }

    public static List<Student> getStudentsByGroup(int groupid) {
        List<Student> studentsList = new ArrayList<>();

        connectionPool = new ConnectionPool(DBConst.URL, DBConst.USERNAME, DBConst.PASS, DBConst.DRIVER);
        try (Connection connection = connectionPool.retrieve();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_STUDENTS_GROUP)) {

            preparedStatement.setInt(1, groupid);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                logger.debug(resultSet.getString("name"));

                Student student = new Student();

                student.setId(resultSet.getInt("id_student"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setGroup_id(resultSet.getInt("group_id"));

                studentsList.add(student);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return studentsList;
    }
}
