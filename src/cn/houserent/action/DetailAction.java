package cn.houserent.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;

import cn.houserent.entity.House;
import cn.houserent.service.HouseBiz;
import cn.houserent.service.impl.HouseBizImpl;

public class DetailAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private House house;

	
	public House getHouse() {
		return house;
	}


	public void setHouse(House house) {
		this.house = house;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public String execute() throws Exception {
		HouseBiz hb = new HouseBizImpl();
		house=new House();
		house = hb.getHouseById(id);
		//System.out.println("====title:" + house.getTitle());
		//request.setAttribute("house", house);
		return SUCCESS;
	}

}
