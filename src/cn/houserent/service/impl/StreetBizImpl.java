package cn.houserent.service.impl;

import java.util.List;

import cn.houserent.dao.StreetDao;
import cn.houserent.dao.hibimpl.StreetDaoHibImpl;
import cn.houserent.entity.Street;
import cn.houserent.service.StreetBiz;

public class StreetBizImpl implements StreetBiz {
	private StreetDao streetDao;
	
	public void setStreetDao(StreetDao streetDao) {
		this.streetDao = streetDao;
	}

	/* (non-Javadoc)
	 * @see cn.houserent.service.impl.StreetBiz#getAllStreet()
	 */
	public List<Street> getAllStreet(){		
		List<Street> result = streetDao.findAll();
		return result;
	}
	
	/* (non-Javadoc)
	 * @see cn.houserent.service.impl.StreetBiz#getStreetById(java.lang.Integer)
	 */
	public Street getStreetById(Integer id){
		try {
			Street street = streetDao.findById(id);
			return street;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}
}
