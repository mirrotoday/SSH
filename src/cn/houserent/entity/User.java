package cn.houserent.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity.
 * 
 * 
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id = 0;
	private String name = " ";
	private String password = " ";
	private String telephone = " ";
	private String username = " ";
	private String isadmin = "0";


	// Constructors

	public String getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}
	/** default constructor */
	public User() {
	}
	public User(String telephone,String username) {
		this.telephone = telephone;
		this.username = username;
	}
	/** minimal constructor */
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}