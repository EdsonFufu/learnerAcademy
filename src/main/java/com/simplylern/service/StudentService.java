package com.simplylern.service;

import java.util.List;

import com.simplylern.model.Student;

public interface StudentService {
	Student add(Student cRoom);
	Student getById(int id);
	List<Student> getAll();
	void delete(int id);
}
