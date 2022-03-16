package com.simplylern.service;

import java.util.List;

import com.simplylern.model.Teacher;

public interface TeacherService {
	Teacher add(Teacher cRoom);
	Teacher getById(int id);
	List<Teacher> getAll();
	void delete(int id);
}
