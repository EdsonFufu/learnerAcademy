package com.simplylern.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;



@Entity
@Table(name = "teacher", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column
	private String name;


	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Teacher(String name) {
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
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	  @JoinTable(name = "CLASS_ROOM_TEACHER", joinColumns = {@JoinColumn(referencedColumnName = "id")}
	      , inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
	  private Set<ClassRoom> classRooms;
	
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + "]";
	}
	
	

	



}
