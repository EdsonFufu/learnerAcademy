package com.simplylern.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUBJECT_TEACHER")
public class TeacherSubject  implements Serializable{

	@Id
	@Column
	private int teachers_id;
	@Id
	@Column
	private int subjects_id;

	public TeacherSubject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TeacherSubject(int tid, int sid) {
		super();
		this.teachers_id = tid;
		this.subjects_id = sid;
	}
	public int getTeachers_id() {
		return teachers_id;
	}
	public void setTeachers_id(int teachers_id) {
		this.teachers_id = teachers_id;
	}
	public int getSubjects_id() {
		return subjects_id;
	}
	public void setSubjects_id(int subjects_id) {
		this.subjects_id = subjects_id;
	}
	@Override
	public String toString() {
		return "TeacherSubject [teachers_id=" + teachers_id + ", subjects_id=" + subjects_id + "]";
	}


}
