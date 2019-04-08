package cn.houserent.util;

import java.util.Date;

public class QueryProperties {
	private String title;
	private Double high_price;
	private Double low_price;

	private Date start_date;
	private Date end_date;
	private String type_id;
	private String street_id;
	private Integer small_floorage;
	private Integer big_floorage;
	/*private String title;
	private String high_price;
	private String low_price;
	private String type_id;
	private String street_id;
		private Integer type_id;
	private Integer street_id;
	private String start_date;
	private String end_date;
	private String small_floorage;
	private String big_floorage;*/
	public Double getHigh_price() {
		return high_price;
	}
	public void setHigh_price(Double high_price) {
		this.high_price = high_price;
	}
	public Double getLow_price() {
		return low_price;
	}
	public void setLow_price(Double low_price) {
		this.low_price = low_price;
	}

	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getStreet_id() {
		return street_id;
	}
	public void setStreet_id(String street_id) {
		this.street_id = street_id;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Integer getSmall_floorage() {
		return small_floorage;
	}
	public void setSmall_floorage(Integer small_floorage) {
		this.small_floorage = small_floorage;
	}
	public Integer getBig_floorage() {
		return big_floorage;
	}
	public void setBig_floorage(Integer big_floorage) {
		this.big_floorage = big_floorage;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
