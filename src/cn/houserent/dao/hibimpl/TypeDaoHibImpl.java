package cn.houserent.dao.hibimpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import cn.houserent.dao.BaseHibernateDAO;
import cn.houserent.dao.TypeDao;
import cn.houserent.entity.Type;

/**
 * A data access object (DAO) providing persistence and search support for Type
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.houserent.entity.Type
 * 
 */

public class TypeDaoHibImpl extends BaseHibernateDAO implements TypeDao {
	private static final Log log = LogFactory.getLog(TypeDaoHibImpl.class);

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.TypeDao#save(cn.houserent.entity.Type)
	 */
	public void save(Type type) {
		log.debug("saving Type instance");
		Transaction tx = getSession().beginTransaction();
		try {
			getSession().save(type);
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
	 * @see cn.jbit.houserent.dao.TypeDao#delete(cn.houserent.entity.Type)
	 */
	public void delete(Type type) {
		log.debug("deleting Type instance");
		Transaction tx = getSession().beginTransaction();
		try {
			getSession().delete(type);
			tx.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			tx.rollback();
			log.error("delete failed", re);
			throw re;
		} finally{
			closeSession();
		}
	}

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.TypeDao#findById(java.lang.Integer)
	 */
	public Type findById(java.lang.Integer id) {
		log.debug("getting Type instance with id: " + id);
		try {
			Type instance = (Type) getSession().get("cn.houserent.entity.Type",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally{
			closeSession();
		}
	}

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.TypeDao#findByExample(cn.houserent.entity.Type)
	 */
	@SuppressWarnings("unchecked")
	public List<Type> findByExample(Type instance) {
		log.debug("finding Type instance by example");
		try {
			List<Type> results = getSession().createCriteria(
					"cn.houserent.entity.Type").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		} finally{
			closeSession();
		}
	}

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.TypeDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<Type> findByProperty(String propertyName, Object value) {
		log.debug("finding Type instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Type as model where model."
					+ propertyName + "= ?";
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
	 * @see cn.jbit.houserent.dao.TypeDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Type> findAll() {
		log.debug("finding all Type instances");
		try {
			String queryString = "from Type";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		} finally{
			closeSession();
		}
	}


}