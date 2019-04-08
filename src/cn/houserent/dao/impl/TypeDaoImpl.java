package cn.houserent.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.houserent.dao.TypeDao;
import cn.houserent.entity.Type;

public class TypeDaoImpl extends HibernateDaoSupport implements TypeDao {

	public void save(Type type) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(type);
	}

	public void delete(Type type) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(type);
	}

	public Type findById(Integer id) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Type.class, id);
	}

	public List<Type> findByExample(Type instance) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().findByExample(instance);
	}

	public List<Type> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(" from Type t where t."+propertyName+"=?",value);
	}

	public List<Type> findAll() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(" from Type ");
	}

}
