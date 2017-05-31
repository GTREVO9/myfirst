package com.member.model;

import java.sql.Date;

//public MemberVO(Integer mem_no, String mem_lname, String mem_fname, String mem_id, String mem_psw, String mem_email,
//String mem_mobile, byte[] mem_photo, Date mem_birthday, String mem_address, Date mem_joindate,
//String mem_bank, Integer mem_point, String mem_status) {

public class MemberVO implements java.io.Serializable {

	// String mem_lname;//會員姓氏*, String mem_fname;//會員名字*,String mem_id;//會員帳號*
	// ,String mem_psw;//會員密碼*,//String mem_email;//會員電子郵件*,
	// String mem_mobile;//會員行動電話*,Date mem_birthday;//會員生日*
	// String mem_address;//會員地址*

	private Integer mem_no;// 會員編號
	private String mem_lname;// 會員姓氏*
	private String mem_fname;// 會員名字*
	private String mem_id;// 會員帳號*
	private String mem_psw;// 會員密碼*
	private String mem_email;// 會員電子郵件*
	private String mem_mobile;// 會員行動電話*
	private byte[] mem_photo;// 會員大頭照
	private String memPotoBase64;// 會員大頭照
	private Date mem_birthday;// 會員生日*

	public String getMemPotoBase64() {
		return memPotoBase64;
	}

	public void setMemPotoBase64(String memPotoBase64) {
		this.memPotoBase64 = memPotoBase64;
	}

	private String mem_address;// 會員地址*
	private Date mem_joindate;//// 加入會員時間
	private String mem_bank;// 會員銀行帳號
	private Integer mem_point;// 會員點數餘額
	private String mem_status;// 會員狀態

	public Integer getMem_no() {
		return mem_no;
	}

	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_lname() {
		return mem_lname;
	}

	public void setMem_lname(String mem_lname) {
		this.mem_lname = mem_lname;
	}

	public String getMem_fname() {
		return mem_fname;
	}

	public void setMem_fname(String mem_fname) {
		this.mem_fname = mem_fname;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_psw() {
		return mem_psw;
	}

	public void setMem_psw(String mem_psw) {
		this.mem_psw = mem_psw;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_mobile() {
		return mem_mobile;
	}

	public void setMem_mobile(String mem_mobile) {
		this.mem_mobile = mem_mobile;
	}

	public byte[] getMem_photo() {
		return mem_photo;
	}

	public void setMem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}

	public Date getMem_birthday() {
		return mem_birthday;
	}

	public void setMem_birthday(Date mem_birthday) {
		this.mem_birthday = mem_birthday;
	}

	public String getMem_address() {
		return mem_address;
	}

	public void setMem_address(String mem_address) {
		this.mem_address = mem_address;
	}

	public Date getMem_joindate() {
		return mem_joindate;
	}

	public void setMem_joindate(Date mem_joindate) {
		this.mem_joindate = mem_joindate;
	}

	public String getMem_bank() {
		return mem_bank;
	}

	public void setMem_bank(String mem_bank) {
		this.mem_bank = mem_bank;
	}

	public Integer getMem_point() {
		return mem_point;
	}

	public void setMem_point(Integer mem_point) {
		this.mem_point = mem_point;
	}

	public String getMem_status() {
		return mem_status;
	}

	public void setMem_status(String mem_status) {
		this.mem_status = mem_status;
	}

}
