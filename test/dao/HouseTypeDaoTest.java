package dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.houserent.dao.TypeDao;
import cn.houserent.entity.Type;
import cn.houserent.service.HouseBiz;

public class HouseTypeDaoTest {

	@Test
	public void test() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		TypeDao typeDao=(TypeDao) ac.getBean("typeDao");
		/*List<Type> list=(List<Type>) typeDao.findByProperty("id", 1004);
		for(Type t :list)
			System.out.println(t.getName());*/
		
		Type instance=new Type();
//		instance.setId(1001);
		instance.setName("两室一厅");
		List<Type> list=(List<Type>) typeDao.findByExample(instance);
		for(Type t :list)
			System.out.println(t.getName());
	}

}
