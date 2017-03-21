package io.buchin.models.dao;

import io.buchin.models.pojo.Student;

/**
 * Created by fedinskiy on 17.03.17.
 */
public interface StudentsMapper {
	Student getStudentById(Integer id);
}
