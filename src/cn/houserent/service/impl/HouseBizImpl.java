package cn.houserent.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.houserent.dao.HouseDao;
import cn.houserent.dao.hibimpl.HouseDaoHibImpl;
import cn.houserent.entity.House;
import cn.houserent.entity.User;
import cn.houserent.service.HouseBiz;
import cn.houserent.util.QueryProperties;
@Service("houseBiz")
public class HouseBizImpl implements HouseBiz {

	@Autowired
	@Qualifier("houseDao")
	private HouseDao houseDao;
	public void setHouseDao(HouseDao houseDao) {
		this.houseDao = houseDao;
	}
	@Transactional
	public boolean save(House house){
		try {
			houseDao.save(house);
			return true;
		} catch (RuntimeException e) {
			return false;
		}	
		
	}
	
	/* (non-Javadoc)
	 * @see cn.houserent.service.impl.HouseBiz#getAllHouse()
	 */
	public List<House> getAllHouse(){
		List<House> result = houseDao.findAll();
		return result;
	}
	
	/* (non-Javadoc)
	 * @see cn.houserent.service.impl.HouseBiz#getHouseByTitle(java.lang.String)
	 */
	public List<House> getHouseByTitle(String title){
		if(null == title || "".equals(title)) title = "%";
		List<House> result = houseDao.findByProperty("title", title);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see cn.houserent.service.impl.HouseBiz#getHouseByProperties(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<House> getHouseByProperties(String title,String price,String street_id,String type_id,String floorage,String publish_date){
		QueryProperties qp = new QueryProperties();
		if(null == title || "".equals(title)){
			title = "%";
		} 	
		qp.setTitle("%" + title +"%");
		
		if(null != price && !"".equals(price)){
			String[] p_price = price.split("-");
			qp.setLow_price(Double.parseDouble(p_price[0].trim()));
			qp.setHigh_price(Double.parseDouble(p_price[1].trim()));
		} else{
			qp.setLow_price(Double.MIN_VALUE);
//			qp.setHigh_price(Double.MAX_VALUE);
			qp.setHigh_price((double) Integer.MAX_VALUE);
		}
		if(null != street_id && !"".equals(street_id)){
			qp.setStreet_id(street_id);
		} else{
			qp.setStreet_id("%%");
		}
		if(null != type_id && !"".equals(type_id)){
			qp.setType_id(type_id);
		} else{
			qp.setType_id("%");
		}
		if(null != floorage && !"".equals(floorage)){
			String[] p_floorage = floorage.split("-");
			qp.setSmall_floorage(Integer.parseInt(p_floorage[0].trim()));
			qp.setBig_floorage(Integer.parseInt(p_floorage[1].trim()));
		}  else{
			qp.setSmall_floorage(0);
			qp.setBig_floorage(Integer.MAX_VALUE);
		}
		if(null != publish_date && !"".equals(publish_date)){
			String[] p_publish_date = publish_date.split("-");
			qp.setStart_date(this.stringToDate(p_publish_date[0].trim()));
			
		}else{
			qp.setStart_date(this.stringToDate("1900-01-01 00:00:00"));
			
		}
		qp.setEnd_date(new Date());
		List<House> result = houseDao.findHouseByProperties(qp);
		return result;
	}
	
	protected Date stringToDate(String transform){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
		Date date = null;
		
		try {
			sdf.parse(transform);
		} catch (ParseException e) {
			e.printStackTrace();
		}			
		return date;
		
	}

	public House getHouseById(Integer id) {
		if(null == id) return null;
		House result = houseDao.findById(id);
		return result;
	}

	public boolean update(House house) {
		try {
			houseDao.update(house);
			return true;
		} catch (RuntimeException e) {
			return false;
		}	
	}

	public List<House> getHouseByUser(User user) {
		House house = new House();
		house.setUser(user);
		List<House> result = houseDao.findByExample(house);
		return result;
	}

	public List<House> query(int first, int size) {
		// TODO Auto-generated method stub
		return houseDao.query(first,size);
	}
	
}
	

