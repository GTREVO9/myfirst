package com.wish_report.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;



public class Wish_ReportService {


	private Wish_ReportDAO_interface dao;

	public Wish_ReportService(){
		dao = new Wish_ReportDAD();
	}


	public Wish_ReportVO getOneWish_Report(Integer wrep_no){
		
		return dao.findByPrimaryKey(wrep_no);
		
	}
	
	
	public List<Wish_ReportVO> getAll(){
		return dao.getAll();
	}


	public List<Wish_ReportVO> getAll(Map<String, String[]> map) {
		
		System.out.println("AAAAA");
		return dao.getAll(map);
		
		
	}
	
	
	public void deleteWish_Report(Integer wrep_no){		
		dao.delete(wrep_no);
	}
	
	
	public  Wish_ReportVO addWish_Report(Integer wis_no, Integer mem_no, String wrep_title, String wrep_cont, 
			java.sql.Date wrep_time, String wrep_status, Integer adm_no, String wrep_result){
		
		Wish_ReportVO wishptVO = new Wish_ReportVO();				
		wishptVO.setWis_no(wis_no);
		wishptVO.setMem_no(mem_no);
		wishptVO.setWrep_title(wrep_title);
		wishptVO.setWrep_cont(wrep_cont);
		wishptVO.setWrep_time(wrep_time);
		wishptVO.setWrep_status(wrep_status);
		wishptVO.setAdm_no(adm_no);
		wishptVO.setWrep_result(wrep_result);	
		dao.insert(wishptVO);
		
		return wishptVO;
	}
	
	
	
	public  Wish_ReportVO updateWish_Report(Integer wrep_no, Integer wis_no, Integer mem_no, String wrep_title, String wrep_cont, 
			java.sql.Date wrep_time, String wrep_status, Integer adm_no, String wrep_result){
		
		
		Wish_ReportVO wishptVO = new Wish_ReportVO();			
	    wishptVO.setWrep_no(wrep_no);				
		wishptVO.setWis_no(wis_no);
		wishptVO.setMem_no(mem_no);
		wishptVO.setWrep_title(wrep_title);
		wishptVO.setWrep_cont(wrep_cont);
		wishptVO.setWrep_time(wrep_time);
		wishptVO.setWrep_status(wrep_status);
		wishptVO.setAdm_no(adm_no);
		wishptVO.setWrep_result(wrep_result);
		dao.update(wishptVO);
		
		return wishptVO;
	}






}
