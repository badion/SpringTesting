package com.epam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.epam.validator.annotation.FirstEmployeeName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "employee")
public class Employee {

	@Column(name = "id", columnDefinition = "bigint")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;

	@FirstEmployeeName(message = "Name is incorrect")
	@Column(name = "name")
	private String name;

	@Column(name = "age", columnDefinition = "bigint")
	private int age;

	public Employee() {

	}

	public Employee(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
