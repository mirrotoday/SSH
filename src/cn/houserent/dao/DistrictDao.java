package cn.houserent.dao;

import java.util.List;

import cn.houserent.entity.District;

public interface DistrictDao {

	public void save(District district);

	public void delete(District district);

	public District findById(java.lang.Integer id);

	public List<District> findByExample(District instance);

	public List<District> findByProperty(String propertyName, Object value);

	public List<District> findAll();

}