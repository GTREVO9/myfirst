package com.ccmt_report.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.ccmt_report.model.*;
import com.course.model.CourseService;
import com.course_comment.model.Course_CommentService;
import com.course_comment.model.Course_CommentVO;




public class Ccmt_ReportServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Ccmt_Report".equals(action)) { 
		
		
		
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
		
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("crep_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_ccmt_report.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer crep_no = null;
				try {
					crep_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_ccmt_report.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Ccmt_ReportService ccmtRptSvc = new Ccmt_ReportService();
				Ccmt_ReportVO ccmtRptVO = ccmtRptSvc.getOneCcmt_Report(crep_no);
				if (ccmtRptVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_ccmt_report.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("ccmtRptVO", ccmtRptVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/ccmt_report/listOneCcmt_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_ccmt_report.jsp");
				failureView.forward(req, res);
			}
				
		}
		
		
	
	
	//�ק�Ӧ�/course_comment/listlistCcmtRptByCcmt.jsp�ШD
		if("getOne_Update".equals(action)){
			
			List<String> errorMsgs	= new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			
				try{
					/***************************1.�����ШD�Ѽ�****************************************/
						
					Integer crep_no = new Integer(req.getParameter("crep_no"));
					
					
					/***************************2.�}�l�d�߸��****************************************/
					
					Ccmt_ReportService ccmtRptSvc = new Ccmt_ReportService();
					Ccmt_ReportVO ccmtRptVO = ccmtRptSvc.getOneCcmt_Report(crep_no);
					
					/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
					//��Ʈw���X��ccmtRptVO����A�s�Jreq
					req.setAttribute("ccmtRptVO",ccmtRptVO);
					
					String url = "/ccmt_report/update_Ccmt_Report.jsp";
					
					//���\���update_Ccmt_Report.jsp
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
				
				}catch(Exception e){
					/***************************��L�i�઺���~�B�z************************************/
					errorMsgs.add("�ק��ƨ��X�ɥ���:" +e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					
				}
			
			
			
			}
	
	
	
	
	//�Ӧ�update_Ccmt_Report.jsp���ШD
	if("submit_Update".equals(action)){
		
		List<String> errorMsgs = new LinkedList<String>();
		
		req.setAttribute("errorMsgs", errorMsgs);
		
		String requestURL = req.getParameter("requestURL"); 
		
		try{
			/********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*******************/
			Integer crep_no = new Integer(req.getParameter("crep_no").trim());
		
			Integer ccmt_no = new Integer(req.getParameter("ccmt_no"));
			
			
			  
			Integer mem_no = new Integer(req.getParameter("mem_no"));
			
			
			
			
			
			String crep_title = req.getParameter("crep_title");
			if(crep_title == null || (crep_title.trim().length() == 0)){
				
				errorMsgs.add("�п�J�峹���D");
			}
			
			
			

			String crep_cnt = req.getParameter("crep_cnt");
			if(crep_cnt == null || (crep_cnt.trim().length() == 0)){
				
				errorMsgs.add("�п�J�峹���e");
			}
			
			
			
			String crep_status = req.getParameter("crep_status");
			if(crep_status == null || (crep_status.trim().length() == 0)){
				
				errorMsgs.add("�п�J�B�z���A");
			}
			
			
			Integer adm_no = new Integer(req.getParameter("adm_no"));
			
			
			String crep_result = req.getParameter("crep_result");
			if(crep_result == null || (crep_result.trim().length() == 0)){
				
				errorMsgs.add("�п�J�B�z���i");
			}
			
			
			System.out.println("BBBBBBB");
			
			
			
			
			
			
			Ccmt_ReportVO ccmtRptVO = new Ccmt_ReportVO();
			ccmtRptVO.setCrep_no(crep_no);
			ccmtRptVO.setCcmt_no(ccmt_no);
			ccmtRptVO.setMem_no(mem_no);
			ccmtRptVO.setCrep_title(crep_title);
			ccmtRptVO.setCrep_cnt(crep_cnt);
			ccmtRptVO.setCrep_status(crep_status);
			ccmtRptVO.setAdm_no(adm_no);
			ccmtRptVO.setCrep_result(crep_result);	
			
		
			//Send the use back to the form, if there were errors
			if(!errorMsgs.isEmpty()){		
				req.setAttribute("ccmtRptVO", ccmtRptVO);
				RequestDispatcher failureView = req.
						getRequestDispatcher("/ccmt_report/update_Ccmt_Report.jsp");
			
				failureView.forward(req, res);
				return;//�{�����_
			}	
			
			
			/*******************2.�}�l�ק�峹***************************/
			
			Ccmt_ReportService ccmtRptSvc = new Ccmt_ReportService();
			ccmtRptVO = ccmtRptSvc.updateCcmt_Report(crep_no,ccmt_no,mem_no,crep_title,
					 crep_cnt,crep_status,adm_no,crep_result);
		
		
			/********************3.�ק粒���A�ǳ����(Send the Success view)****************************************/

			Course_CommentService ccmtSvc = new Course_CommentService();
			if(requestURL.equals("/course_comment/listlistCcmtRptByCcmt.jsp"))
				req.setAttribute("listCcmtRptByCcmt",ccmtSvc.getCcmtRptByCcmt(ccmt_no)); // ��Ʈw���X��list����,�s�Jrequest

            String url = requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(url);   // �ק令�\��,���^�e�X�ק諸�ӷ�����
			successView.forward(req, res);
			 
			

//			req.setAttribute("ccmtRptVO",ccmtRptVO);
//			String url = "/ccmt_report/listOneCcmt_report.jsp";
			// �ק令�\��,���listOneCourse_Comment.jsp
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
//			
		
		}catch(Exception e){
			/**************��L�i�઺���~�B�z*************************/
			
			errorMsgs.add("�ק�峹���ѡG" +e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/ccmt_report/update_Ccmt_Report.jsp");
					failureView.forward(req, res);
			
			
		}
	

	}
	
	
	
	
	
	//�Ӧ�/course_comment/listlistCcmtRptByCcmt.jsp���ШD
	if("delete".equals(action)){
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
		//�e�X�R�����ӷ��������|:�i�ର�i/ccmt_report/listAllCcmt_report.jsp�j ��  �i/course_comment/listlistCcmtRptByCcmt.jsp�j �� �i /course_comment/listAllCourse_Comment.jsp�j
		String requestURL = req.getParameter("requestURL");
	
	try{
		/***************************1.�����ШD�Ѽ�***************************************/
		Integer crep_no = new Integer(req.getParameter("crep_no"));
		
		
		/***************************2.�}�l�R�����***************************************/
		

		Ccmt_ReportService ccmtRptSvc = new Ccmt_ReportService();
		Ccmt_ReportVO ccmtRptVO = ccmtRptSvc.getOneCcmt_Report(crep_no);
		
		ccmtRptSvc.deleteCcmt_Report(crep_no);
		
		/***************************3.�R������,�ǳ����(Send the Success view)***********/
		Course_CommentService ccmtSvc= new Course_CommentService();
		
		if(requestURL.equals("/course_comment/listlistCcmtRptByCcmt.jsp") || 
					requestURL.equals("/course_comment/listAllCourse_Comment.jsp"))
			// ��Ʈw���X��list����,�s�Jrequest
			req.setAttribute("listCcmtRptByCcmt",ccmtSvc.getCcmtRptByCcmt(ccmtRptVO.getCcmt_no()));
		
		String url = requestURL;
		
		System.out.println(url);
		
		// �R�����\��,���^�e�X�R�����ӷ�����
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
		
	}catch(Exception e){
		
		errorMsgs.add("�R����ƥ���:"+e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher(requestURL);
		failureView.forward(req, res);	
	}
		
	
	
	
	}
	
	
	if ("getOne_ReportInsert".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp ���ШD

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
		String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j		
		
		try {
			/***************************1.�����ШD�Ѽ�****************************************/
			Integer crep_no = new Integer(req.getParameter("crep_no"));
			
			/***************************2.�}�l�d�߸��****************************************/
			Ccmt_ReportService ccmRptSvc = new Ccmt_ReportService();
			Ccmt_ReportVO ccmtRptVO = ccmRptSvc.getOneCcmt_Report(crep_no);
							
			/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
			req.setAttribute("ccmtRptVO", ccmtRptVO); // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/ccmt_report/addCcmt_reportQuery.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���update_emp_input.jsp
			successView.forward(req, res);

			/***************************��L�i�઺���~�B�z************************************/
		} catch (Exception e) {
			errorMsgs.add("�s�W��ƨ��X�ɥ���:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher(requestURL);
			failureView.forward(req, res);
		}
	}
	
	
	
	
	

	
	if("submitCcmtRpt_insert".equals(action)){ //�Ӧ�addCourse_Comment
		 List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); 
			
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				Integer ccmt_no = new Integer(req.getParameter("ccmt_no").trim());
				
				
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				
				String crep_title = req.getParameter("crep_title");
				if(crep_title == null || (crep_title.trim()).length() == 0){
					
					 errorMsgs.add("�п�J���|���D");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.
							getRequestDispatcher("/ccmt_report/addCcmt_report.jsp");
				
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				
				
				String crep_cnt = req.getParameter("crep_cnt");
				if(crep_cnt == null || (crep_cnt.trim()).length() == 0){
					
					 errorMsgs.add("�п�J���A");
				}
				
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.
							getRequestDispatcher("/ccmt_report/addCcmt_report.jsp");
				
					failureView.forward(req, res);
					return;//�{�����_
				}
				
//				Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.
							getRequestDispatcher("/ccmt_report/addCcmt_report.jsp");
				
					failureView.forward(req, res);
					return;//�{�����_
				}	
				
				
				
				
				
				
				String crep_status = "0";
			
				Integer adm_no = 13000003;
				String crep_result = null;
				
				
				
				
				Ccmt_ReportVO ccmtRptVO = new Ccmt_ReportVO();
				
				ccmtRptVO.setCcmt_no(ccmt_no);
				ccmtRptVO.setMem_no(mem_no);
				ccmtRptVO.setCrep_title(crep_title);
				ccmtRptVO.setCrep_cnt(crep_cnt);
				ccmtRptVO.setCrep_status(crep_status);
				ccmtRptVO.setAdm_no(adm_no);
				ccmtRptVO.setCrep_result(crep_result);
			
				
				//Send the use back to the form,if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("ccmtRptVO",ccmtRptVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ccmt_report/addCcmt_report.jsp");
					failureView.forward(req, res);
				
				}				
				System.out.println("bbbbbbbbbbbbbbbb");
				/*******************2.�}�l�s�W���********************************/
				
				Ccmt_ReportService ccmtRptSvc = new Ccmt_ReportService();
				ccmtRptVO = ccmtRptSvc.addCcmt_Report(ccmt_no, mem_no, crep_title, crep_cnt, crep_status, adm_no, crep_result);
				
				System.out.println("ccccccccc");
				/*******************3.�s�W�����A�ǳ����(Send the Success view)*************************************/	
				

				Course_CommentService ccmtSvc = new Course_CommentService();
//				if(requestURL.equals("/course_comment/listlistCcmtRptByCcmt.jsp"))
//					req.setAttribute("listCcmtRptByCcmt",ccmtSvc.getCcmtRptByCcmt(ccmt_no)); // ��Ʈw���X��list����,�s�Jrequest

				
				
				if(requestURL.equals("/course_comment/listCcmt_ByCourseQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<Course_CommentVO> list  = ccmtSvc.getAll(map);
					req.setAttribute("listCourse_Comment_ByCompositeQuery",list); //  �ƦX�d��, ��Ʈw���X��list����,�s�Jrequest
				}
				System.out.println("ddddddddddd");
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // �ק令�\��,���^�e�X�ק諸�ӷ�����
				successView.forward(req, res);
				
				 
				
					
				/********************��L�i�઺���~�B�z****************************/	
			}catch(Exception e){
				
				e.printStackTrace();
				errorMsgs.add("�s�W���|���ѡG" +e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ccmt_report/addCcmt_reportQuery.jsp");
						failureView.forward(req, res);
																		
			}
			
			
		}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 if ("insert".equals(action)) { 
			
		 List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); 
			
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				Integer ccmt_no = new Integer(req.getParameter("ccmt_no").trim());
				
				
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				
				String crep_title = req.getParameter("crep_title");
				if(crep_title == null || (crep_title.trim()).length() == 0){
					
					 errorMsgs.add("�п�J���|���D");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.
							getRequestDispatcher("/ccmt_report/addCcmt_report.jsp");
				
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				
				
				String crep_cnt = req.getParameter("crep_cnt");
				if(crep_cnt == null || (crep_cnt.trim()).length() == 0){
					
					 errorMsgs.add("�п�J���A");
				}
				
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.
							getRequestDispatcher("/ccmt_report/addCcmt_report.jsp");
				
					failureView.forward(req, res);
					return;//�{�����_
				}
				
//				Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.
							getRequestDispatcher("/ccmt_report/addCcmt_report.jsp");
				
					failureView.forward(req, res);
					return;//�{�����_
				}	
				
				
				
				
				
				
				String crep_status = req.getParameter("crep_status");
				if(crep_status == null || (crep_status.trim()).length() == 0){
					
					 errorMsgs.add("�п�J���|���D");
				}
				
				Integer adm_no = new Integer(req.getParameter("adm_no").trim());
				String crep_result = null;
				
				
				
				
				Ccmt_ReportVO ccmtRptVO = new Ccmt_ReportVO();
				
				ccmtRptVO.setCcmt_no(ccmt_no);
				ccmtRptVO.setMem_no(mem_no);
				ccmtRptVO.setCrep_title(crep_title);
				ccmtRptVO.setCrep_cnt(crep_cnt);
				ccmtRptVO.setCrep_status(crep_status);
				ccmtRptVO.setAdm_no(adm_no);
				ccmtRptVO.setCrep_result(crep_result);
			
				
				//Send the use back to the form,if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("ccmtRptVO",ccmtRptVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ccmt_report/addCcmt_report.jsp");
					failureView.forward(req, res);
				
				}				
				
				/***************************2.�}�l�s�W���***************************************/
				Ccmt_ReportService ccmtRptSvc = new Ccmt_ReportService();
				
				ccmtRptVO = ccmtRptSvc.addCcmt_Report(ccmt_no, mem_no, crep_title, crep_cnt, crep_status, adm_no, crep_result);
				
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/ccmt_report/listAllCcmt_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ccmt_report/addCcmt_report.jsp");
				failureView.forward(req, res);
			}
		}
	 
	 
	 
	 
	 
	 
	 

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}





}
