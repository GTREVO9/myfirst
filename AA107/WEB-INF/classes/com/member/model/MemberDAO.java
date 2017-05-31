package com.member.model;

import java.util.*;

import java.sql.*;

import javax.naming.*;
import javax.naming.*;
import javax.naming.*;
import javax.sql.*;

public class MemberDAO implements MemberDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO member(mem_no,mem_lname,mem_fname,mem_id,mem_psw,mem_email,mem_mobile,mem_photo, "
			+ "mem_birthday,mem_address,mem_joindate,mem_bank,mem_point,mem_status)"
			+ "VALUES (MEMBER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";

	private static final String GET_ALL_STMT = "SELECT mem_no,mem_lname,mem_fname,mem_id,mem_psw,mem_email,mem_mobile,mem_photo,"
			+ "to_char(mem_birthday,'yyyy-mm-dd') mem_birthday,mem_address,mem_joindate,mem_bank,mem_point,"
			+ "mem_status FROM member order by mem_no";

	private static final String GET_ONE_STMT = "SELECT mem_no,mem_lname,mem_fname,mem_id,mem_psw,mem_email,mem_mobile,mem_photo,"
			+ "to_char(mem_birthday,'yyyy-mm-dd') mem_birthday,mem_address,mem_joindate,mem_bank,mem_point,"
			+ "mem_status FROM member where mem_id = ? ";
	private static final String GET_ONE_STMT_BY_NO = "SELECT mem_no,mem_lname,mem_fname,mem_id,mem_psw,mem_email,mem_mobile,mem_photo,"
			+ "to_char(mem_birthday,'yyyy-mm-dd') mem_birthday,mem_address,mem_joindate,mem_bank,mem_point,"
			+ "mem_status FROM member where mem_no = ? ";

	private static final String DELETE = "DELETE FROM Member where mem_no=?";

	private static final String UPDATE = "UPDATE  Member set mem_lname=?,mem_fname=?,mem_id=?,"
			+ "mem_psw=? ,mem_email=?,mem_mobile=?,mem_photo=?, mem_birthday=?,mem_address=?,mem_joindate=?,mem_bank=?, mem_point=?,mem_status=? where mem_no=?";
	
	

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;

		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memberVO.getMem_lname());

			pstmt.setString(2, memberVO.getMem_fname());

			pstmt.setString(3, memberVO.getMem_id());

			pstmt.setString(4, memberVO.getMem_psw());

			pstmt.setString(5, memberVO.getMem_email());

			pstmt.setString(6, memberVO.getMem_mobile());

			pstmt.setBytes(7, memberVO.getMem_photo());

			pstmt.setDate(8, memberVO.getMem_birthday());

			pstmt.setString(9, memberVO.getMem_address());

			pstmt.setDate(10, memberVO.getMem_joindate());

			pstmt.setString(11, memberVO.getMem_bank());

			pstmt.setInt(12, memberVO.getMem_point());

			pstmt.setString(13, memberVO.getMem_status());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberVO.getMem_lname());

			pstmt.setString(2, memberVO.getMem_fname());

			pstmt.setString(3, memberVO.getMem_id());

			pstmt.setString(4, memberVO.getMem_psw());

			pstmt.setString(5, memberVO.getMem_email());

			pstmt.setString(6, memberVO.getMem_mobile());

			pstmt.setBytes(7, memberVO.getMem_photo());

			pstmt.setDate(8, memberVO.getMem_birthday());

			pstmt.setString(9, memberVO.getMem_address());

			pstmt.setDate(10, memberVO.getMem_joindate());

			pstmt.setString(11, memberVO.getMem_bank());

			pstmt.setInt(12, memberVO.getMem_point());

			pstmt.setString(13, memberVO.getMem_status());

			pstmt.setInt(14, memberVO.getMem_no());

			pstmt.executeUpdate();

			// Handle any driver errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer mem_no) {

		int updateCount_Members = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mem_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	public MemberVO findByPrimaryKey(String mem_id) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

		


			while (rs.next()) {
				// memberVO 也稱為 Domain objects

				memberVO = new MemberVO();
				// memberVO.setMem_no(rs.getInt("mem_no"));
				memberVO.setMem_lname(rs.getString("mem_lname"));
				memberVO.setMem_fname(rs.getString("mem_fname"));
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setMem_psw(rs.getString("mem_psw"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_mobile(rs.getString("mem_mobile"));
				memberVO.setMem_photo(rs.getBytes("mem_photo"));
				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
				memberVO.setMem_address(rs.getString("mem_address"));
				memberVO.setMem_joindate(rs.getDate("mem_joindate"));
				memberVO.setMem_bank(rs.getString("mem_bank"));
				memberVO.setMem_point(rs.getInt("mem_point"));
				memberVO.setMem_status(rs.getString("mem_status"));
				memberVO.setMem_no(rs.getInt("mem_no"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return memberVO;
	}
	

	@Override
	public MemberVO findByPrimaryKey(Integer mem_no) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_NO);
				System.out.println(mem_no);
			pstmt.setInt(1, mem_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVO 也稱為 Domain objects

				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getInt("mem_no"));
				memberVO.setMem_lname(rs.getString("mem_lname"));
				memberVO.setMem_fname(rs.getString("mem_fname"));
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setMem_psw(rs.getString("mem_psw"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_mobile(rs.getString("mem_mobile"));
				memberVO.setMem_photo(rs.getBytes("mem_photo"));
				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
				memberVO.setMem_address(rs.getString("mem_address"));
				memberVO.setMem_joindate(rs.getDate("mem_joindate"));
				memberVO.setMem_bank(rs.getString("mem_bank"));
				memberVO.setMem_point(rs.getInt("mem_point"));
				memberVO.setMem_status(rs.getString("mem_status"));

			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				memberVO = new MemberVO();

				memberVO.setMem_no(rs.getInt("mem_no"));
				memberVO.setMem_lname(rs.getString("mem_lname"));
				memberVO.setMem_fname(rs.getString("mem_fname"));
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setMem_psw(rs.getString("mem_psw"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_mobile(rs.getString("mem_mobile"));
				memberVO.setMem_photo(rs.getBytes("mem_photo"));
				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
				memberVO.setMem_address(rs.getString("mem_address"));
				memberVO.setMem_joindate(rs.getDate("mem_joindate"));
				memberVO.setMem_bank(rs.getString("mem_bank"));
				memberVO.setMem_point(rs.getInt("mem_point"));
				memberVO.setMem_status(rs.getString("mem_status"));
				list.add(memberVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public Set<MemberVO> getEmpsByMember(Integer mem_no) {
		Set<MemberVO> set = new LinkedHashSet<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO.setMem_no(rs.getInt("mem_no"));
				memberVO.setMem_lname(rs.getString("mem_lname"));
				memberVO.setMem_fname(rs.getString("mem_fname"));
				memberVO.setMem_id(rs.getString("mem_id"));
				memberVO.setMem_psw(rs.getString("mem_psw"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_mobile(rs.getString("mem_mobile"));
				memberVO.setMem_photo(rs.getBytes("mem_photo"));
				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
				memberVO.setMem_address(rs.getString("mem_address"));
				memberVO.setMem_joindate(rs.getDate("mem_joindate"));
				memberVO.setMem_bank(rs.getString("mem_bank"));
				memberVO.setMem_point(rs.getInt("mem_point"));
				memberVO.setMem_status(rs.getString("mem_status"));
				set.add(memberVO);  
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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