package cn.houserent.dao;

import java.util.List;

import cn.houserent.entity.Type;

public interface TypeDao {

	public void save(Type type);

	public void delete(Type type);

	public Type findById(java.lang.Integer id);

	public List<Type> findByExample(Type instance);

	public List<Type> findByProperty(String propertyName, Object value);

	public List<Type> findAll();

}