package cn.houserent.dao.hibimpl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;

import cn.houserent.dao.BaseHibernateDAO;
import cn.houserent.dao.HouseDao;
import cn.houserent.entity.House;
import cn.houserent.util.QueryProperties;

/**
 * @author zhiyao.xi
 * 
 */
public class HouseDaoHibImpl extends BaseHibernateDAO implements HouseDao {
	private static final Log log = LogFactory.getLog(HouseDaoHibImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.jbit.houserent.dao.HouseDao#save(cn.houserent.entity.House)
	 */
	public void save(House transientInstance) {
		log.debug("saving House instance");
		Transaction tx = null;
		try {
			tx = getSession().beginTransaction();
			getSession().save(transientInstance);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			tx.rollback();
			log.error("save failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.jbit.houserent.dao.HouseDao#delete(cn.houserent.entity.House)
	 */
	public void delete(House persistentInstance) {
		log.debug("deleting House instance");
		Transaction tx = getSession().beginTransaction();
		try {
			getSession().delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			tx.rollback();
			log.error("delete failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.jbit.houserent.dao.HouseDao#findById(java.lang.Integer)
	 */
	public House findById(java.lang.Integer id) {
		log.debug("getting House instance with id: " + id);
		try {
			House instance = (House) getSession().get(
					"cn.houserent.entity.House", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally {
			closeSession();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.jbit.houserent.dao.HouseDao#findByExample(cn.houserent.entity.House
	 * )
	 */
	@SuppressWarnings("unchecked")
	public List<House> findByExample(House instance) {
		log.debug("finding House instance by example");
		List<House> results = null;
		try {
			results = getSession().createCriteria(
					"cn.houserent.entity.House").add(
					Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			return results;
		} finally {
			closeSession();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.jbit.houserent.dao.HouseDao#findByProperty(java.lang.String,
	 * java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<House> findByProperty(String propertyName, Object value) {
		log.debug("finding House instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from House as model where model."
					+ propertyName + "like ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, "%" + value + "%");
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		} finally {
			closeSession();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.jbit.houserent.dao.HouseDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<House> findAll() {
		log.debug("finding all House instances");
		try {
			String queryString = "from House";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setMaxResults(100);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		} finally {
			closeSession();
		}
	}

	/**
	 * 获得总条数
	 * 
	 * @return
	 */
	public int totalCount() {
		List<House> total = findAll();
		if (total == null)
			return 0;
		else
			return total.size();
	}

	public List<House> query(int first, int size) {
		Configuration conf = null;
		SessionFactory sessionFactory = null;
		Session session = null;
		List<House> list = null;
		try {
			// 1.读取配置文件
			conf = new Configuration().configure();
			// 2.创建SessionFactory
			sessionFactory = conf.buildSessionFactory();
			// 3. 打开session
			session = sessionFactory.openSession();
			// 4. 加载数据操作
			Criteria criteria = session.createCriteria(House.class);
			criteria.setFirstResult(first);
			criteria.setMaxResults(size);
			list = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			// 5. 关闭session
			session.close();
			sessionFactory.close();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<House> findHouseByProperties(QueryProperties qp) {
		log.debug("finding  House by properties");
		try {
			StringBuffer queryString = new StringBuffer();
			
			queryString.append("from House where ");
			queryString.append("title like '%"+qp.getTitle()+"%'");
			//queryString.append("(title like :title) ");
			queryString.append("and price between "+qp.getLow_price()+" and "+qp.getHigh_price()+" ");
			//queryString.append("and (price between :low_price and :high_price) ");
			queryString.append("and street_id like '%"+qp.getStreet_id()+"%' ");
			//queryString.append("and (street_id like :street_id) ");
			queryString.append("and type_id like '%"+qp.getType_id()+"%' ");
			//queryString.append("and (type_id like :type_id) ");
			queryString
			.append("and floorage between "+qp.getSmall_floorage()+" and "+qp.getBig_floorage()+" ");
			//queryString.append("and (floorage between :small_floorage and :big_floorage) ");
			// queryString.append("and (date between :start_date and :end_date)");
			System.out.println(queryString.toString());
			Query queryObject = getSession()
					.createQuery(queryString.toString());
			queryObject.setProperties(qp);
			List l=queryObject.list();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find house by properties failed", re);
			throw re;
		} finally {
			closeSession();
		}

	}
	public int totalSearch(QueryProperties qp) {
		List<House> l = findHouseByProperties(qp);
		if(l == null)
			return 0;
		else
			return l.size();

	}	
	
	public List<House> findHouseByProperties(int first,int size,QueryProperties qp) {
		log.debug("finding  House by properties");
		try {
			StringBuffer queryString = new StringBuffer();
			
			queryString.append("from House where ");
			queryString.append("title like '%"+qp.getTitle()+"%'");
			//queryString.append("(title like :title) ");
			queryString.append("and price between "+qp.getLow_price()+" and "+qp.getHigh_price()+" ");
			//queryString.append("and (price between :low_price and :high_price) ");
			queryString.append("and street_id like '%"+qp.getStreet_id()+"%' ");
			//queryString.append("and (street_id like :street_id) ");
			queryString.append("and type_id like '%"+qp.getType_id()+"%' ");
			//queryString.append("and (type_id like :type_id) ");
			queryString
			.append("and floorage between "+qp.getSmall_floorage()+" and "+qp.getBig_floorage()+" ");
			//queryString.append("and (floorage between :small_floorage and :big_floorage) ");
			// queryString.append("and (date between :start_date and :end_date)");
			System.out.println(queryString.toString());
			Query queryObject = getSession()
					.createQuery(queryString.toString());
			queryObject.setProperties(qp);
			queryObject.setFirstResult(first);
			queryObject.setMaxResults(size);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find house by properties failed", re);
			throw re;
		} finally {
			closeSession();
		}

	}	
	public void update(House transientInstance) {
		log.debug("updating House instance");
		Transaction tx = getSession().beginTransaction();
		try {
			getSession().update(transientInstance);
			tx.commit();
			log.debug("update successful");
		} catch (RuntimeException re) {
			tx.rollback();
			log.error("update failed", re);
			throw re;
		} finally {
			closeSession();
		}

	}
	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.HouseDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<House> findByHQL(String hql) {
		log.debug("finding House instance with hql: " + hql);
		try {
			Query queryObject = getSession().createQuery(hql);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		} finally{
			closeSession();
		}
		
	}
}