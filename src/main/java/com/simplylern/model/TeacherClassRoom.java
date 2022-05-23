package com.simplylern.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teacher_class_room")
public class TeacherClassRoom  implements Serializable{

	@Id
	@Column
	private int teachers_id;
	@Id
	@Column
	private int classrooms_id;

	public TeacherClassRoom() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TeacherClassRoom(int tid, int cid) {
		super();
		this.teachers_id = tid;
		this.classrooms_id = cid;
	}
	public int getTeachers_id() {
		return teachers_id;
	}
	public void setTeachers_id(int teachers_id) {
		this.teachers_id = teachers_id;
	}
	public int getClassrooms_id() {
		return classrooms_id;
	}
	public void setClassrooms_id(int classrooms_id) {
		this.classrooms_id = classrooms_id;
	}
	@Override
	public String toString() {
		return "TeacherClassRoom [teachers_id=" + teachers_id + ", classrooms_id=" + classrooms_id + "]";
	}
	
	

	



}
