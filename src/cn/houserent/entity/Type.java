package cn.houserent.entity;



import java.util.HashSet;
import java.util.Set;

/**
 * Type entity.
 * 
 * 
 */

public class Type implements java.io.Serializable {

	// Fields

	private Integer id = 0;
	private String name = "";


	// Constructors

	/** default constructor */
	public Type() {
	}

	/** minimal constructor */
	public Type(String name) {
		this.name = name;
	}


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


}