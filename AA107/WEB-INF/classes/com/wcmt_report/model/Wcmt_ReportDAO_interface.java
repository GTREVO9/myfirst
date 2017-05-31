package com.wcmt_report.model;

import java.util.List;
import java.util.Map;

public interface Wcmt_ReportDAO_interface {
	
	
	public void insert(Wcmt_ReportVO wcmtRptVO);
	public void update(Wcmt_ReportVO wcmtRptVO);
	public void delete(Integer wcrep_no);
	public Wcmt_ReportVO findByPrimaryKey(Integer wcrep_no);
	public List<Wcmt_ReportVO> getAll();
	
	//萬用複合查詢(傳入參數型態Map)(回傳Lis)
	public List<Wcmt_ReportVO> getAll(Map<String, String[]> map);

}
