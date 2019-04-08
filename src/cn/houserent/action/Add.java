package cn.houserent.action;

import java.util.Date;
import java.util.Map;

import cn.houserent.entity.House;
import cn.houserent.entity.Street;
import cn.houserent.entity.Type;
import cn.houserent.entity.User;
import cn.houserent.service.HouseBiz;
import cn.houserent.service.StreetBiz;
import cn.houserent.service.TypeBiz;
import cn.houserent.service.impl.HouseBizImpl;
import cn.houserent.service.impl.StreetBizImpl;
import cn.houserent.service.impl.TypeBizImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Add extends ActionSupport{

	private String message;
	private String price;
	private String street_id;
	private String type_id;
	private String floorage;
	private String title;	
	private String contact;
	private Date houseDate;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception{
		ActionContext ac = ActionContext.getContext();
		Map session = ac.getSession();
		User user = (User)session.get("login");
		TypeBiz tb = new TypeBizImpl();
		Type type = tb.getTypeById(Integer.parseInt(type_id.trim()));
		StreetBiz sb = new StreetBizImpl();
		Street street = sb.getStreetById(Integer.parseInt(street_id.trim()));
		double p = 0;
		try {
			p = Double.parseDouble(price);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int f = 0;
		try {
			f = Integer.parseInt(floorage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//封装House数据
		House house = new House();
		house.setUser(user);
		house.setType(type);
		house.setStreet(street);
		house.setTitle(title);
		house.setDescription(" ");
		house.setPrice(p);
		house.setDate(new Date());
		house.setFloorage(f);
		house.setContact(contact);
		house.setDate(houseDate);
		//发布房屋信息
		HouseBiz hb = new HouseBizImpl();
		hb.save(house);
		return SUCCESS;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStreet_id() {
		return street_id;
	}

	public void setStreet_id(String streetId) {
		street_id = streetId;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String typeId) {
		type_id = typeId;
	}

	public String getFloorage() {
		return floorage;
	}

	public void setFloorage(String floorage) {
		this.floorage = floorage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getHouseDate() {
		return houseDate;
	}

	public void setHouseDate(Date houseDate) {
		this.houseDate = houseDate;
	}
	
}
