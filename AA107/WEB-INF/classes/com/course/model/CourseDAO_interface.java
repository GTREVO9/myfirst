package com.course.model;

import java.util.List;
import java.util.Set;

import com.course_comment.model.Course_CommentVO;


public interface CourseDAO_interface {
	public void insert(CourseVO courseVO);
    public void update(CourseVO courseVO);
    public void delete(Integer crs_no);
    public CourseVO findByPrimaryKey(Integer crs_no);
    public List<CourseVO> getAll();
    
    public Set<Course_CommentVO> getCcmtByCourse(Integer crs_no);
    

}
