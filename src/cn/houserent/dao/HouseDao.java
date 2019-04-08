package cn.houserent.dao;

import java.util.List;

import cn.houserent.entity.House;
import cn.houserent.util.QueryProperties;

public interface HouseDao {

	/**
	 * @param transientInstance
	 */
	public void save(House transientInstance);

	public void update(House transientInstance);
	/**
	 * @param persistentInstance
	 */
	public void delete(House persistentInstance);

	/**
	 * @param id
	 * @return
	 */
	public House findById(java.lang.Integer id);

	/**
	 * @param instance
	 * @return
	 */
	public List<House> findByExample(House instance);

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<House> findByProperty(String propertyName, Object value);

	/**
	 * @return
	 */
	public List<House> findAll();

	public List<House> findHouseByProperties(QueryProperties qp);

	public List<House> query(int first, int size);

}