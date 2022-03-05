package com.simplylern.service;

import java.util.List;

import com.simplylern.model.ClassRoom;

public interface ClassRoomService {
	ClassRoom add(ClassRoom cRoom);
	ClassRoom getById(int id);
	List<ClassRoom> getAll();
	void delete(int id);
}
