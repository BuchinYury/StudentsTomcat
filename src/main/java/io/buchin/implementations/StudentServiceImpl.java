package io.buchin.implementations;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.buchin.entities.StudentsEntity;
import io.buchin.interfaces.StudentService;
import io.buchin.repository.StudentsRepository;

import java.util.List;

/**
 * Created by fedinskiy on 21.03.17.
 */

//@Service("jpaStudentService")
@Repository
@Transactional
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentsRepository repository;
	
	@Override
	public List<StudentsEntity> findAll() {
		return Lists.newArrayList(repository.findAll());
	}
	
	@Override
	public List<StudentsEntity> findByname(String name) {
		return repository.findByname(name);
	}
}
