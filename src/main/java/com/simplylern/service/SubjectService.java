package com.simplylern.service;

import java.util.List;

import com.simplylern.model.Subject;

public interface SubjectService {
	Subject add(Subject subject);
	Subject getById(int id);
	List<Subject> getAll();
	void delete(int id);
}
