package cn.houserent.dao.hibimpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import cn.houserent.dao.BaseHibernateDAO;
import cn.houserent.dao.StreetDao;
import cn.houserent.entity.Street;

/**
 * A data access object (DAO) providing persistence and search support for
 * Street entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.houserent.entity.Street
 * 
 */

public class StreetDaoHibImpl extends BaseHibernateDAO implements StreetDao {
	private static final Log log = LogFactory.getLog(StreetDaoHibImpl.class);

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.StreetDao#save(cn.houserent.entity.Street)
	 */
	public void save(Street street) {
		log.debug("saving Street instance");
		Transaction tx = getSession().beginTransaction();
		try {
			getSession().save(street);
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
	 * @see cn.jbit.houserent.dao.StreetDao#delete(cn.houserent.entity.Street)
	 */
	public void delete(Street street) {
		log.debug("deleting Street instance");
		Transaction tx = getSession().beginTransaction();
		try {
			getSession().delete(street);
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
	 * @see cn.jbit.houserent.dao.StreetDao#findById(java.lang.Integer)
	 */
	public Street findById(java.lang.Integer id) {
		log.debug("getting Street instance with id: " + id);
		try {
			Street instance = (Street) getSession().get(
					"cn.houserent.entity.Street", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally{
			closeSession();
		}
	}

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.StreetDao#findByExample(cn.houserent.entity.Street)
	 */
	@SuppressWarnings("unchecked")
	public List<Street> findByExample(Street instance) {
		log.debug("finding Street instance by example");
		try {
			List<Street> results = getSession().createCriteria(
					"cn.houserent.entity.Street").add(Example.create(instance))
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
	 * @see cn.jbit.houserent.dao.StreetDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<Street> findByProperty(String propertyName, Object value) {
		log.debug("finding Street instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Street as model where model."
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
	 * @see cn.jbit.houserent.dao.StreetDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Street> findAll() {
		log.debug("finding all Street instances");
		try {
			String queryString = "from Street";
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