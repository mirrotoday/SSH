package cn.houserent.dao;

import java.util.List;

import cn.houserent.entity.Street;

public interface StreetDao {

	public void save(Street street);

	public void delete(Street street);

	public Street findById(java.lang.Integer id);

	public List<Street> findByExample(Street instance);

	public List<Street> findByProperty(String propertyName, Object value);

	public List<Street> findAll();

}