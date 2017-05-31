package com.wis.controller;


import com.wis.model.*;
import com.wish_report.model.Wish_ReportService;
import com.wish_report.model.Wish_ReportVO;

import java.io.*;
import java.sql.Date;
import java.text.DateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class WisServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action"); // ACTION="adm.do"

		/* 查詢_一筆 */
		if ("getOne_For_Display".equals(action)) { // 來自adm_selectPage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/****************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("wis_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入分類編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					// forward回原頁_1
					RequestDispatcher failureView = req.getRequestDispatcher("/wis/wis_selectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer wis_no = null;
				try {
					wis_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("分類編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					// forward回原頁_2
					RequestDispatcher failureView = req.getRequestDispatcher("/wis/wis_selectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				WisService wisSvc = new WisService();
				WisVO wisVO = wisSvc.getOneWis(wis_no);
				if (wisVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					// forward回原頁_3
					RequestDispatcher failureView = req.getRequestDispatcher("/wis/wis_selectPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/****************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("wisVO", wisVO); // 資料庫取出的wisVO物件,存入req
				String url = "/wis/listOneWis.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				// 成功轉交forward listOnewis.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				// forward回原頁_4
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/wis/wis_selectPage.jsp");
				failureView.forward(req, res);
			}
		}

		/* 新增 */
		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String str1 = req.getParameter("mem_no").trim();
				if (str1 == null || str1.trim().length() == 0) {
					errorMsgs.put("mem_no", "會員編號請勿空白");
				}
				Integer mem_no = null;
				try{
					mem_no = new Integer(str1);
				}catch(Exception e){
					errorMsgs.put("mem_no", "會員編號格式不正確");
				}				
				
				String wis_title = req.getParameter("wis_title").trim();
				if (wis_title == null || wis_title.trim().length() == 0) {
					errorMsgs.put("wis_title", "許願標題請勿空白");
				}
				
				String wis_cnt = null;
				if(req.getParameter("wis_cnt_teach") == null){
					wis_cnt = req.getParameter("wis_cnt_learn").trim();
					if(wis_cnt == null || wis_cnt.length() == 0){						
						errorMsgs.put("wis_cnt_learn", "想學什麼請勿空白哦");
					}
				}else if(req.getParameter("wis_cnt_learn") == null){
					wis_cnt = req.getParameter("wis_cnt_teach").trim();
					if(wis_cnt == null || wis_cnt.length() == 0){						
						errorMsgs.put("wis_cnt_teach", "想教什麼請勿空白哦");
					}
				}
				
		System.out.println(wis_cnt);
		System.out.println(errorMsgs.get("wis_cnt_learn"));
		System.out.println(errorMsgs.get("wis_cnt_teach"));
		
		Integer cat_no = 1;
		
		String wis_to = req.getParameter("wis_to");
	System.out.println(wis_to);
		if(wis_to.equals("0")){
			wis_title = "我想學"+ wis_title;
		}else{
			wis_title = "我想教"+ wis_title;
		}
		
		long tm0 = System.currentTimeMillis();
		java.sql.Date start_date = new java.sql.Date(tm0);
//System.out.println(start_date);
		
		long tm1 = tm0 + 30*24*60*60*1000L;
		java.sql.Date end_date = new java.sql.Date(tm1);
//System.out.println(end_date);
		
				Integer wis_like = 0;
				String wis_status = "1";
				
				if (!errorMsgs.isEmpty()) {
System.out.println("wrong!!!!!");
					RequestDispatcher failureView = req.getRequestDispatcher("/wis/listAllWis.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				WisService wisSvc = new WisService();
				wisSvc.addWis(mem_no, wis_title, wis_cnt, cat_no, wis_to, start_date, end_date, wis_like, wis_status);
				String url = "/wis/listAllWis.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/wis/listAllWis.jsp");
				failureView.forward(req, res);
			}
		}

		/* 刪除 */
		if ("delete".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				Integer wis_no = new Integer(req.getParameter("wis_no"));
				WisService wisSvc = new WisService();
				wisSvc.deleteWis(wis_no);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		//關聯式刪除檢舉
		if ("delete_wis".equals(action)) { // 來自/dept/listAllDept.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數***************************************/
				Integer wis_no = new Integer(req.getParameter("wis_no"));
				
				/***************************2.開始刪除資料***************************************/
				WisService wisSvc = new WisService();			
				wisSvc.deleteWis(wis_no);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				
				
				Wish_ReportService wishptSvc = new Wish_ReportService();
				if(requestURL.equals("/wish_report/listWish_Report_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<Wish_ReportVO> list  = wishptSvc.getAll(map);
					req.setAttribute("listWish_Report_ByCompositeQuery",list); 
				}
				
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/wish_report/listWish_Report_ByCompositeQuery.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
//
//		/* 準備修改頁面 */
		if ("getOne_For_Update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer wis_no = new Integer(req.getParameter("wis_no"));
				WisService wisSvc = new WisService();
				WisVO wisVO = wisSvc.getOneWis(wis_no);//撈那個wis出來
				req.setAttribute("wisVO", wisVO); // 資料庫取出的admVO物件,存入req
				String url = "/wis/wis_updateInput.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
//
////		/* 修改 */TODO
		if ("update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer wis_no = new Integer(req.getParameter("wis_no").trim());
			System.out.println(wis_no);
			
				String str = req.getParameter("mem_no");
				if(str == null || str.length() == 0){
					errorMsgs.put("mem_no", "請輸入會員編號");
				}
				Integer mem_no = null;
				try{
					mem_no = new Integer(str);
				}catch(Exception e){
					errorMsgs.put("mem_no", "會員編號格式不正確");
				}				
				String wis_title = req.getParameter("wis_title").trim();
				if (wis_title == null || wis_title.trim().length() == 0) {
					errorMsgs.put("wis_title", "標題請勿空白");
				}
				 
				Integer cat_no = new Integer(req.getParameter("cat_no").trim());
				
				String wis_to = req.getParameter("wis_to").trim();
				if (wis_to == null || wis_to.trim().length() == 0) {
					errorMsgs.put("wis_to", "請選擇許願類型");
				}
				java.sql.Date start_date = null;
				try{
					start_date = java.sql.Date.valueOf(req.getParameter("start_date"));
				}catch(IllegalArgumentException e) {
					errorMsgs.put("start_date", "請輸入發起日期");
				}
				java.sql.Date end_date = null;
				try{
					end_date = java.sql.Date.valueOf(req.getParameter("end_date"));
				}catch(IllegalArgumentException e) {
					errorMsgs.put("end_date", "請輸入結束日期");
				}
				Integer wis_like = 1;
				String wis_status = "1";
				String wis_cnt = "hahaha";

				WisVO wisVO = new WisVO();
				wisVO.setWis_no(wis_no);
				wisVO.setMem_no(mem_no);
				wisVO.setWis_title(wis_title);
				wisVO.setWis_cnt(wis_cnt);
				wisVO.setCat_no(cat_no);
				wisVO.setWis_to(wis_to);
				wisVO.setStart_date(start_date);
				wisVO.setEnd_date(end_date);
				wisVO.setWis_like(wis_like);
				wisVO.setWis_status(wis_status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wisVO", wisVO); // 含有輸入格式錯誤的wisVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/wis/wis_updateInput.jsp");
					failureView.forward(req, res);
					return;
				}
				WisService wisSvc = new WisService();
				wisSvc.updateWis(wis_no, mem_no, wis_title, wis_cnt, cat_no, wis_to, start_date, end_date, wis_like, wis_status);
				req.setAttribute("wisVO", wisVO); // 資料庫update成功後,正確的的admVO物件,存入req
				String url = "/wis/listAllWis.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
System.out.println("lalala");
				e.printStackTrace();
				errorMsgs.put("Exception", "修改資料失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/wis/wis_updateInput.jsp");
				failureView.forward(req, res);
			}
		}
		
	}	
}
