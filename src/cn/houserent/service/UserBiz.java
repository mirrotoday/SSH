package cn.houserent.service;

import cn.houserent.entity.User;

public interface UserBiz {
	/**
	 * 登录
	 * @param name	用户名
	 * @param password 密码
	 * @return 登录成功返回登录成功的用户信息,登录失败返回null
	 */
	public User login(String name, String password);

	/**
	 * 用户注册
	 * @param user 用户
	 * @return 返回是否注册成功
	 */
	public boolean register(User user);

	/**
	 * 验证是否重名
	 * @param name 输入用户名
	 * @return 返回是否重名
	 */
	public boolean validate(String name);

	/**
	 * 按编号查询用户
	 * @param id 用户编号
	 * @return 返回用户信息
	 */
	public User getUserById(Integer id);

}