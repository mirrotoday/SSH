package cn.houserent.entity;


import java.util.HashSet;
import java.util.Set;

/**
 * Street entity.
 * 
 * 
 */

public class Street implements java.io.Serializable {

	// Fields

	private Integer id = 0;
	//private Integer district_id;
	private District district = new District();
	private String name = "";


	// Constructors

	/** default constructor */
	public Street() {
	}

	/** full constructor */


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}