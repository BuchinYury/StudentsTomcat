package services;

import models.dao.StudentDao;
import models.pojo.Student;

import java.util.List;

/**
 * Created by yuri on 25.02.17.
 */
public class StudentService {
    public static List<Student> getAllStudents(){
        return StudentDao.getAllStudents();
    }

    public static int deleteStudentOnId(int id){

        return StudentDao.deleteStudent(id);
    }

    public static int updateStudentOnId(Student student){

        return StudentDao.updateStudent(student);
    }

    public static int insertStudent(Student student){

        return StudentDao.insertStudent(student);
    }

    public static List<Student> getStudentsByGroupId(int groupid){
        return StudentDao.getStudentsByGroup(groupid);
    }

}
