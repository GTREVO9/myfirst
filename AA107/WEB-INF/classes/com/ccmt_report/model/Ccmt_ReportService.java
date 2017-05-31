package com.ccmt_report.model;

import java.util.List;



public class Ccmt_ReportService {


private Ccmt_ReportDAO_interface dao;


public Ccmt_ReportService() {
	dao = new Ccmt_ReportDAO();
}


public Ccmt_ReportVO addCcmt_Report(Integer ccmt_no, Integer mem_no,
		String crep_title, String crep_cnt, String crep_status,Integer adm_no,
		String crep_result) {
			
	
	
	Ccmt_ReportVO ccmtRptVO = new Ccmt_ReportVO();
	
	ccmtRptVO.setCcmt_no(ccmt_no);
	ccmtRptVO.setMem_no(mem_no);
	ccmtRptVO.setCrep_title(crep_title);
	ccmtRptVO.setCrep_cnt(crep_cnt);
	ccmtRptVO.setCrep_status(crep_status);
	ccmtRptVO.setAdm_no(adm_no);
	ccmtRptVO.setCrep_result(crep_result);
	dao.insert(ccmtRptVO);
	
	
	return ccmtRptVO;
	
}




public Ccmt_ReportVO updateCcmt_Report(Integer crep_no,Integer ccmt_no, Integer mem_no,
		String crep_title, String crep_cnt, String crep_status,Integer adm_no,
		String crep_result) {
			
	
	Ccmt_ReportVO ccmtRptVO = new Ccmt_ReportVO();
	ccmtRptVO.setCrep_no(crep_no);
	ccmtRptVO.setCcmt_no(ccmt_no);
	ccmtRptVO.setMem_no(mem_no);
	ccmtRptVO.setCrep_title(crep_title);
	ccmtRptVO.setCrep_cnt(crep_cnt);
	ccmtRptVO.setCrep_status(crep_status);
	ccmtRptVO.setAdm_no(adm_no);
	ccmtRptVO.setCrep_result(crep_result);	
	dao.update(ccmtRptVO);
			
	return ccmtRptVO;
		
	
}



public void deleteCcmt_Report(Integer crep_no) {
	dao.delete(crep_no);
}



public Ccmt_ReportVO getOneCcmt_Report(Integer crep_no) {
	return dao.findByPrimaryKey(crep_no);
}




public List<Ccmt_ReportVO> getAll() {
	return dao.getAll();
}







}
