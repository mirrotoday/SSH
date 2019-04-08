package service;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.houserent.entity.District;
import cn.houserent.entity.House;
import cn.houserent.entity.Street;
import cn.houserent.service.HouseBiz;
import cn.houserent.service.StreetBiz;

public class StreetBizTest {

	@Test
	public void test() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		StreetBiz biz=(StreetBiz) ac.getBean("streetBiz");
		Street street=biz.getStreetById(1000);
		System.out.println(street.getName()+street.getDistrict().getName());
		Iterator<Street> it=street.getDistrict().getStreets().iterator();
		while(it.hasNext())
			System.out.println(it.next().getName());
		/*List<Street> list=biz.getAllStreet();
		for(Street s:list)
		{
			System.out.println(s.getName()+s.getDistrict().getName());
			
			
				
		}*/
		
	}

}
