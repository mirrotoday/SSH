package cn.houserent.service.impl;

import cn.houserent.dao.TypeDao;
import cn.houserent.dao.hibimpl.TypeDaoHibImpl;
import cn.houserent.entity.Type;
import cn.houserent.service.TypeBiz;

public class TypeBizImpl implements TypeBiz {
	private TypeDao typeDao;
	public void setTypeDao(TypeDao typeDao) {
		this.typeDao = typeDao;
	}
	/* (non-Javadoc)
	 * @see cn.houserent.service.impl.TypeBiz#getTypeById(java.lang.Integer)
	 */
	public Type getTypeById(Integer id){
		
		try {
			Type type = typeDao.findById(id);		
			return type;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
