package com.wish_comment.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.wish_comment.model.*;




public class Wish_CommentServlet extends HttpServlet {
	
	// private ServletContext context;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
	
		


		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("wcmt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("許願池留言編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/wish_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer wcmt_no = null;
				try {
					wcmt_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("許願池留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/wish_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Wish_CommentService wish_commentSvc = new Wish_CommentService();
				Wish_CommentVO wish_commentVO = wish_commentSvc.getOneWish_Comment(wcmt_no);
				if (wish_commentVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/wish_comment/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("wish_commentVO", wish_commentVO); // 資料庫取出的empVO物件,存入req
				String url = "/wish_comment/listOneWish_Comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneWish_Comment.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/wish_comment/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllWish_Comment.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer wcmt_no = new Integer(req.getParameter("wcmt_no"));
				
				/***************************2.開始查詢資料****************************************/
				Wish_CommentService wish_commentSvc = new Wish_CommentService();
				Wish_CommentVO wish_commentVO = wish_commentSvc.getOneWish_Comment(wcmt_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("wish_commentVO", wish_commentVO);         // 資料庫取出的wish_commentVO物件,存入req
				String url = "/wish_comment/update_wish_comment_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_wish_comment_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/wish_comment/listAllWish_Comment.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_wish_comment_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer wcmt_no = new Integer(req.getParameter("wcmt_no").trim());
				Integer wis_no = new Integer(req.getParameter("wis_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String wcmt_cont = req.getParameter("wcmt_cont").trim();
				
				java.sql.Timestamp wcmt_time = null;
				try {
					wcmt_time = java.sql.Timestamp.valueOf(req.getParameter("wcmt_time").trim());
				} catch (IllegalArgumentException e) {
					wcmt_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String wcmt_status = null;
				try {
					wcmt_status = req.getParameter("wcmt_status").trim();
				} catch (NumberFormatException e) {
					wcmt_status = "0";
					errorMsgs.add("請輸入狀態碼.");
				}

				
				Wish_CommentVO wish_commentVO = new Wish_CommentVO();
				wish_commentVO.setWcmt_no(wcmt_no);
				wish_commentVO.setWis_no(wis_no);
				wish_commentVO.setMem_no(mem_no);
				wish_commentVO.setWcmt_cont(wcmt_cont);
				wish_commentVO.setWcmt_time(wcmt_time);
				wish_commentVO.setWcmt_status(wcmt_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wish_commentVO", wish_commentVO); // 含有輸入格式錯誤的wish_commentVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/wish_comment/update_wish_comment_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Wish_CommentService wish_commentSvc = new Wish_CommentService();
				wish_commentVO = wish_commentSvc.updateWish_Comment(wcmt_no, wis_no, mem_no, wcmt_cont, wcmt_time,wcmt_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("wish_commentVO", wish_commentVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/wish_comment/listOneWish_Comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneWish_Comment.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/wish_comment/update_wish_comment_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer wis_no = new Integer(req.getParameter("wis_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String wcmt_cont = req.getParameter("wcmt_cont").trim();
				
				java.sql.Timestamp wcmt_time = null;
				try {
					wcmt_time = java.sql.Timestamp.valueOf(req.getParameter("wcmt_time").trim());
				} catch (IllegalArgumentException e) {
					wcmt_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
	
				String wcmt_status = null;
				try {
					wcmt_status = req.getParameter("wcmt_status").trim();
				} catch (NumberFormatException e) {
					wcmt_status = "0";
					errorMsgs.add("請輸入狀態碼.");
				}
				

				Wish_CommentVO wish_commentVO = new Wish_CommentVO();
				wish_commentVO.setWis_no(wis_no);
				wish_commentVO.setMem_no(mem_no);
				wish_commentVO.setWcmt_cont(wcmt_cont);
				wish_commentVO.setWcmt_time(wcmt_time);
				wish_commentVO.setWcmt_status(wcmt_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wish_commentVO", wish_commentVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/wish_comment/addWish_Comment.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Wish_CommentService wish_commentSvc = new Wish_CommentService();
				wish_commentVO = wish_commentSvc.addWish_Comment(wis_no, mem_no, wcmt_cont, wcmt_time,wcmt_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/wish_comment/listAllWish_Comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllWish_Comment.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/wish_comment/addWish_Comment.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer wcmt_no = new Integer(req.getParameter("wcmt_no"));
				
				/***************************2.開始刪除資料***************************************/
				Wish_CommentService wish_commentSvc = new Wish_CommentService();
				wish_commentSvc.deleteWish_Comment(wcmt_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/wish_comment/listAllWish_Comment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/wish_comment/listAllWish_Comment.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

