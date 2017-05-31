package com.course_comment.model;

import java.util.List;
import java.util.*;

import com.ccmt_report.model.Ccmt_ReportVO;


public interface Course_CommentDAO_interface {
	
	public void insert(Course_CommentVO ccmtVO);
	public void update(Course_CommentVO ccmtVO);
	public void delete(Integer ccmt_no);
	public Course_CommentVO findByPrimaryKey(Integer ccmt_no);
	public List<Course_CommentVO> getAll();
	
	 //�d�߬Y���������u(�@��h)(�^�� Set)
	public Set<Ccmt_ReportVO> getCcmtRptByCcmt(Integer ccmt_no);
	
	
	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^��List)
	
	public List<Course_CommentVO> getAll(Map<String, String[]> map);
	
	
	




}
