package cn.houserent.entity;
import java.util.HashSet;
import java.util.Set;

/**
 * District entity.
 * 
 * 
 */

public class District implements java.io.Serializable {

	private static final long serialVersionUID = 5292418074218318463L;
	private Integer id;
	private String name;
	@SuppressWarnings("unchecked")
	private Set streets = new HashSet();

	// Constructors

	/** default constructor */
	public District() {
	}

	/** minimal constructor */
	public District(String name) {
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

	public Set getStreets() {
		return this.streets;
	}

	public void setStreets(Set streets) {
		this.streets = streets;
	}

}