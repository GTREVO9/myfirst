package com.member.model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107";
	String passwd = "aa107";
	
	private static final String INSERT_STMT = "INSERT INTO member(mem_no,mem_lname,mem_fname,mem_id,mem_psw,mem_email,mem_mobile,mem_photo, "
			+ "mem_birthday,mem_address,mem_joindate,mem_bank,mem_point,mem_status)"
			+ "VALUES (MEMBER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";

	private static final String GET_ALL_STMT = "SELECT mem_no,mem_lname,mem_fname,mem_id,mem_psw,mem_email,mem_mobile,mem_photo,"
			+ "to_char(mem_birthday,'yyyy-mm-dd') mem_birthday,mem_address,mem_joindate,mem_bank,mem_point,"
			+ "mem_status FROM member order by mem_no";

	private static final String GET_ONE_STMT = "SELECT mem_no, mem_lname,mem_fname,mem_id,mem_psw,mem_email,mem_mobile,mem_photo,"
			+ "to_char(mem_birthday,'yyyy-mm-dd') mem_birthday,mem_address,mem_joindate,mem_bank,mem_point,"
			+ "mem_status FROM member where mem_no = ? ";
	
	private static final String GET_ONE_id = "SELECT mem_no, mem_lname,mem_fname,mem_id,mem_psw,mem_email,mem_mobile,mem_photo,"
			+ "to_char(mem_birthday,'yyyy-mm-dd') mem_birthday,mem_address,mem_joindate,mem_bank,mem_point,"
			+ "mem_status FROM member where mem_id = ? ";


	private static final String DELETE = "DELETE FROM Member where mem_no=?";

	private static final String UPDATE = "UPDATE  Member set  mem_lname=?,mem_fname=?,mem_id=?,"
			+ "mem_psw=? ,mem_email=?,mem_mobile=?,mem_photo=?, mem_birthday=?,mem_address=?,mem_joindate=?,mem_bank=?, mem_point=?,mem_status=? where mem_no=?";

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			// UPDATE Member set mem_lname=?,mem_fname=?,mem_id=?,"
			// +"mem_psw=? ,mem_email=?,mem_mobile=?,mem_photo=?, "
			// + "mem_birthday=?,mem_address=?,mem_joindate=?,mem_bank=?,
			// mem_point=?,"
			// + "mem_status=? where mem_no=?";

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

		} catch (ClassNotFoundException e) {

			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mem_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {

			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

			// Handle any SQL errors
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
	
	public MemberVO findByPrimaryKey(String mem_id) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_id);

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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public MemberVO findByPrimaryKey(Integer mem_no) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mem_no);

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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return list;
	}

	public static void main(String[] args) throws Exception {

		MemberJDBCDAO dao = new MemberJDBCDAO();

//		 //新增
//		 MemberVO meberVO1 = new MemberVO();
//		
//		
//		 meberVO1.setMem_lname ("蕭");
//		 meberVO1.setMem_fname("亞軒");
//		 meberVO1.setMem_id("111");
//		 meberVO1.setMem_psw ("123");
//		 meberVO1.setMem_email("123@gmail.com");
//		 meberVO1.setMem_mobile ("123456789");
//		
//		 //InputStream in = part.getInputStream();
//		 FileInputStream in =new FileInputStream("c:/images/1.jpg");
//		 byte[] mem_photo = new byte[in.available()];
//		 in.read(mem_photo);
//		 meberVO1.setMem_photo(mem_photo);
//		 in.close();
		
	
	
		
//		 meberVO1.setMem_birthday (java.sql.Date.valueOf("2005-01-01"));
//		 meberVO1.setMem_address("桃園市中正路112號");
//		 meberVO1.setMem_joindate(java.sql.Date.valueOf("2005-01-01"));
//		 meberVO1.setMem_bank ("879566598745");
//		 meberVO1.setMem_point (2000);
//		 meberVO1.setMem_status("1");
//		 dao.insert(meberVO1);
//
//		 //修改
		 MemberVO memberVO2=new MemberVO();
		
		 memberVO2.setMem_no (11000001);
		 memberVO2.setMem_lname ("王");
		 memberVO2.setMem_fname("力宏");
		 memberVO2.setMem_id("321");
		 memberVO2.setMem_psw ("231");
		 memberVO2.setMem_email("234@gmail.com");
		 memberVO2.setMem_mobile ("1999999");
		 
		 //InputStream in = part.getInputStream();
		// FileInputStream in2 =new FileInputStream("c:/images/1.jpg");
//		 byte[] mem_photo2 = new byte[in2.available()];
//		 in2.read(mem_photo2);
//		 memberVO2.setMem_photo(mem_photo2);
//		 in2.close();
		 memberVO2.setMem_photo (null);
		 memberVO2.setMem_birthday (java.sql.Date.valueOf("2005-04-01"));
		 memberVO2.setMem_address("桃園市中正路333號");
		 memberVO2.setMem_joindate(java.sql.Date.valueOf("2008-04-01"));
		 memberVO2.setMem_bank ("879566566666");
		 memberVO2.setMem_point (2220);
		 memberVO2.setMem_status("0");
		
		
		 dao.update(memberVO2);
		 System.out.println("sueccess");

//		// 刪除
//		dao.delete(11000008);
//
//		// 查詢
//		
//		 MemberVO memberVO3 = dao.findByPrimaryKey(11000002);
//		 System.out.print(memberVO3.getMem_no() + ",");
//		 System.out.print(memberVO3.getMem_lname() + ",");
//		 System.out.print(memberVO3.getMem_fname() + ",");
//		 System.out.print(memberVO3.getMem_id() + ",");
//		 System.out.print(memberVO3.getMem_psw() + ",");
//		 System.out.print(memberVO3.getMem_email() + ",");
//		 System.out.println(memberVO3.getMem_mobile());
//		 System.out.println(memberVO3.getMem_photo());
//		 System.out.print(memberVO3.getMem_birthday() + ",");
//		 System.out.print(memberVO3.getMem_address() + ",");
//		 System.out.print(memberVO3.getMem_joindate() + ",");
//		 System.out.print(memberVO3.getMem_bank() + ",");
//		 System.out.println(memberVO3.getMem_point());
//		 System.out.println(memberVO3.getMem_status());
//		 System.out.println("---------------------");
//
//		// 查詢
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO aMember : list) {
//			System.out.print(aMember.getMem_no() + ",");
//			System.out.print(aMember.getMem_lname() + ",");
//			System.out.print(aMember.getMem_fname() + ",");
//			System.out.print(aMember.getMem_id() + ",");
//			System.out.print(aMember.getMem_psw() + ",");
//			System.out.print(aMember.getMem_email() + ",");
//			System.out.print(aMember.getMem_mobile());
//			System.out.print(aMember.getMem_photo());
//
//			System.out.print(aMember.getMem_birthday() + ",");
//			System.out.print(aMember.getMem_address() + ",");
//			System.out.print(aMember.getMem_joindate() + ",");
//			System.out.print(aMember.getMem_bank() + ",");
//			System.out.print(aMember.getMem_point() + ",");
//			System.out.print(aMember.getMem_status() + ",");
//
//			System.out.println();
//
//		}
	}
}
