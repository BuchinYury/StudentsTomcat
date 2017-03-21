package io.buchin.interfaces;

import io.buchin.entities.StudentsEntity;

import java.util.List;

/**
 * Created by fedinskiy on 21.03.17.
 */
public interface StudentService {
	List<StudentsEntity> findAll();
	List<StudentsEntity> findByname(String name);
}
