package cn.houserent.service;

import java.util.List;

import cn.houserent.entity.Street;

public interface StreetBiz {

	public List<Street> getAllStreet();

	public Street getStreetById(Integer id);

}