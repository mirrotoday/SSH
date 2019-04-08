package cn.houserent.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cn.houserent.dao.UserDao;
import cn.houserent.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao{
	//添加用户方法
	public void save(User user) {
		this.getHibernateTemplate().save(user);		
	}
	//按属性值查找用户
	public List<User> findUserByProperty(String propertyName, Object value) {
		String queryString = "from User as u where u." + propertyName + "= ?";
		return this.getHibernateTemplate().find(queryString,value);		 
	}
	//按条件查询方法
	public List<User> findByExample(User instance) {		
		return this.getHibernateTemplate().findByExample(instance);
	}	
	//删除用户方法
	public void delete(User user) {		
		this.getHibernateTemplate().delete(user);
	}
	//更新用户方法
	public void update(User user) {		
		this.getHibernateTemplate().update(user);
	}
	//查询指定用户方法
	public User findById(Integer id) {		
		return this.getHibernateTemplate().get(User.class, id);
	}	
	//查询所有用户
	public List<User> findAll() {		
		return this.getHibernateTemplate().find(" from User");
	}

}
