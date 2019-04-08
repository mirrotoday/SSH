package cn.houserent.service.impl;

import java.util.Iterator;
import java.util.List;

import cn.houserent.dao.UserDao;
import cn.houserent.dao.hibimpl.UserDaoHibImpl;
import cn.houserent.entity.User;
import cn.houserent.service.UserBiz;

/**
 * User 业务逻辑类实现
 */
public class UserBizImpl implements UserBiz {
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/* (non-Javadoc)
	 * @see cn.houserent.service.impl.UserBiz#login(java.lang.String, java.lang.String)
	 */
	public User login(String name,String password){
		User user = null;
		try {

			List<User> users = userDao.findUserByProperty("name", name);
			Iterator<User> it = users.iterator();
			if(it.hasNext()){
				user = (User)it.next();
				if(password.equals(user.getPassword())) return user;
			}
			return null;
		} catch (RuntimeException e) {			
			e.printStackTrace();
			return null;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see cn.houserent.service.impl.UserBiz#register(cn.houserent.entity.User)
	 */
	public boolean register(User user){	
		try {
			userDao.save(user);
			return true;
		} catch (RuntimeException e) {
			
			e.printStackTrace();
			return false;
		}			
	}
	
	/* (non-Javadoc)
	 * @see cn.houserent.service.impl.UserBiz#validate(java.lang.String)
	 */
	public boolean validate(String name){
		try {
			List<User> users = userDao.findUserByProperty("name", name);
			if(users.size() > 0) return false;		
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see cn.houserent.service.impl.UserBiz#getUserById(java.lang.Integer)
	 */
	public User getUserById(Integer id){
		
		try {
			User user = userDao.findById(id);		
			return user;
		} catch (RuntimeException e) {
			return null;
		}
		
	}
}
