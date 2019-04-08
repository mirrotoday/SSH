package service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.houserent.entity.House;
import cn.houserent.service.HouseBiz;

public class UserBizTest {

	@Test
	public void test() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		HouseBiz houseBiz=(HouseBiz) ac.getBean("houseBiz");
		/*List<House> list=houseBiz.getAllHouse();
		for(House h:list)
			System.out.println(h.getTitle());*/
		
		//House h=houseBiz.getHouseById(41);
		
		/*
		List<House> list=houseBiz.getHouseByProperties("大", null, null, null, null, null);
		for(House h:list)
			System.out.println(h.getTitle());*/
		
	}

}
