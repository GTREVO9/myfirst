package com.member.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import sun.misc.BASE64Encoder;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(String mem_lname, String mem_fname, String mem_id, String mem_psw, String mem_email,
			String mem_mobile, byte[] mem_photo, Date mem_birthday, String mem_address, Date mem_joindate,
			String mem_bank, Integer mem_point, String mem_status) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMem_lname(mem_lname);
		memberVO.setMem_fname(mem_fname);
		memberVO.setMem_id(mem_id);
		memberVO.setMem_psw(mem_psw);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_mobile(mem_mobile);
		memberVO.setMem_photo(mem_photo);
		memberVO.setMem_birthday(mem_birthday);
		memberVO.setMem_address(mem_address);
		memberVO.setMem_joindate(mem_joindate);
		memberVO.setMem_bank(mem_bank);
		memberVO.setMem_point(mem_point);
		memberVO.setMem_status(mem_status);

		dao.insert(memberVO);

		return memberVO;
	}

	public MemberVO updateMember(Integer mem_no, String mem_lname, String mem_fname, String mem_id, String mem_psw,
			String mem_email, String mem_mobile, byte[] mem_photo, Date mem_birthday, String mem_address,
			Date mem_joindate, String mem_bank, Integer mem_point, String mem_status) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMem_no(mem_no);
		memberVO.setMem_lname(mem_lname);
		memberVO.setMem_fname(mem_fname);
		memberVO.setMem_id(mem_id);
		memberVO.setMem_psw(mem_psw);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_mobile(mem_mobile);
		memberVO.setMem_photo(mem_photo);
		memberVO.setMem_birthday(mem_birthday);
		memberVO.setMem_address(mem_address);
		memberVO.setMem_joindate(mem_joindate);
		memberVO.setMem_bank(mem_bank);
		memberVO.setMem_point(mem_point);
		memberVO.setMem_status(mem_status);

		dao.update(memberVO);

		return memberVO;
	}

	public void deleteMember(Integer mem_no) {
		dao.delete(mem_no);
	}

	public MemberVO getOnemember(Integer mem_no) {
		MemberVO memberVO = dao.findByPrimaryKey(mem_no);
		if(memberVO == null){
			return null;
		}
		byte[] phto = memberVO.getMem_photo();
		String base64 = blobToBase64(phto);
		memberVO.setMemPotoBase64(base64);
		;

		return memberVO;
	}

	public MemberVO getOnemember(String mem_id) {
		MemberVO memberVO = dao.findByPrimaryKey(mem_id);
		if(memberVO == null){
			return null;
		}
		byte[] phto = memberVO.getMem_photo();
		String base64 = blobToBase64(phto);
		memberVO.setMemPotoBase64(base64);
		

		return memberVO;
	}
	public List<MemberVO> getAll() {
		List<MemberVO> memberList = dao.getAll();
		for (MemberVO memberVO : memberList) {
			byte[] phto = memberVO.getMem_photo();
			String base64 = blobToBase64(phto);
			memberVO.setMemPotoBase64(base64);
			;
		}

		return memberList;

	}

	private String blobToBase64(byte[] phto) {
		return phto != null ? new BASE64Encoder().encode(phto) : null;

	}
}

// 1.(印出所有的值)
// System.out.println(svc.getAll());

// 2.(用PK查單項的值)
// MemberVO vo=svc.getOnemember(11000009);//
// System.out.println(vo.getMem_address());
// System.out.println(vo.getMem_email());
// System.out.println(vo.getMem_id());
// System.out.println(vo.getMem_lname());
// System.out.println(vo.getMem_fname());
// System.out.println(vo.getMem_psw());
// System.out.println(vo.getMem_status());

// 3.(用PK增加表格內容)
// svc.addMember(11000010, "林","心如",
// "a106","a106","a0985@gmail.com","0911200989",
// null,(java.sql.Date.valueOf("2008-04-01")),"桃園市中正路333號",
// (java.sql.Date.valueOf("2008-04-01")),"889911",100,"0");

// 4.(用PK修改表格內容)
// svc.updateMember(11000010,"孫","協志",
// "a106","a106","a0985@gmail.com","0911200989",
// null,(java.sql.Date.valueOf("2008-04-01")),"桃園市中正路333號",
// (java.sql.Date.valueOf("2008-04-01")),"889911",100,"0");
