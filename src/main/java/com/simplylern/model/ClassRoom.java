package com.simplylern.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "class_room")
public class ClassRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column
	private String name;

	
	@ManyToMany(mappedBy = "classRooms",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Teacher> teachers;
	
	@OneToMany(cascade = CascadeType.ALL)  
	@JoinColumn(name="classId")   
	private List<Student> students;  


	public ClassRoom() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClassRoom(String name) {
		super();
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	

	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public Set<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	@Override
	public String toString() {
		return "ClassRoom [id=" + id + ", name=" + name + "]";
	}
	
	

	



}
