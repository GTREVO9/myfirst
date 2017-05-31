package com.wcmt_report.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


import com.wish_comment.model.Wish_CommentVO;

public class Wcmt_ReportVO implements java.io.Serializable{


	private Integer wcrep_no;
	private Integer wcmt_no;	
	private Integer mem_no;
	private String wcrep_title;
	private String wcrep_cont;
	private Date wcrep_time;
	private String wcrep_status;
	private Integer adm_no;
	private String wcrep_result;
	
	
	private Set<Wish_CommentVO> Wish_Comments = new HashSet<Wish_CommentVO>();
	
	
	public Set<Wish_CommentVO> getWish_Comments() {
		return Wish_Comments;
	}
	public void setWish_Comments(Set<Wish_CommentVO> wish_Comments) {
		Wish_Comments = wish_Comments;
	}
	
	
	public Integer getWcrep_no() {
		return wcrep_no;
	}
	public void setWcrep_no(Integer wcrep_no) {
		this.wcrep_no = wcrep_no;
	}
	public Integer getWcmt_no() {
		return wcmt_no;
	}
	public void setWcmt_no(Integer wcmt_no) {
		this.wcmt_no = wcmt_no;
	}

	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public String getWcrep_title() {
		return wcrep_title;
	}
	public void setWcrep_title(String wcrep_title) {
		this.wcrep_title = wcrep_title;
	}
	public String getWcrep_cont() {
		return wcrep_cont;
	}
	public void setWcrep_cont(String wcrep_cont) {
		this.wcrep_cont = wcrep_cont;
	}
	public Date getWcrep_time() {
		return wcrep_time;
	}
	public void setWcrep_time(Date wcrep_time) {
		this.wcrep_time = wcrep_time;
	}
	public String getWcrep_status() {
		return wcrep_status;
	}
	public void setWcrep_status(String wcrep_status) {
		this.wcrep_status = wcrep_status;
	}
	public Integer getAdm_no() {
		return adm_no;
	}
	public void setAdm_no(Integer adm_no) {
		this.adm_no = adm_no;
	}
	public String getWcrep_result() {
		return wcrep_result;
	}
	public void setWcrep_result(String wcrep_result) {
		this.wcrep_result = wcrep_result;
	}
	
	


}
