package com.ccmt_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class Ccmt_ReportDAO implements Ccmt_ReportDAO_interface{

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
			private static DataSource ds = null;
			static {
				try {
					Context ctx = new InitialContext();
					ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
			
			
			
			private static final String GET_ONE_STMT =
					"SELECT crep_no, ccmt_no, mem_no, crep_title, crep_cnt, crep_status, adm_no, crep_result FROM ccmt_report where crep_no = ?";
					
			private static final String GET_ALL_STMT =
					"SELECT crep_no, ccmt_no, mem_no, crep_title, crep_cnt, crep_status, adm_no, crep_result FROM ccmt_report order by crep_no";
			
			private static final String INSERT_STMT =
					"INSERT INTO ccmt_report (crep_no, ccmt_no, mem_no, crep_title, crep_cnt, crep_status, adm_no, crep_result) VALUES (ccmt_report_seq.NEXTVAL, ?, ?, ?, ?, ?,?,?)";			
			
			private static final String UPDATE = 
					"UPDATE ccmt_report set ccmt_no=?, mem_no=?,crep_title=?,crep_cnt=?,crep_status=?,adm_no=?,crep_result=? where crep_no = ?";
					
//			private static final String DELETE =
//					"DELETE FROM ccmt_report where crep_no = ?";
			
			//先刪FK討論區檢舉
			private static final String  DELETE_Reports = "DELETE FROM ccmt_report where crep_no  = ?";
			
			//再刪PK討論區文章
			private static final String DELETE_Comment = "DELETE FROM course_comment where ccmt_no = ?";
	
	
	
	
	
	
	@Override
	public void insert(Ccmt_ReportVO ccmtRptVO) {
		// TODO Auto-generated method stub
		
	

		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);		
			pstmt.setInt(1, ccmtRptVO.getCcmt_no());
			pstmt.setInt(2, ccmtRptVO.getMem_no());
			pstmt.setString(3, ccmtRptVO.getCrep_title());
			pstmt.setString(4, ccmtRptVO.getCrep_cnt());
			pstmt.setString(5, ccmtRptVO.getCrep_status());
			pstmt.setInt(6, ccmtRptVO.getAdm_no());
			pstmt.setString(7, ccmtRptVO.getCrep_result());
				
		   pstmt.executeUpdate();
			
			System.out.println("insert sucessful");
					
		} catch (SQLException se) {
			// TODO Auto-generated catch block
		se.printStackTrace();
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
			//Clean up JDBC resources
		} finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			
			
	
	
	
	
	
	}

	@Override
	public void update(Ccmt_ReportVO ccmtRptVO) {
		// TODO Auto-generated method stub
		
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			
			pstmt.setInt(1, ccmtRptVO.getCcmt_no());
			pstmt.setInt(2, ccmtRptVO.getMem_no());
			pstmt.setString(3, ccmtRptVO.getCrep_title());
			pstmt.setString(4, ccmtRptVO.getCrep_cnt());
			pstmt.setString(5, ccmtRptVO.getCrep_status());
			pstmt.setInt(6, ccmtRptVO.getAdm_no());
			pstmt.setString(7, ccmtRptVO.getCrep_result());
			  
			pstmt.setInt(8, ccmtRptVO.getCrep_no());
				
			pstmt.executeUpdate();
			System.out.println("update sucessful");
			
			
					
			
			
	
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
		}finally{
			
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}
	
	
	
	
	
	}

	@Override
	public void delete(Integer crep_no) {
		// TODO Auto-generated method stub
		
		int updateCount_Reports = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = ds.getConnection();
			//1.設定於pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			//先刪討論區檢舉
			
			
			pstmt = con.prepareStatement(DELETE_Reports);
			
			pstmt.setInt(1, crep_no);
			updateCount_Reports = pstmt.executeUpdate();
			
			
			
			//再刪討論區
			pstmt = con.prepareStatement(DELETE_Comment);
			pstmt.setInt(1, crep_no);
			pstmt.executeUpdate();
			
			
			
			//2.設定於pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			
			System.out.println("刪除討論區文章編號" + crep_no + "時,共有文章" + updateCount_Reports 
					+ "篇同時被刪除");
			
			System.out.println("Delete sucessful!!!!");
			
			

		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}
	
	
	
	}

	@Override
	public Ccmt_ReportVO findByPrimaryKey(Integer crep_no) {
		// TODO Auto-generated method stub
		
		
		Ccmt_ReportVO ccmtRptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, crep_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
					
				//ccmtVo 也稱為Domain objects
				
				ccmtRptVO = new Ccmt_ReportVO();
				ccmtRptVO.setCrep_no(rs.getInt("crep_no"));
				ccmtRptVO.setCcmt_no(rs.getInt("ccmt_no"));
				ccmtRptVO.setMem_no(rs.getInt("mem_no"));
				ccmtRptVO.setCrep_title(rs.getString("crep_title"));				
				ccmtRptVO.setCrep_cnt(rs.getString("crep_cnt"));
				ccmtRptVO.setCrep_status(rs.getString("crep_status"));								
				ccmtRptVO.setAdm_no(rs.getInt("adm_no"));
				ccmtRptVO.setCrep_result(rs.getString("crep_result"));
				
			}
			
			
		
						
			// Handle any SQL errors
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
//			se.printStackTrace();
			
			throw new RuntimeException("A database error occured." 
					+ se.getMessage());
		
			// Clean up JDBC resources
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace(System.err);
				}
			}
		
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace(System.err);
				}
			}
			
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			
		}
			
		return ccmtRptVO;
	
	
	
	
	
	
	}

	@Override
	public List<Ccmt_ReportVO> getAll() {
		// TODO Auto-generated method stub
		
		
		
		List<Ccmt_ReportVO> list = new ArrayList<Ccmt_ReportVO>();
		Ccmt_ReportVO ccmtRptVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
			//ccmtVO 也稱為 Domain objects
			
			ccmtRptVO = new Ccmt_ReportVO();
			ccmtRptVO.setCrep_no(rs.getInt("crep_no"));
			ccmtRptVO.setCcmt_no(rs.getInt("ccmt_no"));
			ccmtRptVO.setMem_no(rs.getInt("mem_no"));
			ccmtRptVO.setCrep_title(rs.getString("crep_title"));
			ccmtRptVO.setCrep_cnt(rs.getString("crep_cnt"));
			ccmtRptVO.setCrep_status(rs.getString("crep_status"));
			ccmtRptVO.setAdm_no(rs.getInt("adm_no"));
			ccmtRptVO.setCrep_result(rs.getString("crep_result"));
			
			
			list.add(ccmtRptVO);//Store the row in the list
			
			}
			
		
			
			//Handle any SQL errors
		} catch (SQLException se) {
			// TODO Auto-generated catch block
//			se.printStackTrace();
			
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			//Clean up JDBC resources
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}
			
		return list;
	
	
	
	}

}
