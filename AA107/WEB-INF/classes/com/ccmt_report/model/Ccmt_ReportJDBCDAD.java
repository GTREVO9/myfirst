package com.ccmt_report.model;





import java.util.*;
import java.sql.*;
import java.sql.Date;


public class Ccmt_ReportJDBCDAD implements Ccmt_ReportDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107";
	String passwd = "123";
	
	
	
	private static final String GET_ONE_STMT =
			"SELECT crep_no, ccmt_no, mem_no, crep_title, crep_cnt, crep_status, adm_no, crep_result FROM ccmt_report where crep_no = ?";
			
	private static final String GET_ALL_STMT =
			"SELECT crep_no, ccmt_no, mem_no, crep_title, crep_cnt, crep_status, adm_no, crep_result FROM ccmt_report order by crep_no";
	
	private static final String INSERT_STMT =
			"INSERT INTO ccmt_report (crep_no, ccmt_no, mem_no, crep_title, crep_cnt, crep_status, adm_no, crep_result) VALUES (ccmt_report_seq.NEXTVAL, ?, ?, ?, ?, ?,?,?)";		
	
//	private static final String INSERT_STMT =
//			"INSERT INTO ccmt_report (crep_no, ccmt_no, mem_no, crep_title, crep_cnt, crep_status, crep_result) VALUES (ccmt_report_seq.NEXTVAL, ?, ?, ?, ?,?,?)";		
//	
	
	private static final String UPDATE = 
			"UPDATE ccmt_report set ccmt_no=?, mem_no=?,crep_title=?,crep_cnt=?,crep_status=?,adm_no=?,crep_result=? where crep_no = ?";
			
	private static final String DELETE =
			"DELETE FROM ccmt_report where crep_no = ?";


	


	@Override
	public void insert(Ccmt_ReportVO ccmtRptVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(Integer crep_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, crep_no);
			
			pstmt.executeUpdate();
			
			System.out.println("Delete sucessful!!!!");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, crep_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
					
				//ccmtVo �]�٬�Domain objects
				
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
			
		return ccmtRptVO;
			
	}

//===============================================================================

	@Override
	public List<Ccmt_ReportVO> getAll() {
		// TODO Auto-generated method stub
		List<Ccmt_ReportVO> list = new ArrayList<Ccmt_ReportVO>();
		Ccmt_ReportVO ccmtRptVO = null;
		
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
//=====================================================================================



	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Ccmt_ReportJDBCDAD dao = new Ccmt_ReportJDBCDAD();

		//�s�W
		Ccmt_ReportVO ccmtRptVO3 = new Ccmt_ReportVO();
		
		ccmtRptVO3.setCcmt_no(23000002);
		ccmtRptVO3.setMem_no(11000010);
		ccmtRptVO3.setCrep_title("aaaaaa");
		ccmtRptVO3.setCrep_cnt("aaaaaaaa");
		ccmtRptVO3.setCrep_status("1");
		ccmtRptVO3.setAdm_no(13000005);
		ccmtRptVO3.setCrep_result("aaaaaaaaa");
		dao.insert(ccmtRptVO3);
		
		
		//�ק�

//		Ccmt_ReportVO ccmtRptVO3 = new Ccmt_ReportVO();
//		ccmtRptVO3.setCrep_no(81000009);
//		ccmtRptVO3.setCcmt_no(23000001);
//		ccmtRptVO3.setMem_no(11000005);
//		ccmtRptVO3.setCrep_title("JavaBeans");
//		ccmtRptVO3.setCrep_cnt("How to Using JavaBeans?");
//		ccmtRptVO3.setCrep_status("0");
//		ccmtRptVO3.setAdm_no(13000002);
//		ccmtRptVO3.setCrep_result("its trouble");	
//		dao.update(ccmtRptVO3);
//				
		
		//�R��
//		dao.delete(81000009);
		
		//�d�߳��
//		Ccmt_ReportVO ccmtRptVO3 = dao.findByPrimaryKey(81000001);	
//		System.out.print(ccmtRptVO3.getCrep_no() + ",");
//		System.out.print(ccmtRptVO3.getCcmt_no() + ",");
//		System.out.print(ccmtRptVO3.getMem_no() + ",");
//		System.out.print(ccmtRptVO3.getCrep_title() + ",");
//		System.out.print(ccmtRptVO3.getCrep_cnt() + ",");
//		System.out.println(ccmtRptVO3.getCrep_status());
//		System.out.print(ccmtRptVO3.getAdm_no() + ",");
//		System.out.println(ccmtRptVO3.getCrep_result());
//		
//		
//		System.out.println("---------------------------------------------------------------------------------");


		//�d�ߥ���
//		List<Ccmt_ReportVO> list = dao.getAll();
//		for(Ccmt_ReportVO cReport : list){
//		System.out.print(cReport.getCrep_no() + ",");
//		System.out.print(cReport.getCcmt_no() + ",");
//		System.out.print(cReport.getMem_no() + ",");
//		System.out.print(cReport.getCrep_title() + ",");
//		System.out.print(cReport.getCrep_cnt() + ",");
//		System.out.println(cReport.getCrep_status());
//		System.out.print(cReport.getAdm_no() + ",");
//		System.out.print(cReport.getCrep_result());
//		System.out.println();		
//	}
	
	
	}

}
