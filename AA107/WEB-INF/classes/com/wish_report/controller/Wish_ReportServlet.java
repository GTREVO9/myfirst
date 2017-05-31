package com.wish_report.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.ccmt_report.model.Ccmt_ReportService;
import com.ccmt_report.model.Ccmt_ReportVO;
import com.course_comment.model.Course_CommentService;
import com.course_comment.model.Course_CommentVO;

import com.wis.model.WisService;
import com.wis.model.WisVO;
import com.wish_report.model.Wish_ReportService;
import com.wish_report.model.Wish_ReportVO;

public class Wish_ReportServlet extends HttpServlet{

	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
			
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if("listWish_Report_ByCompositeQuery".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
		
			
			
			
			try{
				
				
			
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				
				
				Wish_ReportService wishptSvc = new Wish_ReportService();	
				List<Wish_ReportVO> list = wishptSvc.getAll(map);	
				
				
				req.setAttribute("listWish_Report_ByCompositeQuery",list);
				RequestDispatcher successView = req.getRequestDispatcher("/wish_report/listWish_Report_ByCompositeQuery.jsp"); // 嚙踝蕭嚙穀嚙踝蕭嚙締istEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				
			
			} catch (Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/course/Course.jsp");
			}
		
		}
		
		
		
		
		
		
		
		
		
		if ("delete".equals(action)) { 
			System.out.println("AAAAAAAAAA");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); 

			try {
				
				Integer wrep_no = new Integer(req.getParameter("wrep_no"));
				
				
				Wish_ReportService wishptSvc = new Wish_ReportService();		
				Wish_ReportVO wishptVO = wishptSvc.getOneWish_Report(wrep_no);
				wishptSvc.deleteWish_Report(wrep_no);
				
				
WisService wisSvc = new WisService();
				
				
				if(requestURL.equals("/wish_report/listWish_Report_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<Wish_ReportVO> list  = wishptSvc.getAll(map);
					req.setAttribute("listWish_Report_ByCompositeQuery",list); 
				}
				
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("錯誤:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/wish_report/listWish_Report_ByCompositeQuery.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		if("submitWishRpt_insert".equals(action)){ 
			System.out.println("AAAAAAAA");
			     List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				String requestURL = req.getParameter("requestURL"); 
				
				try {
					System.out.println("bbbbbbbb");
		
					
					System.out.println("ccccccccc");
					Integer wis_no = new Integer(req.getParameter("wis_no").trim());
					System.out.println("ddddddddd");
					Integer mem_no = new Integer(req.getParameter("mem_no").trim());
					
					String wrep_title = req.getParameter("wrep_title");
					if(wrep_title == null || (wrep_title.trim()).length() == 0){
						
						 errorMsgs.add("請輸入檢舉標題");
					}
					
					if(!errorMsgs.isEmpty()){
						RequestDispatcher failureView = req.
								getRequestDispatcher("/wish_report/addWish_Report.jsp");
					
						failureView.forward(req, res);
						return;
					}
					
					
					
					String wrep_cont = req.getParameter("wrep_cont");
					if(wrep_cont == null || (wrep_cont.trim()).length() == 0){
						
						 errorMsgs.add("請輸入檢舉原因");
					}
					
					
					if(!errorMsgs.isEmpty()){
						RequestDispatcher failureView = req.
								getRequestDispatcher("/wish_report/addWish_Report.jsp");
					
						failureView.forward(req, res);
						return;
					}
					
						
					
					
					java.sql.Date wrep_time;
					
					try{
						wrep_time = java.sql.Date.valueOf(req
								.getParameter("wrep_time").trim());
						
					}catch(IllegalArgumentException e){
						
						wrep_time = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期");
						
					}
				
																							
					String wrep_status = "0";
				
					Integer adm_no = 13000003;
					String wrep_result = null;
																					
					Wish_ReportVO wishptVO = new Wish_ReportVO();
					
					wishptVO.setWis_no(wis_no);
					wishptVO.setMem_no(mem_no);
					wishptVO.setWrep_title(wrep_title);
					wishptVO.setWrep_cont(wrep_cont);
					wishptVO.setWrep_time(wrep_time);
					wishptVO.setWrep_status(wrep_status);
					wishptVO.setAdm_no(adm_no);
					wishptVO.setWrep_result(wrep_result);
					
					//Send the use back to the form,if there were errors
					if(!errorMsgs.isEmpty()){
						req.setAttribute("wishptVO",wishptVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/wish_report/addWish_Report.jsp");
						failureView.forward(req, res);
					
					}				
					
										
					
					
					Wish_ReportService wishptSvc = new Wish_ReportService();		
					wishptVO = wishptSvc.addWish_Report(wis_no, mem_no, wrep_title, wrep_cont, wrep_time, wrep_status, adm_no, wrep_result);																
					
																		
					WisService wisSvc = new WisService();
										
					if(requestURL.equals("/wish_report/listWish_Report_ByCompositeQuery.jsp")){
						HttpSession session = req.getSession();
						Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
						List<Wish_ReportVO> list  = wishptSvc.getAll(map);
						req.setAttribute("list",list); 
					}
					
					
					String url = requestURL;
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
																
					System.out.println("ddddddddddd");
																		 															
				}catch(Exception e){
					
					e.printStackTrace();
					errorMsgs.add("錯誤" +e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/wish_report/addWish_Report.jsp");
							failureView.forward(req, res);
																			
				}
				
				
			}
		 
		 
		 
		
	
	
	
	
	
	
	
}













}
