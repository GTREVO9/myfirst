package com.course.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.course.model.CourseService;
import com.course.model.CourseVO;

public class CoursePhoto2 extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

			try {
				String crs_no=req.getParameter("crs_no");
				CourseService courseSve = new CourseService();
				CourseVO courseVO = courseSve.getOneCourse(new Integer(crs_no));
				byte[] buf = courseVO.getCrs_image2();
				out.write(buf);
	
			} catch (Exception e) {
				InputStream in = getServletContext().getResourceAsStream("/NoData/nopic.jpg");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}

	}
}
