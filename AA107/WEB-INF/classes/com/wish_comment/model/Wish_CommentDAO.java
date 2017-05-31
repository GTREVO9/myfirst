package com.wish_comment.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Wish_CommentDAO implements Wish_CommentDAO_interface {

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
			"INSERT INTO wish_comment (wcmt_no,wis_no,mem_no,wcmt_cont,wcmt_time,wcmt_status) VALUES (wish_comment_seq.NEXTVAL, ?, ?, ?, (select sysdate from dual), ?)";
	private static final String GET_ALL_STMT = 
			"SELECT wcmt_no,wis_no,mem_no,wcmt_cont,to_char(wcmt_time,'yyyy-mm-dd hh24:mi:ss') wcmt_time,wcmt_status FROM wish_comment order by wcmt_no";
	private static final String GET_ONE_STMT = 
			"SELECT wcmt_no,wis_no,mem_no,wcmt_cont,to_char(wcmt_time,'yyyy-mm-dd hh24:mi:ss') wcmt_time,wcmt_status FROM wish_comment where wcmt_no = ?";
	private static final String GET_MEM_NO_STMT = 
			"SELECT wcmt_no,wis_no,mem_no,wcmt_cont,to_char(wcmt_time,'yyyy-mm-dd hh24:mi:ss') wcmt_time,wcmt_status FROM wish_comment where mem_no = ?";
	private static final String DELETE = 
			"DELETE FROM wish_comment where wcmt_no = ?";
	private static final String UPDATE = 
			"UPDATE wish_comment set wis_no=?, mem_no=?, wcmt_cont=?, wcmt_time=(select sysdate from dual), wcmt_status=? where wcmt_no = ?";

	@Override
	public void insert(Wish_CommentVO wish_commentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, wish_commentVO.getWis_no());
			pstmt.setInt(2, wish_commentVO.getMem_no());
			pstmt.setString(3, wish_commentVO.getWcmt_cont());
			pstmt.setString(4, wish_commentVO.getWcmt_status());

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
	public void update(Wish_CommentVO wish_commentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, wish_commentVO.getWis_no());
			pstmt.setInt(2, wish_commentVO.getMem_no());
			pstmt.setString(3, wish_commentVO.getWcmt_cont());
			pstmt.setString(4, wish_commentVO.getWcmt_status());
			pstmt.setInt(5, wish_commentVO.getWcmt_no());


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
	public void delete(Integer wcmt_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, wcmt_no);

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
	public Wish_CommentVO findByPrimaryKey(Integer wcmt_no) {

		Wish_CommentVO wish_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, wcmt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				wish_commentVO = new Wish_CommentVO();
				wish_commentVO.setWcmt_no(rs.getInt("wcmt_no"));
				wish_commentVO.setWis_no(rs.getInt("wis_no"));
				wish_commentVO.setMem_no(rs.getInt("mem_no"));
				wish_commentVO.setWcmt_cont(rs.getString("wcmt_cont"));
				wish_commentVO.setWcmt_time(rs.getTimestamp("wcmt_time"));
				wish_commentVO.setWcmt_status(rs.getString("wcmt_status"));
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
		return wish_commentVO;
	}

	@Override
	public List<Wish_CommentVO> getAll() {
		List<Wish_CommentVO> list = new ArrayList<Wish_CommentVO>();
		Wish_CommentVO wish_commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				wish_commentVO = new Wish_CommentVO();
				wish_commentVO.setWcmt_no(rs.getInt("wcmt_no"));
				wish_commentVO.setWis_no(rs.getInt("wis_no"));
				wish_commentVO.setMem_no(rs.getInt("mem_no"));
				wish_commentVO.setWcmt_cont(rs.getString("wcmt_cont"));
				wish_commentVO.setWcmt_time(rs.getTimestamp("wcmt_time"));
				wish_commentVO.setWcmt_status(rs.getString("wcmt_status"));
				list.add(wish_commentVO); // Store the row in the list
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
	
	public List<Wish_CommentVO> getMem_no(Integer mem_no) {
		List<Wish_CommentVO> list = new ArrayList<Wish_CommentVO>();
		Wish_CommentVO wish_commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_NO_STMT);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				wish_commentVO = new Wish_CommentVO();
				wish_commentVO.setWcmt_no(rs.getInt("wcmt_no"));
				wish_commentVO.setWis_no(rs.getInt("wis_no"));
				wish_commentVO.setMem_no(rs.getInt("mem_no"));
				wish_commentVO.setWcmt_cont(rs.getString("wcmt_cont"));
				wish_commentVO.setWcmt_time(rs.getTimestamp("wcmt_time"));
				wish_commentVO.setWcmt_status(rs.getString("wcmt_status"));
				list.add(wish_commentVO); // Store the row in the list
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
}