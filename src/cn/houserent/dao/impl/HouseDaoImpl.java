package cn.houserent.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;


import cn.houserent.dao.HouseDao;
import cn.houserent.entity.House;
import cn.houserent.util.QueryProperties;

@Repository("houseDao")
public class HouseDaoImpl extends HibernateDaoSupport  implements HouseDao{

	public HouseDaoImpl(){}
	@Autowired//使用注解构造注入
	public HouseDaoImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	public void save(House transientInstance) {		
		this.getHibernateTemplate().save(transientInstance);
	}

	public void update(House transientInstance) {
		this.getHibernateTemplate().update(transientInstance);	
	}

	public void delete(House persistentInstance) {
		this.getHibernateTemplate().delete(persistentInstance);
	}

	public House findById(Integer id) {
		return this.getHibernateTemplate().get(House.class, id);
	}

	public List<House> findByExample(House instance) {
		return this.getHibernateTemplate().findByExample(instance);
	}

	public List<House> findByProperty(String propertyName, Object value) {
		return this.getHibernateTemplate().find(" from House h where h."+propertyName+"=?",value);
	}

	public List<House> findAll() {		
		return this.getHibernateTemplate().find(" from House");
	}
	
	public List<House> findHouseByProperties(final QueryProperties qp) {	
		return this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer queryString = new StringBuffer();		
				queryString.append("from House where ");
				queryString.append("title like '%"+qp.getTitle()+"%'");		
				queryString.append("and price between "+qp.getLow_price()+" and "+qp.getHigh_price()+" ");		
				queryString.append("and street_id like '%"+qp.getStreet_id()+"%' ");		
				queryString.append("and type_id like '%"+qp.getType_id()+"%' ");		
				queryString.append("and floorage between "+qp.getSmall_floorage()+" and "+qp.getBig_floorage()+" ");
				System.out.println(queryString.toString());
				Query queryObject = session
						.createQuery(queryString.toString());						
				return queryObject.list();
			}
			
		});
		
		
	}

	/**
	 * 分页查询
	 */
	public List<House> query(final int first, final int size) {		
		return this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				Query query =session.createQuery("from House");
				query.setFirstResult(first);
				query.setMaxResults(size);
				return query.list();
			}});
	}

}
