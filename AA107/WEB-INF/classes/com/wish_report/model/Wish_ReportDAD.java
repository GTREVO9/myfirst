package com.wish_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.compositequery_wish_report.CompositeQuery_Wish_Report;
import com.course_comment.model.Course_CommentVO;

import oracle.net.aso.e;

public class Wish_ReportDAD implements Wish_ReportDAO_interface{
	
	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
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
			"SELECT wrep_no, wis_no, mem_no, wrep_title, wrep_cont, wrep_time, wrep_status, adm_no, wrep_result  FROM wish_report where wrep_no = ?";
	
	private static final String GET_ALL_STMT =
			"SELECT wrep_no, wis_no, mem_no, wrep_title, wrep_cont, wrep_time, wrep_status, adm_no, wrep_result FROM wish_report order by wrep_no";
	
	private static final String INSERT_STMT =
			"INSERT INTO wish_report (wrep_no, wis_no, mem_no, wrep_title, wrep_cont, wrep_time, wrep_status, adm_no, wrep_result) VALUES (wish_report_seq.NEXTVAL, ?,?,?,?,?,?,?,?)";
	
	private static final String DELETE =
			"DELETE FROM wish_report where wrep_no = ?";
	
	private static final String UPDATE = 
			"UPDATE wish_report set wis_no=?, mem_no=?, wrep_title=?, wrep_cont=?, wrep_time=?, wrep_status=?, adm_no=?, wrep_result=?  where wrep_no = ?";
	
	
	
	
	
	@Override
	public void insert(Wish_ReportVO wishptVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, wishptVO.getWis_no());
			pstmt.setInt(2,wishptVO.getMem_no());
			pstmt.setString(3,wishptVO.getWrep_title());
			pstmt.setString(4, wishptVO.getWrep_cont());
			pstmt.setDate(5,wishptVO.getWrep_time());
			pstmt.setString(6, wishptVO.getWrep_status());
			pstmt.setInt(7,wishptVO.getAdm_no());
			pstmt.setString(8, wishptVO.getWrep_result());			
			pstmt.executeUpdate();
			
			System.out.println("insert sucessful");
			
		} catch (Exception se) {
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
	public void update(Wish_ReportVO wishptVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
						
			pstmt.setInt(1, wishptVO.getWis_no());
			pstmt.setInt(2,wishptVO.getMem_no());
			pstmt.setString(3,wishptVO.getWrep_title());
			pstmt.setString(4, wishptVO.getWrep_cont());
			pstmt.setDate(5,wishptVO.getWrep_time());
			pstmt.setString(6, wishptVO.getWrep_status());
			pstmt.setInt(7,wishptVO.getAdm_no());
			pstmt.setString(8, wishptVO.getWrep_result());
			pstmt.setInt(9, wishptVO.getWrep_no());
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void delete(Integer wrep_no) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, wrep_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public Wish_ReportVO findByPrimaryKey(Integer wrep_no) {
		// TODO Auto-generated method stub
		Wish_ReportVO wishptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);	
			
			pstmt.setInt(1, wrep_no);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()){		
				wishptVO = new Wish_ReportVO();
				wishptVO.setWrep_no(rs.getInt("wrep_no"));
				wishptVO.setWis_no(rs.getInt("wis_no"));
				wishptVO.setMem_no(rs.getInt("mem_no"));
				wishptVO.setWrep_title(rs.getString("wrep_title"));
				wishptVO.setWrep_cont(rs.getString("wrep_cont"));
				wishptVO.setWrep_time(rs.getDate("wrep_time"));
				wishptVO.setWrep_status(rs.getString("wrep_status"));
				wishptVO.setAdm_no(rs.getInt("adm_no"));
				wishptVO.setWrep_result(rs.getString("wrep_result"));						
			}		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}finally {
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
		return wishptVO;
		
	
	
	}
	@Override
	public List<Wish_ReportVO> getAll() {
		// TODO Auto-generated method stub
		List<Wish_ReportVO> list = new ArrayList<Wish_ReportVO>();
		Wish_ReportVO wishptVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				wishptVO = new Wish_ReportVO();
				wishptVO.setWrep_no(rs.getInt("wrep_no"));
				wishptVO.setWis_no(rs.getInt("wis_no"));
				wishptVO.setMem_no(rs.getInt("mem_no"));
				wishptVO.setWrep_title(rs.getString("wrep_title"));
				wishptVO.setWrep_cont(rs.getString("wrep_cont"));
				wishptVO.setWrep_time(rs.getDate("wrep_time"));
				wishptVO.setWrep_status(rs.getString("wrep_status"));
				wishptVO.setAdm_no(rs.getInt("adm_no"));
				wishptVO.setWrep_result(rs.getString("wrep_result"));						
				list.add(wishptVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return list;
	}
	@Override
	public List<Wish_ReportVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		List<Wish_ReportVO> list = new ArrayList<Wish_ReportVO>();
		Wish_ReportVO wishptVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		
		try {

			
			con = ds.getConnection();
			
			String finalSQL = "select * from wish_report " 
					  + CompositeQuery_Wish_Report.getWishReport_WhereCondition(map)
					  + "order by wrep_no";		
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("����finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO �]�٬� Domain objects
				wishptVO = new Wish_ReportVO();
				wishptVO.setWrep_no(rs.getInt("wrep_no"));
				wishptVO.setWis_no(rs.getInt("wis_no"));
				wishptVO.setMem_no(rs.getInt("mem_no"));
				wishptVO.setWrep_title(rs.getString("wrep_title"));
				wishptVO.setWrep_cont(rs.getString("wrep_cont"));
				wishptVO.setWrep_time(rs.getDate("wrep_time"));
				wishptVO.setWrep_status(rs.getString("wrep_status"));
				wishptVO.setAdm_no(rs.getInt("adm_no"));
				wishptVO.setWrep_result(rs.getString("wrep_result"));						
				list.add(wishptVO); // Store the row in the list
			}

			// Handle any driver errors
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
			// Clean up JDBC resources
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
		return list;
	
	
	
	
	}
	
	
}
