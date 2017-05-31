package com.course.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.course_comment.model.Course_CommentVO;

public class CourseDAO implements CourseDAO_interface{

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

	private static final String INSERT_STMT = 
		"INSERT INTO COURSE(crs_no,ach_no,mem_no,cat_no,crs_teacher,crs_name,crs_info,crs_cont,crs_image0,crs_image1,crs_image2,crs_introvideo"
		+ ",crs_fr_target,crs_fr_str,crs_fr_fin,crs_fr_num,crs_price,crs_status,crs_create,crs_stand,crs_ppl,crs_time)"
		 + " VALUES (COURSE_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM COURSE order by crs_no";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM COURSE where crs_no = ?";
	private static final String DELETE = 
		"DELETE FROM COURSE where crs_no = ?";
	private static final String UPDATE = 
			"UPDATE COURSE set ach_no=?, mem_no=?, crs_teacher=?, crs_name=?, crs_info=?, crs_cont=?, crs_image0=?, crs_image1=?, "
			+ "crs_image2=?, crs_introvideo=?, crs_fr_target=?, crs_fr_str=?, crs_fr_fin=?, crs_fr_num=?, crs_price=?"
			+ ", crs_status=?, crs_create=?, crs_stand=?, crs_ppl=?, cat_no=?, crs_time=? where crs_no= ?";
	
	
	private static final String GET_CcmtByCourse_STMT = "SELECT ccmt_no, crs_no, mem_no, ccmt_cont, ccmt_status, ccmt_posttime FROM course_comment where crs_no = ? order by ccmt_no";
	
	
	
	@Override
	public void insert(CourseVO courseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, courseVO.getAch_no());
			pstmt.setInt(2, courseVO.getMem_no());
			pstmt.setInt(3, courseVO.getCat_no());
			pstmt.setString(4, courseVO.getCrs_teacher());
			pstmt.setString(5, courseVO.getCrs_name());
			pstmt.setString(6, courseVO.getCrs_info());
			pstmt.setString(7, courseVO.getCrs_cont());
			pstmt.setBytes(8, courseVO.getCrs_image0());
			pstmt.setBytes(9, courseVO.getCrs_image1());
			pstmt.setBytes(10, courseVO.getCrs_image2());
			pstmt.setBytes(11, courseVO.getCrs_introvideo());
			pstmt.setInt(12, courseVO.getCrs_fr_target());
			pstmt.setDate(13, courseVO.getCrs_fr_str());
			pstmt.setDate(14, courseVO.getCrs_fr_fin());
			pstmt.setInt(15, courseVO.getCrs_fr_num());
			pstmt.setInt(16, courseVO.getCrs_price());
			pstmt.setString(17, courseVO.getCrs_status());
			pstmt.setDate(18, courseVO.getCrs_create());
			pstmt.setString(19, courseVO.getCrs_stand());
			pstmt.setInt(20, courseVO.getCrs_ppl());	
			pstmt.setString(21, courseVO.getCrs_time());
			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(CourseVO courseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, courseVO.getAch_no());
			pstmt.setInt(2, courseVO.getMem_no());
			pstmt.setString(3, courseVO.getCrs_teacher());
			pstmt.setString(4, courseVO.getCrs_name());
			pstmt.setString(5, courseVO.getCrs_info());
			pstmt.setString(6, courseVO.getCrs_cont());
			pstmt.setBytes(7, courseVO.getCrs_image0());
			pstmt.setBytes(8, courseVO.getCrs_image1());
			pstmt.setBytes(9, courseVO.getCrs_image2());
			pstmt.setBytes(10, courseVO.getCrs_introvideo());
			pstmt.setInt(11, courseVO.getCrs_fr_target());
			pstmt.setDate(12, courseVO.getCrs_fr_str());
			pstmt.setDate(13, courseVO.getCrs_fr_fin());
			pstmt.setInt(14, courseVO.getCrs_fr_num());
			pstmt.setInt(15, courseVO.getCrs_price());
			pstmt.setString(16, courseVO.getCrs_status());
			pstmt.setDate(17, courseVO.getCrs_create());
			pstmt.setString(18, courseVO.getCrs_stand());
			pstmt.setInt(19, courseVO.getCrs_ppl());
			pstmt.setInt(20, courseVO.getCat_no());
			pstmt.setString(21, courseVO.getCrs_time());			
			pstmt.setInt(22, courseVO.getCrs_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void delete(Integer crs_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, crs_no);

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public CourseVO findByPrimaryKey(Integer crs_no) {
		CourseVO courseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, crs_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				courseVO = new CourseVO();
				courseVO.setCrs_no(rs.getInt("crs_no"));
				courseVO.setAch_no(rs.getInt("ach_no"));
				courseVO.setMem_no(rs.getInt("mem_no"));
				courseVO.setCrs_teacher(rs.getString("crs_teacher"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setCrs_info(rs.getString("crs_info"));
				courseVO.setCrs_cont(rs.getString("crs_cont"));
				courseVO.setCrs_image0(rs.getBytes("crs_image0"));
				courseVO.setCrs_image1(rs.getBytes("crs_image1"));
				courseVO.setCrs_image2(rs.getBytes("crs_image2"));
				courseVO.setCrs_introvideo(rs.getBytes("crs_introvideo"));
				courseVO.setCrs_fr_target(rs.getInt("crs_fr_target"));
				courseVO.setCrs_fr_str(rs.getDate("crs_fr_str"));
				courseVO.setCrs_fr_fin(rs.getDate("crs_fr_fin"));
				courseVO.setCrs_fr_num(rs.getInt("crs_fr_num"));
				courseVO.setCrs_price(rs.getInt("crs_price"));
				courseVO.setCrs_status(rs.getString("crs_status"));
				courseVO.setCrs_create(rs.getDate("crs_create"));
				courseVO.setCrs_stand(rs.getString("crs_stand"));
				courseVO.setCrs_ppl(rs.getInt("crs_ppl"));
				courseVO.setCat_no(rs.getInt("cat_no"));
				courseVO.setCrs_time(rs.getString("crs_time"));
			}

			// Handle any SQL errors
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
		return courseVO;
	}

	@Override
	public List<CourseVO> getAll() {
		List<CourseVO> list = new ArrayList<CourseVO>();
		CourseVO courseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				courseVO = new CourseVO();
				courseVO.setCrs_no(rs.getInt("crs_no"));
				courseVO.setAch_no(rs.getInt("ach_no"));
				courseVO.setMem_no(rs.getInt("mem_no"));
				courseVO.setCrs_teacher(rs.getString("crs_teacher"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setCrs_info(rs.getString("crs_info"));
				courseVO.setCrs_cont(rs.getString("crs_cont"));
				courseVO.setCrs_image0(rs.getBytes("crs_image0"));
				courseVO.setCrs_image1(rs.getBytes("crs_image1"));
				courseVO.setCrs_image2(rs.getBytes("crs_image2"));
				courseVO.setCrs_introvideo(rs.getBytes("crs_introvideo"));
				courseVO.setCrs_fr_target(rs.getInt("crs_fr_target"));
				courseVO.setCrs_fr_str(rs.getDate("crs_fr_str"));
				courseVO.setCrs_fr_fin(rs.getDate("crs_fr_fin"));
				courseVO.setCrs_fr_num(rs.getInt("crs_fr_num"));
				courseVO.setCrs_price(rs.getInt("crs_price"));
				courseVO.setCrs_status(rs.getString("crs_status"));
				courseVO.setCrs_create(rs.getDate("crs_create"));
				courseVO.setCrs_stand(rs.getString("crs_stand"));
				courseVO.setCrs_ppl(rs.getInt("crs_ppl"));
				courseVO.setCat_no(rs.getInt("cat_no"));
				courseVO.setCrs_time(rs.getString("crs_time"));
				list.add(courseVO); // Store the row in the list
			}

			// Handle any SQL errors
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
	public Set<Course_CommentVO> getCcmtByCourse(Integer crs_no) {
		// TODO Auto-generated method stub
Set<Course_CommentVO> set = new LinkedHashSet<Course_CommentVO>();
		
		Course_CommentVO ccmtVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
try{
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CcmtByCourse_STMT);
					
			pstmt.setInt(1,crs_no);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				
				ccmtVO = new Course_CommentVO();
				ccmtVO.setCcmt_no(rs.getInt("ccmt_no"));
				ccmtVO.setCrs_no(rs.getInt("crs_no"));
				ccmtVO.setMem_no(rs.getInt("mem_no"));
				ccmtVO.setCcmt_cont(rs.getString("ccmt_cont"));
				ccmtVO.setCcmt_status(rs.getString("ccmt_status"));
				ccmtVO.setCcmt_posttime(rs.getDate("ccmt_posttime"));
				set.add(ccmtVO);
				
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
	
}
