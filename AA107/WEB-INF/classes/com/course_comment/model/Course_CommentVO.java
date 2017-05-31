package com.course_comment.model;

import java.sql.Date;

public class Course_CommentVO implements java.io.Serializable{

	private Integer ccmt_no;
	private Integer crs_no;
	private Integer mem_no;
	private String ccmt_cont;
	private String ccmt_status;
	private Date ccmt_posttime;
	
	
	public Integer getCcmt_no() {
		return ccmt_no;
	}
	public void setCcmt_no(Integer ccmt_no) {
		this.ccmt_no = ccmt_no;
	}
	public Integer getCrs_no() {
		return crs_no;
	}
	public void setCrs_no(Integer crs_no) {
		this.crs_no = crs_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public String getCcmt_cont() {
		return ccmt_cont;
	}
	public void setCcmt_cont(String ccmt_cont) {
		this.ccmt_cont = ccmt_cont;
	}
	public String getCcmt_status() {
		return ccmt_status;
	}
	public void setCcmt_status(String ccmt_status) {
		this.ccmt_status = ccmt_status;
	}
	public Date getCcmt_posttime() {
		return ccmt_posttime;
	}
	public void setCcmt_posttime(Date ccmt_posttime) {
		this.ccmt_posttime = ccmt_posttime;
	}
	
	
	
	
}
