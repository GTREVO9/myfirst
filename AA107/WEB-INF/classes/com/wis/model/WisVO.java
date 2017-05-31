package com.wis.model;

import java.sql.*;

public class WisVO {
	
	private Integer wis_no;       //1
	private Integer mem_no;       //2
	private String wis_title;     //3
	private String wis_cnt;       //4
	private Integer cat_no;       //5
	private String wis_to;        //6
	private Date start_date;      //7
	private Date end_date;        //8
	private Integer wis_like;     //9
	private String wis_status;    //10
	
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
	public String getWis_title() {
		return wis_title;
	}
	public void setWis_title(String wis_title) {
		this.wis_title = wis_title;
	}
	public String getWis_cnt() {
		return wis_cnt;
	}
	public void setWis_cnt(String wis_cnt) {
		this.wis_cnt = wis_cnt;
	}
	public Integer getCat_no() {
		return cat_no;
	}
	public void setCat_no(Integer cat_no) {
		this.cat_no = cat_no;
	}
	public String getWis_to() {
		return wis_to;
	}
	public void setWis_to(String wis_to) {
		this.wis_to = wis_to;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Integer getWis_like() {
		return wis_like;
	}
	public void setWis_like(Integer wis_like) {
		this.wis_like = wis_like;
	}
	public String getWis_status() {
		return wis_status;
	}
	public void setWis_status(String wis_status) {
		this.wis_status = wis_status;
	}
	
}
