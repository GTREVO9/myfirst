package com.course_comment.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccmt_report.model.Ccmt_ReportService;
import com.ccmt_report.model.Ccmt_ReportVO;
import com.course.model.CourseService;
import com.course_comment.model.*;




public class Course_CommentServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
	
		
		
	    // 來自select_page.jsp的請求                                  // 來自 dept/listAllDept.jsp的請求
			if ("listCcmtRptByCcmt_A".equals(action) || "listCcmtRptByCcmt_B".equals(action)) {
				
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 ****************************************/
					Integer ccmt_no = new Integer(req.getParameter("ccmt_no"));
					
					/*************************** 2.開始查詢資料 ****************************************/
					Course_CommentService ccmtSvc = new Course_CommentService();
					
					
					Set<Ccmt_ReportVO> set = ccmtSvc.getCcmtRptByCcmt(ccmt_no);
					
					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("listCcmtRptByCcmt", set);    // 資料庫取出的set物件,存入request

					String url = null;
					if ("listCcmtRptByCcmt_A".equals(action))
						url = "/course_comment/listlistCcmtRptByCcmt.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
					else if ("listCcmtRptByCcmt_B".equals(action))
						url = "/course_comment/listAllCourse_Comment.jsp";              // 成功轉交 dept/listAllDept.jsp

					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 ***********************************/
				} catch (Exception e) {
					throw new ServletException(e);
				}
			}
	
	
	
	
	
			
			//來自/course_comment/listAllCourse_Comment.jsp的請求
			if("delete_Course_Comment".equals(action)){
				
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs",errorMsgs);	 
				
			
			try{
				/***************************1.接收請求參數***************************************/
				
				Integer ccmt_no = new Integer(req.getParameter("ccmt_no"));
				
				
				/***************************2.開始刪除資料***************************************/
				
				Course_CommentService ccmtSvc = new Course_CommentService();
				ccmtSvc.deleteCourse_Comment(ccmt_no);
				
				
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			
				String url ="/course_comment/listAllCourse_Comment.jsp";
				// 刪除成功後, 成功轉交 回到 /course_comment/listAllCourse_Comment.jsp
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			
			}catch(Exception e){
				/***************************其他可能的錯誤處理***********************************/
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/course_comment/listAllCourse_Comment.jsp");
				failureView.forward(req, res);
			
			
			
			}
			
			
		
			}
	
	
			
			
			
			
			 if("getOne_From01".equals(action) || "getOne_From02".equals(action)){
				 
				 try{
					 //Retrieve form parameters.
					 Integer ccmt_no = new Integer(req.getParameter("ccmt_no"));
					 
				 
				Course_CommentDAO dao = new Course_CommentDAO();
					
				Course_CommentVO ccmtVO = dao.findByPrimaryKey(ccmt_no);
				 
				 req.setAttribute("ccmtVO",ccmtVO);//資料庫取出的empVO物件，存入req
				 
				 //取出的emp送給lostOneEmp.jsp
				 RequestDispatcher successView = req.getRequestDispatcher("/course_comment/listOneCourse_Comment.jsp");
				 
				 successView.forward(req,res);
				 return;
				 
				 }catch(Exception e){
					 
					 throw new ServletException(e);
				 }
				 
			 }
			 
			
			
			
			 if("insert".equals(action)){ //來自addCourse_Comment
					
					List<String> errorMsgs = new LinkedList<String>();
					//Store this set in the request scope, in case we need to
					//send the Errorpage view.
					
					req.setAttribute("errorMsgs", errorMsgs);
					
					
					try{
						
						/*****************1.接收請求參數 - 輸入格式的錯誤**************************/
						
						
						Integer crs_no = new Integer(req.getParameter("crs_no").trim());
						
						
						Integer mem_no = new Integer(req.getParameter("mem_no").trim());
						
						
					
						
						
						
						
						
						String ccmt_cont = req.getParameter("ccmt_cont");
						if(ccmt_cont == null || (ccmt_cont.trim()).length() == 0){
							
							 errorMsgs.add("請輸入文章內容");
						}
						
//						Send the use back to the form, if there were errors
						if(!errorMsgs.isEmpty()){
							RequestDispatcher failureView = req.
									getRequestDispatcher("/course_comment/listCcmt_ByCourse.jsp");
						
							failureView.forward(req, res);
							return;//程式中斷
						}	
						
						
						
//						String ccmt_status = req.getParameter("ccmt_status");
//						if (ccmt_status == null || (ccmt_status.trim()).length() == 0) {
//							errorMsgs.add("請輸入文章狀態");
//						}
//						// Send the use back to the form, if there were errors
//						if (!errorMsgs.isEmpty()) {
//							RequestDispatcher failureView = req
//									.getRequestDispatcher("/course_comment/addCourse_Comment.jsp");
//							failureView.forward(req, res);
//							return;//程式中斷
//						}
						
						
//						String ccmt_status = req.getParameter("ccmt_status").trim();
						String ccmt_status = null;
						
				
						
				
						
						
					
java.sql.Date ccmt_posttime;
						
						try{
							ccmt_posttime = java.sql.Date.valueOf(req
									.getParameter("ccmt_posttime").trim());
							
						}catch(IllegalArgumentException e){
							
							ccmt_posttime = new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入日期");
							
						}
						
						
						Course_CommentVO ccmtVO = new Course_CommentVO();
						ccmtVO.setCrs_no(crs_no);
						ccmtVO.setMem_no(mem_no);
						ccmtVO.setCcmt_cont(ccmt_cont);
						ccmtVO.setCcmt_status(ccmt_status);
						ccmtVO.setCcmt_posttime(ccmt_posttime);
						
						
						//Send the use back to the form,if there were errors
						if(!errorMsgs.isEmpty()){
							req.setAttribute("ccmtVO",ccmtVO);
							RequestDispatcher failureView = req
									.getRequestDispatcher("course_comment/listCcmt_ByCourse.jsp");
							failureView.forward(req, res);
						
						}				
						
						/*******************2.開始新增資料********************************/
						Course_CommentService ccmtSvc = new Course_CommentService();
						ccmtVO = ccmtSvc.addCourse_Comment(crs_no, mem_no, ccmt_cont, ccmt_status, ccmt_posttime);
						
							
						/*******************3.新增完成，準備轉交(Send the Success view)*************************************/	
							String url = "/course_comment/listOneCourse_Comment.jsp";
							//新增成功後轉交listAllCourse_Comment.jsp
							RequestDispatcher successView = req.getRequestDispatcher(url);
							successView.forward(req, res);
						
							
						/********************其他可能的錯誤處理****************************/	
					}catch(Exception e){
						
						e.printStackTrace();
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/course_comment/listCcmt_ByCourse.jsp");
						failureView.forward(req, res);															
					}
					
					
				}
	
	
			 if("getOne_insert".equals(action)){
					
					List<String> errorMsgs	= new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					
					String requestURL = req.getParameter("requestURL");
					
					
						try{
							/***************************1.接收請求參數****************************************/
								
							Integer ccmt_no = new Integer(req.getParameter("ccmt_no"));
							
							
							/***************************2.開始查詢資料****************************************/
							
							Course_CommentService ccmtSvc = new Course_CommentService();
							Course_CommentVO ccmtVO = ccmtSvc.getOneCourse_Comment(ccmt_no);
							
							/***************************3.查詢完成,準備轉交(Send the Success view)************/
							//資料庫取出的ccmtRptVO物件，存入req
							req.setAttribute("ccmtVO",ccmtVO);
							
							String url = "/ccmt_report/addCcmt_reportQuery.jsp";
//							String url = "/course_comment/addAllCourse_Comment.jsp";
							//成功轉交update_Ccmt_Report.jsp
							RequestDispatcher successView = req.getRequestDispatcher(url); 
							successView.forward(req, res);
						
						}catch(Exception e){
							/***************************其他可能的錯誤處理************************************/
							errorMsgs.add("修改資料取出時失敗:" +e.getMessage());
							RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
							failureView.forward(req, res);
							
						}
					
					
					
					}
			
			
			
			 
			 
			 
			 
			 if("submit_insert".equals(action)){ //來自addCourse_Comment
					
					List<String> errorMsgs = new LinkedList<String>();
					//Store this set in the request scope, in case we need to
					//send the Errorpage view.
					
					req.setAttribute("errorMsgs", errorMsgs);
					String requestURL = req.getParameter("requestURL"); 
					
					try{
						
						/*****************1.接收請求參數 - 輸入格式的錯誤**************************/
						
						
						Integer crs_no = new Integer(req.getParameter("crs_no").trim());
						
						
						Integer mem_no = new Integer(req.getParameter("mem_no").trim());
						
						
					
						
						
						
						
						
						String ccmt_cont = req.getParameter("ccmt_cont");
						if(ccmt_cont == null || (ccmt_cont.trim()).length() == 0){
							
							 errorMsgs.add("請輸入文章內容");
						}
						
//						Send the use back to the form, if there were errors
						if(!errorMsgs.isEmpty()){
							RequestDispatcher failureView = req.
									getRequestDispatcher("/course_comment/addAllCourse_Comment.jsp");
						
							failureView.forward(req, res);
							return;//程式中斷
						}	
						
						
						
//						String ccmt_status = req.getParameter("ccmt_status");
//						if (ccmt_status == null || (ccmt_status.trim()).length() == 0) {
//							errorMsgs.add("請輸入文章狀態");
//						}
//						// Send the use back to the form, if there were errors
//						if (!errorMsgs.isEmpty()) {
//							RequestDispatcher failureView = req
//									.getRequestDispatcher("/course_comment/addCourse_Comment.jsp");
//							failureView.forward(req, res);
//							return;//程式中斷
//						}
						
						
//						String ccmt_status = req.getParameter("ccmt_status").trim();
						String ccmt_status = null;
						
				
						
				
						
						
					
						java.sql.Date ccmt_posttime;
						
						try{
							ccmt_posttime = java.sql.Date.valueOf(req
									.getParameter("ccmt_posttime").trim());
							
						}catch(IllegalArgumentException e){
							
							ccmt_posttime = new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入日期");
							
						}
						
						
						Course_CommentVO ccmtVO = new Course_CommentVO();
						ccmtVO.setCrs_no(crs_no);
						ccmtVO.setMem_no(mem_no);
						ccmtVO.setCcmt_cont(ccmt_cont);
						ccmtVO.setCcmt_status(ccmt_status);
						ccmtVO.setCcmt_posttime(ccmt_posttime);
						
						
						//Send the use back to the form,if there were errors
						if(!errorMsgs.isEmpty()){
							req.setAttribute("ccmtVO",ccmtVO);
							RequestDispatcher failureView = req
									.getRequestDispatcher("/course_comment/addAllCourse_Comment.jsp");
							failureView.forward(req, res);
						
						}				
						
						/*******************2.開始新增資料********************************/
						Course_CommentService ccmtSvc = new Course_CommentService();
						ccmtVO = ccmtSvc.addCourse_Comment(crs_no, mem_no, ccmt_cont, ccmt_status, ccmt_posttime);
						
							
						/*******************3.新增完成，準備轉交(Send the Success view)*************************************/	
						CourseService courseSvc = new CourseService();
				if(requestURL.equals("/course_comment/listCcmt_ByCourse.jsp"))
		req.setAttribute("listCcmt_ByCourse",courseSvc.getCcmtByCourse(crs_no)); // 資料庫取出的list物件,存入request

			            String url = requestURL;
						RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
						successView.forward(req, res);
						 
						
							
						/********************其他可能的錯誤處理****************************/	
					}catch(Exception e){
						
						e.printStackTrace();
						errorMsgs.add("新增文章失敗：" +e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/course_comment/addAllCourse_Comment.jsp");
								failureView.forward(req, res);
																				
					}
					
					
				}
	
	
	
	
		
	
			 if("listCourse_Comment_ByCompositeQuery".equals(action)){
				 
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

				try{
					/******************1.將輸入資料轉為Map**********************/
					//採用Map<String,String[]> getParameterMap()的方法
					//注意:an immutable java.util.Map
					//Map<String, String[]> map = req.getParameterMap();
					
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				
					if(req.getParameter("whichPage") == null){
						HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
						HashMap<String, String[]> map2 = new HashMap<String, String[]>();
						map2 = (HashMap<String, String[]>)map1.clone();
						session.setAttribute("map",map2);
						map = (HashMap<String, String[]>)req.getParameterMap();
					}
				
					/***************************2.開始複合查詢***************************************/
					
					Course_CommentService ccmtSvc = new Course_CommentService();
					List<Course_CommentVO> list = ccmtSvc.getAll(map);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
						req.setAttribute("listCourse_Comment_ByCompositeQuery", list);
						
						RequestDispatcher successView = req.getRequestDispatcher("/course_comment/listCcmt_ByCourseQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
						successView.forward(req, res);
						
						
				}catch(Exception e){
					
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/course_comment/listCcmt_ByCourse.jsp");
					failureView.forward(req, res);
					
				}
				 
			 
			 
			 
			 
			 }
	
	
	
			 if("submitQuery_insert".equals(action)){ //來自addCourse_Comment
					
					List<String> errorMsgs = new LinkedList<String>();
					//Store this set in the request scope, in case we need to
					//send the Errorpage view.
					
					req.setAttribute("errorMsgs", errorMsgs);
					String requestURL = req.getParameter("requestURL"); 
					
					try{
						
						/*****************1.接收請求參數 - 輸入格式的錯誤**************************/
						
						
						Integer crs_no = new Integer(req.getParameter("crs_no").trim());
						
						
						Integer mem_no = new Integer(req.getParameter("mem_no").trim());
						
						
					
						
						
						
						
						
						String ccmt_cont = req.getParameter("ccmt_cont");
						if(ccmt_cont == null || (ccmt_cont.trim()).length() == 0){
							
							 errorMsgs.add("請輸入文章內容");
						}
						
//						Send the use back to the form, if there were errors
						if(!errorMsgs.isEmpty()){
							RequestDispatcher failureView = req.
									getRequestDispatcher("/course_comment/addCourse_Comment_Queryjsp.jsp");
						
							failureView.forward(req, res);
							return;//程式中斷
						}	
						
						
						
//						String ccmt_status = req.getParameter("ccmt_status");
//						if (ccmt_status == null || (ccmt_status.trim()).length() == 0) {
//							errorMsgs.add("請輸入文章狀態");
//						}
//						// Send the use back to the form, if there were errors
//						if (!errorMsgs.isEmpty()) {
//							RequestDispatcher failureView = req
//									.getRequestDispatcher("/course_comment/addCourse_Comment.jsp");
//							failureView.forward(req, res);
//							return;//程式中斷
//						}
						
						
//						String ccmt_status = req.getParameter("ccmt_status").trim();
						String ccmt_status = null;
						
				
						
				
						
						
					
						java.sql.Date ccmt_posttime;
						
						try{
							ccmt_posttime = java.sql.Date.valueOf(req
									.getParameter("ccmt_posttime").trim());
							
						}catch(IllegalArgumentException e){
							
							ccmt_posttime = new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入日期");
							
						}
						
						
						Course_CommentVO ccmtVO = new Course_CommentVO();
						ccmtVO.setCrs_no(crs_no);
						ccmtVO.setMem_no(mem_no);
						ccmtVO.setCcmt_cont(ccmt_cont);
						ccmtVO.setCcmt_status(ccmt_status);
						ccmtVO.setCcmt_posttime(ccmt_posttime);
						
						
						//Send the use back to the form,if there were errors
						if(!errorMsgs.isEmpty()){
							req.setAttribute("ccmtVO",ccmtVO);
							RequestDispatcher failureView = req
									.getRequestDispatcher("/course_comment/addCourse_Comment_Queryjsp.jsp");
							failureView.forward(req, res);
						
						}				
						
						/*******************2.開始新增資料********************************/
						Course_CommentService ccmtSvc = new Course_CommentService();
						ccmtVO = ccmtSvc.addCourse_Comment(crs_no, mem_no, ccmt_cont, ccmt_status, ccmt_posttime);
						
							
						/*******************3.新增完成，準備轉交(Send the Success view)*************************************/	
						CourseService courseSvc = new CourseService();
						
		 
						
						
						if(requestURL.equals("/course_comment/listCcmt_ByCourseQuery.jsp")){
							HttpSession session = req.getSession();
							Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
							List<Course_CommentVO> list  = ccmtSvc.getAll(map);
							req.setAttribute("listCourse_Comment_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
						}
						
						
						String url = requestURL;
						RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
						successView.forward(req, res);
						
						 
						
							
						/********************其他可能的錯誤處理****************************/	
					}catch(Exception e){
						
						e.printStackTrace();
						errorMsgs.add("新增文章失敗：" +e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/course_comment/addCourse_Comment_Queryjsp.jsp");
								failureView.forward(req, res);
																				
					}
					
					
				}
	
	
			 	
			 
			 if ("delete_ccmt".equals(action)) { // 來自/dept/listAllCourse_Comment.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
			
					try {
						/***************************1.接收請求參數***************************************/
						Integer ccmt_no = new Integer(req.getParameter("ccmt_no"));
						
						/***************************2.開始刪除資料***************************************/
						Course_CommentService ccmtSvc = new Course_CommentService();
						ccmtSvc.deleteCourse_Comment(ccmt_no);
						
						/***************************3.刪除完成,準備轉交(Send the Success view)***********/
						String url = "/ccmt_report/listAllCcmt_report.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllDept.jsp
						successView.forward(req, res);
						
						/***************************其他可能的錯誤處理***********************************/
					} catch (Exception e) {
						errorMsgs.add("刪除資料失敗:"+e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/ccmt_report/listAllCcmt_report.jsp");
						failureView.forward(req, res);
					}
				}
			 
	
	
	
	}

}
