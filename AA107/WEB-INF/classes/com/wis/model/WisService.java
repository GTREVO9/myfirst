package com.wis.model;

import java.sql.Date;
import java.util.*;

public class WisService {

	private WisDAO_interface dao;
	
	public WisService(){
		dao = new WisDAO_JNDI();
	}
	
	public WisVO addWis( 
			Integer mem_no, String wis_title, String wis_cnt, 
			Integer cat_no, String wis_to, Date start_date, 
			Date end_date, Integer wis_like, String wis_status){
		
		WisVO wisVO = new WisVO();
		wisVO.setMem_no(mem_no);
		wisVO.setWis_title(wis_title);
		wisVO.setWis_cnt(wis_cnt);
		wisVO.setCat_no(cat_no);
		wisVO.setWis_to(wis_to);
		wisVO.setStart_date(start_date);
		wisVO.setEnd_date(end_date);
		wisVO.setWis_like(wis_like);
		wisVO.setWis_status(wis_status);
		
		dao.insert(wisVO);
		return wisVO;
	}
	
	public WisVO updateWis(Integer wis_no, 
			Integer mem_no, String wis_title, String wis_cnt, 
			Integer cat_no, String wis_to, Date start_date, 
			Date end_date, Integer wis_like, String wis_status){
		
		WisVO wisVO = new WisVO();
		wisVO.setWis_no(wis_no);
		wisVO.setMem_no(mem_no);
		wisVO.setWis_title(wis_title);
		wisVO.setWis_cnt(wis_cnt);
		wisVO.setCat_no(cat_no);
		wisVO.setWis_to(wis_to);
		wisVO.setStart_date(start_date);
		wisVO.setEnd_date(end_date);
		wisVO.setWis_like(wis_like);
		wisVO.setWis_status(wis_status);
		
		dao.update(wisVO);
		return dao.findByPrimaryKey(wis_no);		
	}
	
	public void deleteWis(Integer wis_no){
		dao.delete(wis_no);
	}
	
	public WisVO getOneWis(Integer wis_no){
		return dao.findByPrimaryKey(wis_no);
	}
	
	public List<WisVO> getAll(){
		return dao.getAll();
	}

}
