package com.course.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.course.model.CourseService;
import com.course.model.CourseVO;
import com.course_comment.model.Course_CommentVO;



@MultipartConfig(fileSizeThreshold = 10240 * 1024, maxFileSize = 50000 * 1024 * 1024, maxRequestSize = 5000 * 5 * 1024
		* 1024)
// ��ƾڶq�j��fileSizeThreshold�ȮɡA���e�N�Q�g�J�Ϻ�
// �W�ǹL�{���L�׬O��Ӥ��W�LmaxFileSize�ȡA�Ϊ̤W�Ǫ��`�q�j��maxRequestSize �ȳ��|�ߥXIllegalStateException
// ���`

public class CourseServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				String str = req.getParameter("crs_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ҵ{�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				System.out.println("cccccccccccccccccc");
				
				Integer crs_no = null;
				try {
					crs_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�ҵ{�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				CourseService courseSvc = new CourseService();
				CourseVO courseVO = courseSvc.getOneCourse(crs_no);
				if (courseVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 *************/
				req.setAttribute("courseVO", courseVO); // ��Ʈw���X��courseVO����,�s�Jreq
				String url = "/course/listOneCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// listOneCourse.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllCourse.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer crs_no = new Integer(req.getParameter("crs_no"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				CourseService courseSvc = new CourseService();
				CourseVO courseVO = courseSvc.getOneCourse(crs_no);

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("courseVO", courseVO); // ��Ʈw���X��lessonVO����,�s�Jreq
				String url = "/course/update_course_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
																				// update_film_course.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/course/listAllCourse.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // �Ӧ�update_film_input.jsp���ШD
			
			Collection<Part> parts = req.getParts(); // Servlet3.0�s�W�FPart�����A���ڭ̤�K���i���ɮפW�ǳB�z
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				Integer crs_no = new Integer(req.getParameter("crs_no").trim());
				Integer ach_no = new Integer(req.getParameter("ach_no").trim());
				Integer mem_no = 11000001;
				// Integer mem_no = new
				// Integer(req.getParameter("mem_no").trim());
				Integer cat_no = new Integer(req.getParameter("cat_no").trim());
				// get crs_teacher �Ѽ�
				String crs_teacher = req.getParameter("crs_teacher").trim();
				if ((crs_teacher == null) || (crs_teacher.equals(""))) {
					errorMsgs.add("�п�J�Ѯv²��");
				}

				// get crs_name �Ѽ�
				String crs_name = req.getParameter("crs_name").trim();
				if ((crs_name == null) || (crs_name.equals(""))) {
					errorMsgs.add("�п�J�ҵ{�W��");
				}

				// get crs_info �Ѽ�
				String crs_info = req.getParameter("crs_info").trim();
				if ((crs_info == null) || (crs_info.equals(""))) {
					errorMsgs.add("�п�J�ҵ{²��");
				}

				// get crs_cont �Ѽ�
				String crs_cont = req.getParameter("crs_cont").trim();
				if ((crs_cont == null) || (crs_cont.equals(""))) {
					errorMsgs.add("�п�J�ҵ{���e");
				}

				// get crs_fr_target �Ѽ�
				Integer crs_fr_target = 0;
				String crs_fr_target_str = req.getParameter("crs_fr_target");
				if (crs_fr_target_str == null || ((crs_fr_target_str.trim()).length() == 0) || crs_fr_target_str.equals("0")) {
					errorMsgs.add("�п�J�ؼоǥͼ�");
				} else {
					try {
						crs_fr_target = new Integer(crs_fr_target_str);
					} catch (Exception e) {
						errorMsgs.add("�ؼоǥͼƮ榡�����T");
					}

				}
				// get crs_fr_str �Ѽ�
				java.sql.Date crs_fr_str = java.sql.Date.valueOf(req.getParameter("crs_fr_str").trim());
	

				// get crs_fr_fin �Ѽ�
				java.sql.Date crs_fr_fin = java.sql.Date.valueOf(req.getParameter("crs_fr_fin").trim());
	

				// get crs_fr_num �Ѽ�
				Integer crs_fr_num = new Integer(req.getParameter("crs_fr_num"));
		

				// get crs_price �Ѽ�
				Integer crs_price = 0;
				String crs_price_str = req.getParameter("crs_price");
				if (crs_price_str == null || ((crs_price_str.trim()).length() == 0) || crs_price_str.equals("0")) {
					errorMsgs.add("�п�J�ҵ{����");
				} else {
					try {
						crs_price = new Integer(crs_price_str);
					} catch (Exception e) {
						errorMsgs.add("�ҵ{�����榡�����T");
					}
				}

				// get crs_status �Ѽ�
				String crs_status = req.getParameter("crs_status").trim();


				// get crs_create �Ѽ�
				java.sql.Date crs_create = java.sql.Date.valueOf(req.getParameter("crs_create").trim());;
		

				// get crs_stand �Ѽ�
				String crs_stand = req.getParameter("crs_stand").trim();

				// get crs_ppl �Ѽ�
				Integer crs_ppl = new Integer(req.getParameter("crs_ppl"));
		

				// get crs_time �Ѽ�
				String crs_time = req.getParameter("crs_time").trim();
				if ((crs_time == null) || (crs_time.equals("")) || crs_time.equals("0")) {
					crs_time = "0";
					errorMsgs.add("�п�J�ҵ{�ɶ�");
				}

				// crs_image0�Bcrs_image1�Bcrs_image2�Bcrs_introvideo�Ѽ�
				byte[] crs_image0 = null;
				byte[] crs_image1 = null;
				byte[] crs_image2 = null;
				byte[] crs_introvideo = null;
				int count = 0;
				// �����e�ݺ����e�Ӫ��v���ɮ�
				for (Part part : parts) {
					count++;
					if (count == 7) {
						InputStream in = part.getInputStream();
						crs_image0 = new byte[in.available()];
						in.read(crs_image0);
						in.close();
						if (crs_image0.length == 0) {
							CourseService courseSvc = new CourseService();
							CourseVO getimage0CourseVO = courseSvc.getOneCourse(new Integer(crs_no));
							crs_image0 = getimage0CourseVO.getCrs_image0();
						}
					}
					if (count == 8) {
						InputStream in = part.getInputStream();
						crs_image1 = new byte[in.available()];
						in.read(crs_image1);
						in.close();
						if (crs_image1.length == 0) {
							CourseService courseSvc = new CourseService();
							CourseVO getimage1CourseVO = courseSvc.getOneCourse(new Integer(crs_no));
							crs_image1 = getimage1CourseVO.getCrs_image1();
						}
					}
					if (count == 9) {
						InputStream in = part.getInputStream();
						crs_image2 = new byte[in.available()];
						in.read(crs_image2);
						in.close();
						if (crs_image2.length == 0) {
							CourseService courseSvc = new CourseService();
							CourseVO getimage2CourseVO = courseSvc.getOneCourse(new Integer(crs_no));
							crs_image2 = getimage2CourseVO.getCrs_image2();
						}
					}
					if (count == 10) {
						InputStream in = part.getInputStream();
						crs_introvideo = new byte[in.available()];
						in.read(crs_introvideo);
						in.close();
						if (crs_introvideo.length == 0) {
							CourseService courseSvc = new CourseService();
							CourseVO getintrovideoCourseVO = courseSvc.getOneCourse(new Integer(crs_no));
							crs_introvideo = getintrovideoCourseVO.getCrs_introvideo();
						}
					}
				}
				
				CourseVO courseVO = new CourseVO();
				courseVO.setCrs_no(crs_no);
				courseVO.setAch_no(ach_no);
				courseVO.setMem_no(mem_no);
				courseVO.setCat_no(cat_no);
				courseVO.setCrs_teacher(crs_teacher);
				courseVO.setCrs_name(crs_name);
				courseVO.setCrs_info(crs_info);
				courseVO.setCrs_cont(crs_cont);
				courseVO.setCrs_image0(crs_image0);
				courseVO.setCrs_image1(crs_image1);
				courseVO.setCrs_image2(crs_image2);
				courseVO.setCrs_introvideo(crs_introvideo);
				courseVO.setCrs_fr_target(crs_fr_target);
				courseVO.setCrs_fr_str(crs_fr_str);
				courseVO.setCrs_fr_fin(crs_fr_fin);
				courseVO.setCrs_fr_num(crs_fr_num);
				courseVO.setCrs_price(crs_price);
				courseVO.setCrs_status(crs_status);
				courseVO.setCrs_create(crs_create);
				courseVO.setCrs_stand(crs_stand);
				courseVO.setCrs_ppl(crs_ppl);
				courseVO.setCrs_time(crs_time);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("courseVO", courseVO); // �t����J�榡���~��courseVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/course/update_course_input.jsp");
					failureView.forward(req, res);
					return;
				}

				
				
				/***************************2.�}�l�ק���*****************************************/
				CourseService courseSvc = new CourseService();
				courseVO = courseSvc.updateCourse(crs_no,ach_no, mem_no, cat_no, crs_teacher, crs_name, crs_info, crs_cont,
						crs_image0, crs_image1, crs_image2, crs_introvideo, crs_fr_target, crs_fr_str, crs_fr_fin,
						crs_fr_num, crs_price, crs_status, crs_create, crs_stand, crs_ppl, crs_time);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("courseVO", courseVO); // ��Ʈwupdate���\��,���T����courseVO����,�s�Jreq
				String url = "/course/listOneCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneCourse.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/course/update_course_input.jsp");
				failureView.forward(req, res);
			}
		}

		

		if ("insert".equals(action)) { // �Ӧ�addCourse.jsp���ШD

			Collection<Part> parts = req.getParts(); // Servlet3.0�s�W�FPart�����A���ڭ̤�K���i���ɮפW�ǳB�z
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/

				Integer ach_no = new Integer(req.getParameter("ach_no").trim());
				Integer mem_no = 11000001;
				// Integer mem_no = new
				// Integer(req.getParameter("mem_no").trim());
				Integer cat_no = new Integer(req.getParameter("cat_no").trim());
				
				// get crs_teacher �Ѽ�
				String crs_teacher = req.getParameter("crs_teacher").trim();
				if ((crs_teacher == null) || (crs_teacher.equals(""))) {
					errorMsgs.add("�п�J�Ѯv²��");
				}

				// get crs_name �Ѽ�
				String crs_name = req.getParameter("crs_name").trim();
				if ((crs_name == null) || (crs_name.equals(""))) {
					errorMsgs.add("�п�J�ҵ{�W��");
				}

				// get crs_info �Ѽ�
				String crs_info = req.getParameter("crs_info").trim();
				if ((crs_info == null) || (crs_info.equals(""))) {
					errorMsgs.add("�п�J�ҵ{²��");
				}

				// get crs_cont �Ѽ�
				String crs_cont = req.getParameter("crs_cont").trim();
				if ((crs_cont == null) || (crs_cont.equals(""))) {
					errorMsgs.add("�п�J�ҵ{���e");
				}
				
				// crs_image0�Bcrs_image1�Bcrs_image2�Bcrs_introvideo�Ѽ�
				byte[] crs_image0 = null;
				byte[] crs_image1 = null;
				byte[] crs_image2 = null;
				byte[] crs_introvideo = null;
				int count = 0;
				// �B�~���� InputStream �P byte[] (���N��model��VO�w�@�ǳ�)
				for (Part part : parts) {
					count++;
					if (count == 7) {
						InputStream in = part.getInputStream();
						crs_image0 = new byte[in.available()];
						in.read(crs_image0);
						in.close();
						if (crs_image0.length == 0) {
							errorMsgs.add("�п�ܽҵ{�Ϥ�");
						}
					}
					if (count == 8) {
						InputStream in = part.getInputStream();
						crs_image1 = new byte[in.available()];
						in.read(crs_image1);
						in.close();
					}
					if (count == 9) {
						InputStream in = part.getInputStream();
						crs_image2 = new byte[in.available()];
						in.read(crs_image2);
						in.close();
					}
					if (count == 10) {
						InputStream in = part.getInputStream();
						crs_introvideo = new byte[in.available()];
						in.read(crs_introvideo);
						in.close();
						if (crs_introvideo.length == 0) {
							errorMsgs.add("�п�ܽҵ{���мv��");
						}
					}
				}

				// get crs_fr_target �Ѽ�
				Integer crs_fr_target = 0;
				String crs_fr_target_str = req.getParameter("crs_fr_target");
				if (crs_fr_target_str == null || (crs_fr_target_str.trim()).length() == 0 || crs_fr_target_str.equals("0")) {
					errorMsgs.add("�п�J�ؼоǥͼ�");
				} else {
					try {
						crs_fr_target = new Integer(crs_fr_target_str);
					} catch (Exception e) {
						errorMsgs.add("�ؼоǥͼƮ榡�����T");
					}

				}
				// get crs_fr_str �Ѽ�
				java.sql.Date crs_fr_str = null;
				try {
					crs_fr_str = java.sql.Date.valueOf(req.getParameter("crs_fr_str").trim());
				} catch (IllegalArgumentException e) {
					crs_fr_str = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�Ҹ�o�_����!");
				}

				// get crs_fr_fin �Ѽ�
				java.sql.Date crs_fr_fin = null;
				try {
					crs_fr_fin = java.sql.Date.valueOf(req.getParameter("crs_fr_fin").trim());
				} catch (IllegalArgumentException e) {
					crs_fr_fin = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�Ҹ굲�����!");
				}

				// get crs_fr_num �Ѽ�
				Integer crs_fr_num = new Integer(req.getParameter("crs_fr_num"));
		

				// get crs_price �Ѽ�
				Integer crs_price = 0;
				String crs_price_str = req.getParameter("crs_price");
				if (crs_price_str == null || (crs_price_str.trim()).length() == 0 || crs_price_str.equals("0")) {
					errorMsgs.add("�п�J�ҵ{����");
				} else {
					try {
						crs_price = new Integer(crs_price_str);
					} catch (Exception e) {
						errorMsgs.add("�ҵ{�����榡�����T");
					}
				}

				// get crs_status �Ѽ�
				String crs_status = req.getParameter("crs_status").trim();


				// get crs_create �Ѽ�
				java.sql.Date crs_create = null;
				try {
					crs_create = java.sql.Date.valueOf(req.getParameter("crs_create").trim());
				} catch (IllegalArgumentException e) {
					crs_create = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�ҵ{�إߤ��!");
				}

				// get crs_stand �Ѽ�			
				String crs_stand = req.getParameter("crs_stand");


				// get crs_ppl �Ѽ�
				Integer crs_ppl = new Integer(req.getParameter("crs_ppl"));
		

				// get crs_time �Ѽ�
				String crs_time = req.getParameter("crs_time").trim();
				if ((crs_time == null) || (crs_time.equals("") || crs_time.equals("0"))) {
					crs_time = "0";
					errorMsgs.add("�п�J�ҵ{�ɶ�");
				}

				

				CourseVO courseVO = new CourseVO();
				courseVO.setAch_no(ach_no);
				courseVO.setMem_no(mem_no);
				courseVO.setCat_no(cat_no);
				courseVO.setCrs_teacher(crs_teacher);
				courseVO.setCrs_name(crs_name);
				courseVO.setCrs_info(crs_info);
				courseVO.setCrs_cont(crs_cont);
				courseVO.setCrs_image0(crs_image0);
				courseVO.setCrs_image1(crs_image1);
				courseVO.setCrs_image2(crs_image2);
				courseVO.setCrs_introvideo(crs_introvideo);
				courseVO.setCrs_fr_target(crs_fr_target);
				courseVO.setCrs_fr_str(crs_fr_str);
				courseVO.setCrs_fr_fin(crs_fr_fin);
				courseVO.setCrs_fr_num(crs_fr_num);
				courseVO.setCrs_price(crs_price);
				courseVO.setCrs_status(crs_status);
				courseVO.setCrs_create(crs_create);
				courseVO.setCrs_stand(crs_stand);
				courseVO.setCrs_ppl(crs_ppl);
				courseVO.setCrs_time(crs_time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("courseVO", courseVO); // �t����J�榡���~��courseVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/course/addCourse.jsp");
					failureView.forward(req, res);
					return;
				}

	

				/*************************** 2.�}�l�s�W��� ***************************************/
				CourseService courseSvc = new CourseService();
				courseVO = courseSvc.addCourse(ach_no, mem_no, cat_no, crs_teacher, crs_name, crs_info, crs_cont,
						crs_image0, crs_image1, crs_image2, crs_introvideo, crs_fr_target, crs_fr_str, crs_fr_fin,
						crs_fr_num, crs_price, crs_status, crs_create, crs_stand, crs_ppl, crs_time);

				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				String url = "/course/listAllCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllCourse.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/course/addCourse.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer crs_no = new Integer(req.getParameter("crs_no"));

				/*************************** 2.�}�l�R����� ***************************************/
				CourseService courseSvc = new CourseService();
				courseSvc.deleteCourse(crs_no);

				/***************************
				 * 3.�R������,�ǳ����(Send the Success view)
				 ***********/
				String url = "/course/listAllCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/course/listAllCourse.jsp");
				failureView.forward(req, res);
			}
		}

	
	
	
		if ("listCcmt_ByCourse_A".equals(action) || "listCcmt_ByCourse_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer crs_no = new Integer(req.getParameter("crs_no"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				CourseService courseSvc = new CourseService();
				Set<Course_CommentVO> set = courseSvc.getCcmtByCourse(crs_no);
				

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listCcmt_ByCourse", set);    // ��Ʈw���X��set����,�s�Jrequest

				String url = null;
				if ("listCcmt_ByCourse_A".equals(action))
					url = "/course_comment/listCcmt_ByCourse.jsp";        // ���\��� dept/listEmps_ByDeptno.jsp
				else if ("listCcmt_ByCourse_B".equals(action))
					url = "/course_comment/listCcmt_ByCourse.jsp";              // ���\��� dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
				
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
	
	
		
		
	
	
	
	
	
	
	
	
	
	
	}

}
