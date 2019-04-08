package cn.houserent.dao.hibimpl;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import cn.houserent.dao.BaseHibernateDAO;
import cn.houserent.dao.UserDao;
import cn.houserent.entity.User;

public class UserDaoHibImpl extends BaseHibernateDAO implements UserDao {
	private static final Log log = LogFactory.getLog(UserDaoHibImpl.class);

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.UserDao#save(cn.houserent.entity.User)
	 */
	public void save(User user) {
		log.debug("saving User instance");
		Transaction tx = getSession().beginTransaction();
		try {
			getSession().save(user);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			tx.rollback();
			log.error("save failed", re);
			throw re;
		} finally{
			closeSession();
		}
	}

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.UserDao#delete(cn.houserent.entity.User)
	 */
	public void delete(User user) {
		log.debug("deleting User instance");
		Transaction tx = getSession().beginTransaction();
		try {
			getSession().delete(user);
			tx.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		} finally{
			closeSession();
		}
	}

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.UserDao#findById(java.lang.Integer)
	 */
	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getSession().get("cn.houserent.entity.User",id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally{
			closeSession();
		}
	}

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.UserDao#findByExample(cn.houserent.entity.User)
	 */
	@SuppressWarnings("unchecked")
	public List<User> findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List<User>  results = getSession().createCriteria("cn.houserent.entity.User").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		} finally{
			closeSession();
		}
	}

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.UserDao#findUserByProperty(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUserByProperty(String propertyName, Object value) {
		log.debug("finding User instance with property: " + propertyName + ", value: " + value);
		
		try {
			String queryString = "from User as u where u." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		} finally{
			closeSession();
		}
	}

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.UserDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		} finally{
			closeSession();
		}
	}


	public void update(User user) {
		log.debug("saving User instance");
		Transaction tx = getSession().beginTransaction();
		try {
			getSession().update(user);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			tx.rollback();
			log.error("save failed", re);
			throw re;
		} finally{
			closeSession();
		}
		
	}

}