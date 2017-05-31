package com.member.model;

import java.util.List;



public interface MemberDAO_interface {
	
	public void insert(MemberVO memberVo);
	
	public void update(MemberVO memberVO);
	
	public void delete(Integer mem_no);
	
	public MemberVO findByPrimaryKey(Integer mem_no);
	
	public MemberVO findByPrimaryKey(String mem_id);
	
	public List<MemberVO>getAll();      
}
