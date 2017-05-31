package com.ccmt_report.model;

import java.util.*;

public interface Ccmt_ReportDAO_interface {
	
	public void insert(Ccmt_ReportVO ccmtRptVO);
	public void update(Ccmt_ReportVO ccmtRptVO);
	public void delete(Integer crep_no);
	public Ccmt_ReportVO findByPrimaryKey(Integer crep_no);
	public List<Ccmt_ReportVO> getAll();
	
	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^��List)
//	public List<Ccmt_ReportVO> getAll(Map<String, String[]> map);
	
	
}
