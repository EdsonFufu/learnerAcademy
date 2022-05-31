package com.simplylern.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class StudentList {

	private String studentId;

	private String studentName;
	
	private String className;
	
	

	public StudentList() {
		super();
		// TODO Auto-generated constructor stub
	}



	public StudentList(String studentId, String studentName, String className) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.className = className;
	}



	public String getStudentId() {
		return studentId;
	}



	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}



	public String getStudentName() {
		return studentName;
	}



	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}



	public String getClassName() {
		return className;
	}



	public void setClassName(String className) {
		this.className = className;
	}



	@Override
	public String toString() {
		return "StudentList [studentId=" + studentId + ", studentName=" + studentName + ", className=" + className
				+ "]";
	}
	

	

}
