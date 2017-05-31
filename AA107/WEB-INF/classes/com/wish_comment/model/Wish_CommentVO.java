package com.wish_comment.model;
import java.sql.Date;
import java.sql.Timestamp;

public class Wish_CommentVO implements java.io.Serializable{
	private Integer wcmt_no;
	private Integer wis_no;
	private Integer mem_no;
	private String wcmt_cont;
	private Timestamp wcmt_time;
	private String wcmt_status;
	
	
	public Integer getWcmt_no() {
		return wcmt_no;
	}
	public void setWcmt_no(Integer wcmt_no) {
		this.wcmt_no = wcmt_no;
	}
	public Integer getWis_no() {
		return wis_no;
	}
	public void setWis_no(Integer wis_no) {
		this.wis_no = wis_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public String getWcmt_cont() {
		return wcmt_cont;
	}
	public void setWcmt_cont(String wcmt_cont) {
		this.wcmt_cont = wcmt_cont;
	}
	public Timestamp getWcmt_time() {
		return wcmt_time;
	}
	public void setWcmt_time(Timestamp wcmt_time) {
		this.wcmt_time = wcmt_time;
	}
	public String getWcmt_status() {
		return wcmt_status;
	}
	public void setWcmt_status(String wcmt_status) {
		this.wcmt_status = wcmt_status;
	}
	
	
	

}
