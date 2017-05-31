package com.course_comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.ccmt_report.model.Ccmt_ReportVO;
import com.compositequery_course_comment.CompositeQuery_Course_Comment;


public class Course_CommentJDBCDAD implements Course_CommentDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107";
	String passwd = "123";
	

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, ccmtVO.getCrs_no());
			pstmt.setInt(2, ccmtVO.getMem_no());
			pstmt.setString(3, ccmtVO.getCcmt_cont());

			pstmt.setString(4, ccmtVO.getCcmt_status());
			pstmt.setDate(5, ccmtVO.getCcmt_posttime());
			
			
//			pstmt.setBytes(6, ccmtVO.getCcmt_image());
			
			
			
			pstmt.executeUpdate();
			
			System.out.println("insert sucessful");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setInt(1, ccmtVO.getCrs_no());
			pstmt.setInt(2, ccmtVO.getMem_no());
			pstmt.setString(3, ccmtVO.getCcmt_cont());
			pstmt.setString(4, ccmtVO.getCcmt_status());
			pstmt.setDate(5, ccmtVO.getCcmt_posttime());
			
			pstmt.setInt(6, ccmtVO.getCcmt_no());
			
			
			

			pstmt.executeUpdate();
			
					
			System.out.println("update sucessful");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			
			
			//1.�]�w��pstm.executeUpdate()���e
			con.setAutoCommit(false);
			
					//���R�Q�װ����|
			pstmt = con.prepareStatement(DELETE_Ccmt_Reports);
			pstmt.setInt(1, ccmt_no);
			updateCount_Ccmt_Reports = pstmt.executeUpdate();
			
			
					//�A�R�Q�װ�
			pstmt = con.prepareStatement(DELETE_Course_Comment);
			pstmt.setInt(1, ccmt_no);
			pstmt.executeUpdate();
			
			//2.�]�w��pstm.executeUpdate()����
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			
			throw new RuntimeException("Couldn't load database driver."
					+ e.getMessage());
						
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			
			//Handle any driver errors
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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

	
	
	
	
	
	@Override
	public Set<Ccmt_ReportVO> getCcmtRptByCcmt(Integer ccmt_no) {
		// TODO Auto-generated method stub
		
Set<Ccmt_ReportVO> set = new LinkedHashSet<Ccmt_ReportVO>();
		
		Ccmt_ReportVO ccmtRptVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			

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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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









	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Course_CommentJDBCDAD dao = new Course_CommentJDBCDAD();
	
		
		
		
		//�s�W
//		Course_CommentVO ccmtVO2 = new Course_CommentVO();
//		
//		ccmtVO2.setCrs_no(21000005);
//		ccmtVO2.setMem_no(11000005);
//		ccmtVO2.setCcmt_cont("bbbb");
//		ccmtVO2.setCcmt_status("0");
//		ccmtVO2.setCcmt_posttime(java.sql.Date.valueOf("2017-05-07"));
//		dao.insert(ccmtVO2);
		
		
		
		
		//�d�߳��
//		Course_CommentVO ccmtVO1 = dao.findByPrimaryKey(23000002);	
//		System.out.print(ccmtVO1.getCcmt_no() + ",");
//		System.out.print(ccmtVO1.getCrs_no() + ",");
//		System.out.print(ccmtVO1.getMem_no() + ",");
//		System.out.print(ccmtVO1.getCcmt_cont() + ",");
//		System.out.print(ccmtVO1.getCcmt_status() + ",");
//		System.out.println(ccmtVO1.getCcmt_posttime());
//		System.out.println("---------------------------------------------------------------------------------");
	
	
		// �d�ߥ���
//		List<Course_CommentVO> list = dao.getAll();
//		for (Course_CommentVO aCctm : list) {
//		System.out.print(aCctm.getCcmt_no() + ",");
//		System.out.print(aCctm.getCrs_no() + ",");
//		System.out.print(aCctm.getMem_no() + ",");
//		System.out.print(aCctm.getCcmt_cont() + ",");
//		System.out.print(aCctm.getCcmt_status() + ",");
//		System.out.print(aCctm.getCcmt_posttime() + ",");
//	
//		System.out.println();
//	}
		
		
		// �d�ߥ���Set
//		Set<Ccmt_ReportVO> set = dao.getCcmtRptByCcmt(23000001);
//		for (Ccmt_ReportVO aCctm : set) {
//		
//		System.out.print(aCctm.getCrep_no() + ",");	
//		System.out.print(aCctm.getCcmt_no() + ",");
//		System.out.print(aCctm.getMem_no() + ",");
//		System.out.print(aCctm.getCrep_title() + ",");
//		System.out.print(aCctm.getCrep_cnt() + ",");
//		System.out.print(aCctm.getCrep_status() + ",");
//		System.out.print(aCctm.getAdm_no() + ",");
//		System.out.print(aCctm.getCrep_result() + ",");
//	
//		System.out.println();
//	}
//		
		
		
		
		
		
		//�ק�
//		Course_CommentVO ccmtVO3 = new Course_CommentVO();
//		ccmtVO3.setCcmt_no(23000009);
//		ccmtVO3.setCrs_no(21000002);
//		ccmtVO3.setMem_no(11000002);
//		ccmtVO3.setCcmt_cont("cccccccccc");
//		ccmtVO3.setCcmt_status("1");
//		ccmtVO3.setCcmt_posttime(java.sql.Date.valueOf("2017-04-19"));
//		dao.update(ccmtVO3);
		
//		dao.delete(23000002);
		
		
		
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("ccmt_no", new String[] {"23000001"});
		map.put("crs_no", new String[] {"21000001"});
		map.put("mem_no", new String[] {"11000001"});
		map.put("ccmt_cont", new String[] {"How to printing Hello World?"});
		map.put("ccmt_status", new String[] {"1"});
		map.put("ccmt_posttime", new String[] {"2016-12-19"});

Set set = map.keySet();
		
		Iterator it = set.iterator();
		while(it.hasNext()){
			Object mykey = it.next();
			System.out.println(mykey + "=" + map.get(mykey));
		}

	
	
	}




}
