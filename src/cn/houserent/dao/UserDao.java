package cn.houserent.dao;

import java.util.List;

import cn.houserent.entity.User;

public interface UserDao {

	/**
	 * 添加用户
	 * @param user 用户
	 */
	public void save(User user);

	/**
	 * 删除用户
	 * @param user 用户
	 */
	public void delete(User user);
	
	/**
	 * 修改用户
	 * @param user 用户
	 */
	public void update(User user);

	/**
	 * 按ID获取用户
	 * @param id 用户编号
	 * @return 返回用户
	 */
	public User findById(java.lang.Integer id);

	/**
	 * 按条件查询用户列表
	 * @param instance 查询条件 
	 * @return 返回用户列表
	 */
	public List<User> findByExample(User instance);

	/**
	 * 按属性值查询用户列表
	 * @param propertyName 属性名
	 * @param value 属性值
	 * @return 返回用户列表
	 */
	public List<User> findUserByProperty(String propertyName, Object value);

	/**
	 * 获取全部用户列表
	 * @return 返回全部用户列表
	 */
	public List<User> findAll();

}