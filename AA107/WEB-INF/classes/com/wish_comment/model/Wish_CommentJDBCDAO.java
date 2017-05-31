package com.wish_comment.model;

import java.util.*;
import java.sql.*;

public class Wish_CommentJDBCDAO implements Wish_CommentDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107";
	String passwd = "123";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, wish_commentVO.getWis_no());
			pstmt.setInt(2, wish_commentVO.getMem_no());
			pstmt.setString(3, wish_commentVO.getWcmt_cont());
			pstmt.setString(4, wish_commentVO.getWcmt_status());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, wish_commentVO.getWis_no());
			pstmt.setInt(2, wish_commentVO.getMem_no());
			pstmt.setString(3, wish_commentVO.getWcmt_cont());
			pstmt.setString(4, wish_commentVO.getWcmt_status());
			pstmt.setInt(5, wish_commentVO.getWcmt_no());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(Integer wcmt_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, wcmt_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public Wish_CommentVO findByPrimaryKey(Integer wcmt_no) {

		Wish_CommentVO wish_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, wcmt_no);

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
				
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	
	public List<Wish_CommentVO> getMem_no(Integer mem_no) {
		List<Wish_CommentVO> list = new ArrayList<Wish_CommentVO>();
		Wish_CommentVO wish_commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
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

	public static void main(String[] args) {

		Wish_CommentJDBCDAO dao = new Wish_CommentJDBCDAO();

		// 新增
//		Wish_CommentVO wish_commentVO1 = new Wish_CommentVO();
//		wish_commentVO1.setWis_no(new Integer(31000001));
//		wish_commentVO1.setMem_no(new Integer(11000001));
//		wish_commentVO1.setWcmt_cont("我看看");
//		wish_commentVO1.setWcmt_status("1");
//		dao.insert(wish_commentVO1);

		// 修改
//		Wish_CommentVO wish_commentVO2 = new Wish_CommentVO();
//		wish_commentVO2.setWis_no(new Integer(31000010));
//		wish_commentVO2.setMem_no(new Integer(11000010));
//		wish_commentVO2.setWcmt_cont("我看看");
//		wish_commentVO2.setWcmt_status("2");
//		wish_commentVO2.setWcmt_no(new Integer(32000002));
//		dao.update(wish_commentVO2);

//		// 刪除
//		dao.delete(32000006);

		// 查詢
		Wish_CommentVO wish_commentVO3 = dao.findByPrimaryKey(32000002);
		System.out.print(wish_commentVO3.getWcmt_no() + ",");
		System.out.print(wish_commentVO3.getWis_no() + ",");
		System.out.print(wish_commentVO3.getMem_no() + ",");
		System.out.print(wish_commentVO3.getWcmt_cont() + ",");
		System.out.print(wish_commentVO3.getWcmt_time() + ",");
		System.out.print(wish_commentVO3.getWcmt_status() + ",");
		System.out.println("---------------------");


		// 查詢
		List<Wish_CommentVO> list = dao.getAll();
		for (Wish_CommentVO awish_comment : list) {
			System.out.print(awish_comment.getWcmt_no() + ",");
			System.out.print(awish_comment.getWis_no() + ",");
			System.out.print(awish_comment.getMem_no() + ",");
			System.out.print(awish_comment.getWcmt_cont() + ",");
			System.out.print(awish_comment.getWcmt_time() + ",");
			System.out.print(awish_comment.getWcmt_status() + ",");
			System.out.println();
			}
		// 查詢
		List<Wish_CommentVO> list2 = dao.getMem_no(11000001);
		for (Wish_CommentVO awish_comment2 : list2) {
			System.out.print(awish_comment2.getWcmt_no() + ",");
			System.out.print(awish_comment2.getWis_no() + ",");
			System.out.print(awish_comment2.getMem_no() + ",");
			System.out.print(awish_comment2.getWcmt_cont() + ",");
			System.out.print(awish_comment2.getWcmt_time() + ",");
			System.out.print(awish_comment2.getWcmt_status() + ",");
			System.out.println("---------------------");
		}
	
	}
}