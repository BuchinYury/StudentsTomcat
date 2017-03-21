package io.buchin.controllers.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import io.buchin.controllers.servlets.LoginServlet;
import io.buchin.entities.StudentsEntity;
import io.buchin.interfaces.StudentService;
import io.buchin.models.pojo.Student;
import io.buchin.services.StudentDAOService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fedinskiy on 06.03.17.
 */
@Controller
public class StudentsController {
	private static Logger logger = Logger.getLogger(StudentsController.class);
	
	@Autowired
	private StudentDAOService studentDAOService;
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getStudentList(Model model){
		final List<StudentsEntity> all = studentService.findAll();
		List<Student> list=new ArrayList<>(all.size());
		logger.info(all.size());
		for (StudentsEntity studentsEntity : all) {
			Student student=new Student();
			student.setBirthdate(studentsEntity.getBirthdate());
			student.setName(studentsEntity.getName());
			student.setSex(studentsEntity.getSex());
			logger.info(student.toString());
			list.add(student);
		}
		
//		List<Student> list = studentDAOService.getAllStudents();
		model.addAttribute("studentList", list);
		return "list";
	}
	@RequestMapping(value = "/list", method = RequestMethod.POST,params ={"operation=delete"} )
	public String deleteStudent(Model model, @RequestParam(value = "chosen") String [] chosen){
		System.out.println(chosen[0]);
		studentDAOService.deleteStudents(chosen);
		List<Student> list = studentDAOService.getAllStudents();
		model.addAttribute("studentList", list);
		return ControllerUtils.redirectTo("list");
	}
	@RequestMapping(value = "/list", method = RequestMethod.POST,params ={"operation=add"} )
	public String addStudent(){
		return ControllerUtils.redirectTo("list");
	}
	@RequestMapping(value = "/list", method = RequestMethod.POST,params ={"operation=edit"} )
	public String editStudent(){
		return ControllerUtils.redirectTo("list");
	}
}