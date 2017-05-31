package com.course.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.course_comment.model.Course_CommentVO;

public class CourseService {
	
private CourseDAO_interface dao;
	
	public CourseService(){
		dao = new CourseDAO();
	}
	
	public CourseVO addCourse(Integer ach_no,Integer mem_no,Integer cat_no,String crs_teacher,String crs_name,String crs_info,String crs_cont,byte[] crs_image0,byte[] crs_image1,byte[] crs_image2,byte[] crs_introvideo,Integer crs_fr_target,Date crs_fr_str,Date crs_fr_fin,Integer crs_fr_num,Integer crs_price
			,String crs_status,Date crs_create,String crs_stand,Integer crs_ppl,String crs_time) {

		CourseVO courseVO = new CourseVO();
		courseVO.setAch_no(ach_no);
		courseVO.setMem_no(mem_no);
		courseVO.setCat_no(cat_no);
		courseVO.setCrs_teacher(crs_teacher);
		courseVO.setCrs_name(crs_name);
		courseVO.setCrs_info(crs_info);
		courseVO.setCrs_cont(crs_cont);
		courseVO.setCrs_image0(crs_image0);
		courseVO.setCrs_image1(crs_image1);
		courseVO.setCrs_image2(crs_image2);
		courseVO.setCrs_introvideo(crs_introvideo);
		courseVO.setCrs_fr_target(crs_fr_target);
		courseVO.setCrs_fr_str(crs_fr_str);
		courseVO.setCrs_fr_fin(crs_fr_fin);
		courseVO.setCrs_fr_num(crs_fr_num);
		courseVO.setCrs_price(crs_price);
		courseVO.setCrs_status(crs_status);
		courseVO.setCrs_create(crs_create);
		courseVO.setCrs_stand(crs_stand);
		courseVO.setCrs_ppl(crs_ppl);
		courseVO.setCrs_time(crs_time);

		dao.insert(courseVO);

		return courseVO;
	}
	
	public CourseVO updateCourse(Integer crs_no,Integer ach_no,Integer mem_no,Integer cat_no,String crs_teacher,String crs_name,String crs_info,String crs_cont,byte[] crs_image0,byte[] crs_image1,byte[] crs_image2,byte[] crs_introvideo,Integer crs_fr_target,Date crs_fr_str,Date crs_fr_fin,Integer crs_fr_num,Integer crs_price
			,String crs_status,Date crs_create,String crs_stand,Integer crs_ppl,String crs_time) {
		
		CourseVO courseVO = new CourseVO();
		courseVO.setCrs_no(crs_no);
		courseVO.setAch_no(ach_no);
		courseVO.setMem_no(mem_no);
		courseVO.setCat_no(cat_no);
		courseVO.setCrs_teacher(crs_teacher);
		courseVO.setCrs_name(crs_name);
		courseVO.setCrs_info(crs_info);
		courseVO.setCrs_cont(crs_cont);
		courseVO.setCrs_image0(crs_image0);
		courseVO.setCrs_image1(crs_image1);
		courseVO.setCrs_image2(crs_image2);
		courseVO.setCrs_introvideo(crs_introvideo);
		courseVO.setCrs_fr_target(crs_fr_target);
		courseVO.setCrs_fr_str(crs_fr_str);
		courseVO.setCrs_fr_fin(crs_fr_fin);
		courseVO.setCrs_fr_num(crs_fr_num);
		courseVO.setCrs_price(crs_price);
		courseVO.setCrs_status(crs_status);
		courseVO.setCrs_create(crs_create);
		courseVO.setCrs_stand(crs_stand);
		courseVO.setCrs_ppl(crs_ppl);
		courseVO.setCrs_time(crs_time);
		dao.update(courseVO);
		
		return courseVO;
	}

	public void deleteCourse(Integer crs_no) {
		dao.delete(crs_no);
	}

	public CourseVO getOneCourse(Integer crs_no) {
		return dao.findByPrimaryKey(crs_no);
	}

	public List<CourseVO> getAll() {
		return dao.getAll();
	}
	
	

	public Set<Course_CommentVO> getCcmtByCourse(Integer crs_no) {
		return dao.getCcmtByCourse(crs_no);
	}


	 


}
