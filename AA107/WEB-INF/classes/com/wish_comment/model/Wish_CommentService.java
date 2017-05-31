package com.wish_comment.model;

import java.util.List;

public class Wish_CommentService {

	private Wish_CommentDAO_interface dao;

	public Wish_CommentService() {
		dao = new Wish_CommentDAO();
	}

	public Wish_CommentVO addWish_Comment(Integer wis_no, Integer mem_no,String wcmt_cont, java.sql.Timestamp wcmt_time,
			String wcmt_status) {

		Wish_CommentVO wish_commentVO = new Wish_CommentVO();

		wish_commentVO.setWis_no(wis_no);
		wish_commentVO.setMem_no(mem_no);
		wish_commentVO.setWcmt_cont(wcmt_cont);
		wish_commentVO.setWcmt_time(wcmt_time);
		wish_commentVO.setWcmt_status(wcmt_status);
		dao.insert(wish_commentVO);


		return wish_commentVO;
	}

	public Wish_CommentVO updateWish_Comment(Integer wcmt_no, Integer wis_no, Integer mem_no,String wcmt_cont, java.sql.Timestamp wcmt_time,
			String wcmt_status) {

		Wish_CommentVO wish_commentVO = new Wish_CommentVO();

		wish_commentVO.setWcmt_no(wcmt_no);
		wish_commentVO.setWis_no(wis_no);
		wish_commentVO.setMem_no(mem_no);
		wish_commentVO.setWcmt_cont(wcmt_cont);
		wish_commentVO.setWcmt_time(wcmt_time);
		wish_commentVO.setWcmt_status(wcmt_status);
		dao.update(wish_commentVO);

		return wish_commentVO;
	}

	public void deleteWish_Comment(Integer wcmt_no) {
		dao.delete(wcmt_no);
	}

	public Wish_CommentVO getOneWish_Comment(Integer wcmt_no) {
		return dao.findByPrimaryKey(wcmt_no);
	}

	public List<Wish_CommentVO> getAll() {
		return dao.getAll();
	}
	public List<Wish_CommentVO> getMem_no(Integer mem_no){
		return dao.getMem_no(mem_no);
	}
}
