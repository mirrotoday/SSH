package cn.houserent.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.houserent.dao.StreetDao;
import cn.houserent.entity.Street;

public class StreetDaoImpl extends HibernateDaoSupport implements StreetDao {

	public void save(Street street) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(street);
	}

	public void delete(Street street) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(street);
	}

	public Street findById(Integer id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Street.class,id);
	}

	public List<Street> findByExample(Street instance) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().findByExample(instance);
	}

	public List<Street> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from Street s where s."+propertyName+"=?",value);
	}

	public List<Street> findAll() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from Street ");
	}

}
