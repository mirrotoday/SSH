package cn.houserent.dao.hibimpl;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import cn.houserent.dao.BaseHibernateDAO;
import cn.houserent.dao.DistrictDao;
import cn.houserent.entity.District;



public class DistrictDaoHibImpl extends BaseHibernateDAO implements DistrictDao {
	private static final Log log = LogFactory.getLog(DistrictDaoHibImpl.class);

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.DistrictDao#save(cn.houserent.entity.District)
	 */
	public void save(District district) {
		log.debug("saving District instance");
		Transaction tx = getSession().beginTransaction();
		try {
			
			getSession().save(district);
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
	 * @see cn.jbit.houserent.dao.DistrictDao#delete(cn.houserent.entity.District)
	 */
	public void delete(District district) {
		log.debug("deleting District instance");
		Transaction tx = getSession().beginTransaction();
		try {
			getSession().delete(district);
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
	 * @see cn.jbit.houserent.dao.DistrictDao#findById(java.lang.Integer)
	 */
	public District findById(java.lang.Integer id) {
		log.debug("getting District instance with id: " + id);
		try {
			District instance = (District) getSession().get(
					"cn.houserent.entity.District", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.DistrictDao#findByExample(cn.houserent.entity.District)
	 */
	@SuppressWarnings("unchecked")
	public List<District> findByExample(District instance) {
		log.debug("finding District instance by example");
		try {
			List<District> results = getSession().createCriteria(
					"cn.houserent.entity.District")
					.add(Example.create(instance)).list();
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
	 * @see cn.jbit.houserent.dao.DistrictDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<District> findByProperty(String propertyName, Object value) {
		log.debug("finding District instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from District as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			List<District> result = queryObject.list();
			return result;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see cn.jbit.houserent.dao.DistrictDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<District> findAll() {
		log.debug("finding all District instances");
		try {
			String queryString = "from District";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}