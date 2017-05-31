package com.course_comment.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ccmt_report.model.Ccmt_ReportVO;

public class Course_CommentService {

	private Course_CommentDAO_interface dao;
	
	public Course_CommentService(){
		
		dao = new Course_CommentDAO();
	}

	public List<Course_CommentVO> getAll(){
		return dao.getAll();
	}
	
	public List<Course_CommentVO> getAll(Map<String, String[]> map){
		return dao.getAll(map);
	}
	
	
	public Course_CommentVO getOneCourse_Comment(Integer ccmt_no){
		return dao.findByPrimaryKey(ccmt_no);		
	}
	
	
	public Set<Ccmt_ReportVO> getCcmtRptByCcmt(Integer ccmt_no){
		return dao.getCcmtRptByCcmt(ccmt_no);
	}
	
	
	public Course_CommentVO addCourse_Comment(Integer crs_no, Integer mem_no, String 
			ccmt_cont, String ccmt_status ,java.sql.Date ccmt_posttime){

Course_CommentVO ccmtVO = new Course_CommentVO();

ccmtVO.setCrs_no(crs_no);
ccmtVO.setMem_no(mem_no);
ccmtVO.setCcmt_cont(ccmt_cont);
ccmtVO.setCcmt_status(ccmt_status);
ccmtVO.setCcmt_posttime(ccmt_posttime);

dao.insert(ccmtVO);
return ccmtVO;

}
	
	
	
	public void deleteCourse_Comment(Integer ccmt_no){
		dao.delete(ccmt_no);
	}
	
	
	





}
