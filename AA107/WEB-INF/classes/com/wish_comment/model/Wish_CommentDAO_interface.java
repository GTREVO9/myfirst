package com.wish_comment.model;

import java.util.*;



public interface Wish_CommentDAO_interface {
          public void insert(Wish_CommentVO wish_commentVO);
          public void update(Wish_CommentVO wish_commentVO);
          public void delete(Integer wcmt_no);
          public Wish_CommentVO findByPrimaryKey(Integer wcmt_no);
          public List<Wish_CommentVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
          //public List<Wish_CommentVO> getAll(Map<String, String[]> map); 
          public List<Wish_CommentVO> getMem_no(Integer mem_no);
}
