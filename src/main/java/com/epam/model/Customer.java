package com.epam.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Customer {

	private int custId;

	@NotEmpty
	@Email(message = "{contact.wrong.email}", regexp = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
	@FormParam("email")
	private String email;

	@Size(min = 2, max = 30)
	@FormParam("name")
	private String name;

	@NotNull
	@Min(18)
	@Max(100)
	@FormParam("age")
	private int age;

	@NotEmpty
	@Size(min = 6)
	@FormParam("password")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
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

	public Customer(int custId, String name, int age, String email,
			String password) {
		this.custId = custId;
		this.name = name;
		this.age = age;
		this.email = email;
		this.password = password;
	}

	public Customer() {
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", email=" + email + ", name="
				+ name + ", age=" + age + ", password=" + password + "]";
	}

}
