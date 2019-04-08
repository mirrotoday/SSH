package cn.houserent.dao;
import org.hibernate.Session;

import cn.houserent.util.HibernateUtil;

/**
 * Data access object (DAO) for domain model
 * 
 */
public class BaseHibernateDAO {	
	public Session getSession() {
		return HibernateUtil.getSession();
	}	
	public void closeSession(){
		HibernateUtil.closeSession();
	}	
}