package com.course_comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ccmt_report.model.Ccmt_ReportVO;
import com.compositequery_course_comment.CompositeQuery_Course_Comment;

public class Course_CommentDAO implements Course_CommentDAO_interface{

	
	//�@�����ε{����,�w��@�Ӹ�Ʈw�A�@�Τ@��DataSource�Y�i
	
	private static DataSource ds = null;
	static{
		
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			
						
		}catch(NamingException e){
			e.printStackTrace();
		}
		
	}
	
	

	private static final String INSERT_STMT =
			"INSERT INTO course_comment (ccmt_no,crs_no,mem_no,ccmt_cont,ccmt_status,ccmt_posttime) VALUES (course_comment_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	
	private static final String GET_ONE_STMT =
			"SELECT ccmt_no, crs_no, mem_no, ccmt_cont, ccmt_status, to_char(ccmt_posttime,'yyyy-mm-dd') ccmt_posttime FROM course_comment where ccmt_no = ?";
	
	private static final String GET_ALL_STMT =
			"SELECT ccmt_no, crs_no, mem_no, ccmt_cont, ccmt_status, to_char(ccmt_posttime,'yyyy-mm-dd') ccmt_posttime FROM course_comment order by ccmt_no";
	
	private static final String UPDATE = 
			"UPDATE course_comment set crs_no=?, mem_no=?, ccmt_cont=?, ccmt_status=?, ccmt_posttime=? where ccmt_no = ?";	
	
	private static final String GET_CcmtRptsByCcmt_STMT = "SELECT crep_no, ccmt_no, mem_no, crep_title, crep_cnt, crep_status, adm_no, crep_result FROM ccmt_report where ccmt_no = ? order by crep_no";
							
	
	
	
	private static final String DELETE_Ccmt_Reports = "DELETE FROM ccmt_report where ccmt_no = ?";
	
	
	private static final String DELETE_Course_Comment = "DELETE FROM course_comment where ccmt_no = ?";	
	
	
	
	
	
	@Override
	public void insert(Course_CommentVO ccmtVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, ccmtVO.getCrs_no());
			pstmt.setInt(2, ccmtVO.getMem_no());
			pstmt.setString(3, ccmtVO.getCcmt_cont());

			pstmt.setString(4, ccmtVO.getCcmt_status());
			pstmt.setDate(5, ccmtVO.getCcmt_posttime());
			
			
//			pstmt.setBytes(6, ccmtVO.getCcmt_image());
			
			
			
			pstmt.executeUpdate();
			
			System.out.println("insert sucessful");
				
		} catch (SQLException se) {
			// TODO Auto-generated catch block
		se.printStackTrace();
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
			//Clean up JDBC resources
		}  finally{
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
	public void update(Course_CommentVO ccmtVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setInt(1, ccmtVO.getCrs_no());
			pstmt.setInt(2, ccmtVO.getMem_no());
			pstmt.setString(3, ccmtVO.getCcmt_cont());
			pstmt.setString(4, ccmtVO.getCcmt_status());
			pstmt.setDate(5, ccmtVO.getCcmt_posttime());
			
			pstmt.setInt(6, ccmtVO.getCcmt_no());
			
			
			

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
	public void delete(Integer ccmt_no) {
		// TODO Auto-generated method stub
		

		int updateCount_Ccmt_Reports = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			
			
			
			con.setAutoCommit(false);
			
					
			pstmt = con.prepareStatement(DELETE_Ccmt_Reports);
			pstmt.setInt(1, ccmt_no);
			updateCount_Ccmt_Reports = pstmt.executeUpdate();
			
			
			
			
		
			pstmt = con.prepareStatement(DELETE_Course_Comment);
			pstmt.setInt(1, ccmt_no);
			pstmt.executeUpdate();
			
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("�R���Q�װϤ峹�s��" + ccmt_no + "��,�@���峹" + updateCount_Ccmt_Reports 
					+ "�g�P�ɳQ�R��");
			
			
			
			
		}catch(Exception e){
			
			if(con != null){
				
				try {
					
					//3.�]�w���exception�o�ͮɪ�catch�϶���
					con.rollback();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					throw new RuntimeException("rollback error occured. "
							+ e1.getMessage());
				}
				
			}
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
			
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
	
	
	
	
	
	}

	@Override
	public Course_CommentVO findByPrimaryKey(Integer ccmt_no) {
		// TODO Auto-generated method stub
		
		
		Course_CommentVO ccmtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, ccmt_no);
			
			rs = pstmt.executeQuery();
			
	while (rs.next()){
				
				//ccmtVo �]�٬�Domain objects
				
				ccmtVO = new Course_CommentVO();
				ccmtVO.setCcmt_no(rs.getInt("ccmt_no"));
				ccmtVO.setCrs_no(rs.getInt("crs_no"));
				ccmtVO.setMem_no(rs.getInt("mem_no"));
				ccmtVO.setCcmt_cont(rs.getString("ccmt_cont"));				
				ccmtVO.setCcmt_status(rs.getString("ccmt_status"));
				ccmtVO.setCcmt_posttime(rs.getDate("ccmt_posttime"));				
								
			}
			
			
			//Handle any driver errors
			
		
			
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
			
		return ccmtVO;
	
		
		
		
		
		
	
	
	
	
	}

	@Override
	public List<Course_CommentVO> getAll() {
		// TODO Auto-generated method stub
		
		List<Course_CommentVO> list = new ArrayList<Course_CommentVO>();
		Course_CommentVO ccmtVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
			//ccmtVO �]�٬� Domain objects
			
			ccmtVO = new Course_CommentVO();
			ccmtVO.setCcmt_no(rs.getInt("ccmt_no"));
			ccmtVO.setCrs_no(rs.getInt("crs_no"));
			ccmtVO.setMem_no(rs.getInt("mem_no"));
			ccmtVO.setCcmt_cont(rs.getString("ccmt_cont"));
			ccmtVO.setCcmt_status(rs.getString("ccmt_status"));
			ccmtVO.setCcmt_posttime(rs.getDate("ccmt_posttime"));
			
			
//			ccmtVO.setCcmt_image(rs.getBytes("ccmt_image"));
			
			
			
			list.add(ccmtVO);//Store the row in the list
				
			}
			
			

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

	@Override
	public Set<Ccmt_ReportVO> getCcmtRptByCcmt(Integer ccmt_no) {
		// TODO Auto-generated method stub
		
		
		Set<Ccmt_ReportVO> set = new LinkedHashSet<Ccmt_ReportVO>();
		
		Ccmt_ReportVO ccmtRptVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CcmtRptsByCcmt_STMT);
					
			pstmt.setInt(1,ccmt_no);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				
				ccmtRptVO = new Ccmt_ReportVO();
				ccmtRptVO.setCrep_no(rs.getInt("crep_no"));
				ccmtRptVO.setCcmt_no(rs.getInt("ccmt_no"));
				ccmtRptVO.setMem_no(rs.getInt("mem_no"));
				ccmtRptVO.setCrep_title(rs.getString("crep_title"));
				ccmtRptVO.setCrep_cnt(rs.getString("crep_cnt"));
				ccmtRptVO.setCrep_status(rs.getString("crep_status"));
				ccmtRptVO.setAdm_no(rs.getInt("adm_no"));
				ccmtRptVO.setCrep_result(rs.getString("crep_result"));
				set.add(ccmtRptVO);
				
			}
					
		}catch (Exception se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
		
		
		
	}

	@Override
	public List<Course_CommentVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		
		
		List<Course_CommentVO> list = new ArrayList<Course_CommentVO>();
		Course_CommentVO ccmtVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		
			
			con = ds.getConnection();
			String finalSQL = "select * from course_comment " 
							  + CompositeQuery_Course_Comment.get_WhereCondition(map)
							  + "order by ccmt_no";		
			
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("����finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
			//ccmtVO �]�٬� Domain objects
			
				ccmtVO = new Course_CommentVO();
				ccmtVO.setCcmt_no(rs.getInt("ccmt_no"));
				ccmtVO.setCrs_no(rs.getInt("crs_no"));
				ccmtVO.setMem_no(rs.getInt("mem_no"));
				ccmtVO.setCcmt_cont(rs.getString("ccmt_cont"));
				ccmtVO.setCcmt_status(rs.getString("ccmt_status"));
				ccmtVO.setCcmt_posttime(rs.getDate("ccmt_posttime"));
			
			
//			ccmtVO.setCcmt_image(rs.getBytes("ccmt_image"));
			
			
			
			list.add(ccmtVO);//Store the row in the list
				
			}
			
			
			//Handle any driver errors
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
