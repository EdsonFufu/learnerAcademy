package com.simplylern.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name="studentId")
	private String studentId;
	@Column
	private String name;
	
	@Column(name = "classId")
	private String classId;


	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String sid,String name) {
		super();
		this.studentId = sid;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", studentId=" + studentId + ", name=" + name + ", classId=" + classId + "]";
	}

}
