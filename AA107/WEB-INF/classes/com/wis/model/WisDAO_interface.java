package com.wis.model;

import java.util.List;

public interface WisDAO_interface {
	
	public void insert(WisVO wisVO);
	public void delete(Integer wis_no);
	public void update(WisVO wisVO);
	public WisVO findByPrimaryKey(Integer wis_no);
	public List<WisVO> getAll();
	
}
