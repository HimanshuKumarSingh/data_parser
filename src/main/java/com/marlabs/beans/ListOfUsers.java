package com.marlabs.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="ListOfUsers")
public class ListOfUsers implements Serializable {

	private static final long serialVersionUID = 1L;

	// ------LIST TYPE DATA MEMBER TO STORE ALL THE USERS-----------------
	List<User> user = new ArrayList<User>();

	// ------CONSTRUCTORS------------------------------------------------------------------------------
	public ListOfUsers() {
		
	}

	public ListOfUsers(List<User> users) {
		super();
		this.user = users;
	}
//	-----------GETTER AND SETTER METHOD FOR THE DATA MEMBER------------------------------------

	public List<User> getUsers() {
		return user;
	}

	public void setUsers(List<User> users) {
		this.user = users;
	}
	
}
