package com.epam.model;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "customer")
@org.codehaus.jackson.annotate.JsonIgnoreProperties(ignoreUnknown = true)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
@org.codehaus.jackson.annotate.JsonTypeName("")
public class Customer {

	@XmlAttribute(name = "custId", required = true)
	@org.codehaus.jackson.annotate.JsonProperty("custId")
	@com.fasterxml.jackson.annotation.JsonProperty("custId")
	private int custId;
	
	@XmlElement(required = true, name = "email")
	@org.codehaus.jackson.annotate.JsonProperty("email")
	@com.fasterxml.jackson.annotation.JsonProperty("email")
	@NotEmpty
	@Email(message = "{contact.wrong.email}", regexp = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
	@FormParam("email")
	private String email;

	@XmlTransient
	@org.codehaus.jackson.annotate.JsonProperty("date")
	@com.fasterxml.jackson.annotation.JsonProperty("date")
	public Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@XmlElement(required = true, name = "name")
	@org.codehaus.jackson.annotate.JsonProperty("name")
	@com.fasterxml.jackson.annotation.JsonProperty("name")
	@Size(min = 2, max = 30)
	@FormParam("name")
	private String name;

	@XmlTransient
	@org.codehaus.jackson.annotate.JsonProperty("avaliable")
	@com.fasterxml.jackson.annotation.JsonProperty("avaliable")
	private boolean avaliable = false;

	
	@XmlElement(required = true, name = "age")
	@org.codehaus.jackson.annotate.JsonProperty("age")
	@com.fasterxml.jackson.annotation.JsonProperty("age")
	@NotNull
	@Min(18)
	@Max(100)
	@FormParam("age")
	private int age;

	@XmlElement(required = true, name = "password")
	@org.codehaus.jackson.annotate.JsonProperty("password")
	@com.fasterxml.jackson.annotation.JsonProperty("password")
	@NotEmpty
	@Size(min = 6)
	@FormParam("password")
	private String password;

	@XmlElement(required = false, name = "facebookId")
	@org.codehaus.jackson.annotate.JsonProperty("facebookId")
	@com.fasterxml.jackson.annotation.JsonProperty("facebookId")
	private String facebookId;

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
			String password, String facebookId) {
		this.custId = custId;
		this.name = name;
		this.age = age;
		this.email = email;
		this.password = password;
		this.facebookId = facebookId;
	}

	public Customer() {
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", email=" + email + ", name="
				+ name + ", avaliable=" + avaliable + ", age=" + age
				+ ", password=" + password + ", facebookId=" + facebookId + "]";
	}

	/**
	 * @return the facebookId
	 */
	public String getFacebookId() {
		return facebookId;
	}

	/**
	 * @param facebookId
	 *            the facebookId to set
	 */
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	/**
	 * @return the avaliable
	 */
	public boolean isAvaliable() {
		return avaliable;
	}

	/**
	 * @param avaliable
	 *            the avaliable to set
	 */
	public void setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
	}

}
