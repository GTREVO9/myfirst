<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

<%

Integer wis_no = new Integer(request.getParameter("wis_no"));
Integer mem_no = new Integer(request.getParameter("mem_no"));
System.out.println(wis_no+ ", "+ mem_no);

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try{
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","FORWU", "jj123");
	pstmt = conn.prepareStatement("SELECT * FROM wish_collection WHERE mem_no=? AND wis_no=?");
	pstmt.setInt(1, mem_no);
	pstmt.setInt(2, wis_no);
	rs = pstmt.executeQuery();
	
	
	if(rs.next()){
		out.print(1);
	}else{
		pstmt.clearParameters();
		pstmt = conn.prepareStatement("UPDATE wish SET wis_like = wis_like +1 where wis_no = ?");
	 	pstmt.setInt(1, wis_no);
		int n = pstmt.executeUpdate();
System.out.println(n);
		pstmt.clearParameters();
		pstmt = conn.prepareStatement("INSERT INTO wish_collection (mem_no, wis_no) values (?, ?)");
		pstmt.setInt(1, mem_no);
		pstmt.setInt(2, wis_no);		
		int nn = pstmt.executeUpdate();
System.out.println(nn);
	 	out.print(0);
	 	pstmt.close();
	}

	
	
}catch(ClassNotFoundException e){
	e.printStackTrace();
	throw new RuntimeException(e.getMessage());
}catch(SQLException se){
	se.printStackTrace();
	throw new RuntimeException(se.getMessage());
}finally{
	if(rs != null){
		try{
			rs.close();
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
	if(pstmt != null){
		try{
			pstmt.close();
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
	if(conn != null){
		try{
			conn.close();
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
}

%>