package com.member.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.*;

public class MemberDBReader extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		
		
		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		 
		
		try {
			
			String mem_no = req.getParameter("mem_no");
			MemberService memberSvc = new MemberService();
			MemberVO memberVO	= memberSvc.getOnemember(new Integer(mem_no));
			byte[] buf = memberVO.getMem_photo();
			out.write(buf);
			
			
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("c:/NoData/10.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
			
			System.out.println(e);
		}
	}



	

}
