package cn.houserent.service;
import java.util.List;

import cn.houserent.entity.House;
import cn.houserent.entity.User;

public interface HouseBiz {

	public boolean save(House house);

	public boolean update(House house);
	
	public List<House> getHouseByUser(User user);
	/**
	 * @return List 
	 * 返回所有租房信息
	 */
	public List<House> getAllHouse();

	/**
	 * @param title
	 * 
	 * @return List
	 * 返回查询结果
	 */
	public List<House> getHouseByTitle(String title);
	
	public House getHouseById(Integer id);

	public List<House> getHouseByProperties(String title, String price,
			String street_id, String type_id, String floorage,
			String publish_date);

	public List<House> query(int first, int size);
}