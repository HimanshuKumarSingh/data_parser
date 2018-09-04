package com.marlabs.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="user")
@XmlType(propOrder= {"userName","firstName","lastName","sex","age"})
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// -----------PRIVATE DATA MEMBERS OF USER CLASS---------------------------------
	private String id;
	private String userName;
	private String firstName;
	private String lastName;
	private String sex;
	private String age;

	// ---------------CONSTRUCTORS OF USER CLASS-----------------------------------
	public User() {
		
	}

	public User(String id, String userName, String firstName, String lastName, String sex, String age) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.age = age;
	}
//    -----------------GETTER AND SETTER METHODS FOR THE RESPECTIVE DATA MEMBERS-----

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
//	---------OVERRIDING THE TO-STRINF METHOD------------------------

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", sex=" + sex + ", age=" + age + "]";
	}
	
}
