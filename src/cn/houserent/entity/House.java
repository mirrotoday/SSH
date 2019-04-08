package cn.houserent.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * House entity.
 * 示例19——Hibernate注解
 * 
 */
@Entity
@Table(name="`HOUSE`")
public class House implements java.io.Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_house")
	@SequenceGenerator(name = "seq_house", sequenceName = "SEQ_ID")
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`USER_ID`")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`TYPE_ID`")
	private Type type;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`STREET_ID`")
	private Street street;
	@Column(name = "`TITLE`")
	private String title;
	@Column(name = "`DESCRIPTION`")
	private String description;
	@Column(name = "`PRICE`")
	private Double price;
	@Column(name = "`PUBDATE`")
	private Date date;
	@Column(name = "`FLOORAGE`")
	private Integer floorage;
	@Column(name = "`CONTACT`")
	private String contact;

	// Constructors

	/** default constructor */
	public House() {
	}

	/** minimal constructor */
	public House(User user, Type type, Street street, String title, Double price) {
		this.user = user;
		this.type = type;
		this.street = street;
		this.title = title;
		this.price = price;
	}

	/** full constructor */
	public House(User user, Type type, Street street, String title,
			String description, Double price, Date date, Integer floorage,
			String contact) {
		this.user = user;
		this.type = type;
		this.street = street;
		this.title = title;
		this.description = description;
		this.price = price;
		this.date = date;
		this.floorage = floorage;
		this.contact = contact;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Street getStreet() {
		return this.street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getFloorage() {
		return this.floorage;
	}

	public void setFloorage(Integer floorage) {
		this.floorage = floorage;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}