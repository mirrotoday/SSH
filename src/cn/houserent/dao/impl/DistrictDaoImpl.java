package cn.houserent.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.houserent.dao.DistrictDao;
import cn.houserent.entity.District;

public class DistrictDaoImpl extends HibernateDaoSupport implements DistrictDao {

	public void save(District district) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(district);
	}

	public void delete(District district) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(district);
	}

	public District findById(Integer id) {
		return this.getHibernateTemplate().get(District.class, id);
	}

	public List<District> findByExample(District instance) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().findByExample(instance);
	}

	public List<District> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(" from District d where d."+propertyName+"=?",value);
	}

	public List<District> findAll() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(" from District ");
	}

}
