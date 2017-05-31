package com.wish_report.model;


import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.compositequery_wish_report.CompositeQuery_Wish_Report;

public class Wish_ReportJDBCDAD implements Wish_ReportDAO_interface{

	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107";
	String passwd = "123";
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block

			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
	public void update(Wish_ReportVO wishptVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(Integer wrep_no) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, wrep_no);

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
	public Wish_ReportVO findByPrimaryKey(Integer wrep_no) {
		// TODO Auto-generated method stub
		

		Wish_ReportVO wishptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
		
			
			pstmt.setInt(1, wrep_no);
			
			rs = pstmt.executeQuery();
			
			
			while (rs.next()){
				
				//ccmtVo �]�٬�Domain objects
				
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
			
			
			
		} catch (ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
			
			
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	@Override
	public List<Wish_ReportVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		List<Wish_ReportVO> list = new ArrayList<Wish_ReportVO>();
		Wish_ReportVO wishptVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			String finalSQL = "select * from wish_Report " 
					  + CompositeQuery_Wish_Report.getWishReport_WhereCondition(map)
					  + "order by crep_no";
			
			
			
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("finalSQL(by DAO) = "+finalSQL);
			System.out.println("AAAAAA");
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





	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		Wish_ReportJDBCDAD dao = new Wish_ReportJDBCDAD();
		
		
		
//		Wish_ReportVO wishptVO = dao.findByPrimaryKey(82000001);
//		System.out.print(wishptVO.getWrep_no() + ",");
//		System.out.print(wishptVO.getWis_no() + ",");
//		System.out.print(wishptVO.getMem_no() + ",");
//		System.out.print(wishptVO.getWrep_title() + ",");
//		System.out.print(wishptVO.getWrep_cont() + ",");
//		System.out.print(wishptVO.getWrep_time() + ",");
//		System.out.print(wishptVO.getWrep_status() + ",");
//		System.out.print(wishptVO.getAdm_no() + ",");
//		System.out.print(wishptVO.getWrep_result() + ",");
	
	
			
//		List<Wish_ReportVO> list = dao.getAll();
//		for (Wish_ReportVO aWish_Report : list) {
//			System.out.print(aWish_Report.getWrep_no() + ",");
//			System.out.print(aWish_Report.getWis_no() + ",");
//			System.out.print(aWish_Report.getMem_no() + ",");
//			System.out.print(aWish_Report.getWrep_title() + ",");
//			System.out.print(aWish_Report.getWrep_cont() + ",");
//			System.out.print(aWish_Report.getWrep_time() + ",");
//			System.out.print(aWish_Report.getWrep_status() + ",");
//			System.out.print(aWish_Report.getAdm_no() + ",");
//			System.out.print(aWish_Report.getWrep_result() + ",");
//			
//			System.out.println();
//		}
	
		
		
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("wrep_no", new String[] {"82000001"});
//		map.put("wis_no", new String[] {"31000001"});
//		map.put("mem_no", new String[] {"11000001"});
//		map.put("wrep_title", new String[] {"JQuery"});
//		map.put("wrep_cont", new String[] {"I wnat to learn JQuery"});
//		map.put("wrep_time", new String[] {"2016-12-31"});
//		map.put("adm_no", new String[] {"13000001"});
//		map.put("wrep_status", new String[] {"2"});
//		map.put("wrep_result", new String[] {"its not wrong"});
//		
//		
//		
//
//
//		Set set = map.keySet();
//		
//		Iterator it = set.iterator();
//		while(it.hasNext()){
//			Object mykey = it.next();
//			System.out.println(mykey + "=" + map.get(mykey));
//		}
		
		
		
		Wish_ReportVO wishptVO = new Wish_ReportVO();				
		wishptVO.setWis_no(31000010);
		wishptVO.setMem_no(11000003);
		wishptVO.setWrep_title("刪除測試-台灣夢");
		wishptVO.setWrep_cont("its teach have a big head");
		wishptVO.setWrep_time(java.sql.Date.valueOf("2005-01-01"));
		wishptVO.setWrep_status("0");
		wishptVO.setAdm_no(13000003);
		wishptVO.setWrep_result("台灣夢");	
		dao.insert(wishptVO);
	
		
		
//		dao.delete(82000005);

		
//				Wish_ReportVO wishptVO = new Wish_ReportVO();			
//	    wishptVO.setWrep_no(82000006);				
//		wishptVO.setWis_no(31000002);
//		wishptVO.setMem_no(11000003);
//		wishptVO.setWrep_title("bbb");
//		wishptVO.setWrep_cont("aaaaaa");
//		wishptVO.setWrep_time(java.sql.Date.valueOf("2017-05-18"));
//		wishptVO.setWrep_status("0");
//		wishptVO.setAdm_no(13000003);
//		wishptVO.setWrep_result("cc");
//		dao.update(wishptVO);
	}


}
