package com.wish_report.model;
import java.util.*;

public interface Wish_ReportDAO_interface {

	public void insert(Wish_ReportVO wishptVO);
	public void update(Wish_ReportVO  wishptVO);
	public void delete(Integer wrep_no);
	public Wish_ReportVO findByPrimaryKey(Integer wrep_no);
	public List<Wish_ReportVO> getAll();
	
	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^��List)
	public List<Wish_ReportVO> getAll(Map<String, String[]> map);
	
}
