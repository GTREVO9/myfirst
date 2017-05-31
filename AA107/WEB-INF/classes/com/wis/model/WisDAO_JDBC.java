package com.wis.model;

import java.util.*;
import java.io.*;
import java.sql.*;

public class WisDAO_JDBC implements WisDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	 String userId = "AA107";
//	String userId = "FORWU";
	String userPsw = "123";

	private static final String INSERT_STMT = "INSERT INTO WISH (WIS_no, MEM_no, WIS_title, WIS_cnt, CAT_no, WIS_to, start_date, end_date, WIS_like, WIS_status) "
			+ "VALUES (WIS_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM WISH where WIS_no = ?";
	private static final String UPDATE = "UPDATE WISH set MEM_no=?, WIS_title=?, WIS_cnt=?, CAT_no=?, WIS_to=?, start_date=?, end_date=?, WIS_like=?, WIS_status=? where WIS_no = ?";
	private static final String GET_ONE_STMT = "SELECT WIS_no, MEM_no, WIS_title, WIS_cnt, CAT_no, WIS_to, start_date, end_date, WIS_like, WIS_status FROM WISH where WIS_no = ?";
	private static final String GET_ALL_STMT = "SELECT WIS_no, MEM_no, WIS_title, WIS_cnt, CAT_no, WIS_to, start_date, end_date, WIS_like, WIS_status FROM WISH order by WIS_no";

	@Override
	public void insert(WisVO wisVO) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPsw);
			pstmt = conn.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, wisVO.getMem_no());
			pstmt.setString(2, wisVO.getWis_title());
			// =========================================
			// Reader reader = new FileReader(wisVO.getWis_cnt());
			// pstmt.setCharacterStream(3, reader);

			pstmt.setString(3, wisVO.getWis_cnt());
			// =========================================
			pstmt.setInt(4, wisVO.getCat_no());
			pstmt.setString(5, wisVO.getWis_to());
			pstmt.setDate(6, wisVO.getStart_date());
			pstmt.setDate(7, wisVO.getEnd_date());
			pstmt.setInt(8, wisVO.getWis_like());
			pstmt.setString(9, wisVO.getWis_status());

			pstmt.executeQuery();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Unable to load database. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// throw to whom?? to those who call this Servlet, in this case,
			// it's main method
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer wis_no) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPsw);
			pstmt = conn.prepareStatement(DELETE);

			pstmt.setInt(1, wis_no);

			pstmt.executeQuery();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Unable to load database. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(WisVO wisVO) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPsw);
			pstmt = conn.prepareStatement(UPDATE);

			pstmt.setInt(1, wisVO.getMem_no());
			pstmt.setString(2, wisVO.getWis_title());
			pstmt.setString(3, wisVO.getWis_cnt());
			pstmt.setInt(4, wisVO.getCat_no());
			pstmt.setString(5, wisVO.getWis_to());
			pstmt.setDate(6, wisVO.getStart_date());
			pstmt.setDate(7, wisVO.getEnd_date());
			pstmt.setInt(8, wisVO.getWis_like());
			pstmt.setString(9, wisVO.getWis_status());
			pstmt.setInt(10, wisVO.getWis_no());

			pstmt.executeQuery();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Unable to load database. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public WisVO findByPrimaryKey(Integer wis_no) {

		WisVO wisVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPsw);
			pstmt = conn.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, wis_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				wisVO = new WisVO();

				wisVO.setWis_no(rs.getInt("wis_no"));
				wisVO.setMem_no(rs.getInt("mem_no"));
				wisVO.setWis_title(rs.getString("wis_title"));
				wisVO.setWis_cnt(readString(rs.getClob("wis_cnt")));
				wisVO.setCat_no(rs.getInt("cat_no"));
				wisVO.setWis_to(rs.getString("wis_to"));
				wisVO.setStart_date(rs.getDate("start_date"));
				wisVO.setEnd_date(rs.getDate("end_date"));
				wisVO.setWis_like(rs.getInt("wis_like"));
				wisVO.setWis_status(rs.getString("wis_status"));

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Unable to load database. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (IOException ie) {
			throw new RuntimeException("An IOException occured. " + ie.getMessage());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return wisVO;
	}

	@Override
	public List<WisVO> getAll() {

		List<WisVO> list = new ArrayList<WisVO>();
		WisVO wisVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPsw);
			pstmt = conn.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				wisVO = new WisVO();

				wisVO.setWis_no(rs.getInt("wis_no"));
				wisVO.setMem_no(rs.getInt("mem_no"));
				wisVO.setWis_title(rs.getString("wis_title"));
				wisVO.setWis_cnt(readString(rs.getClob("wis_cnt")));
				wisVO.setCat_no(rs.getInt("cat_no"));
				wisVO.setWis_to(rs.getString("wis_to"));
				wisVO.setStart_date(rs.getDate("start_date"));
				wisVO.setEnd_date(rs.getDate("end_date"));
				wisVO.setWis_like(rs.getInt("wis_like"));
				wisVO.setWis_status(rs.getString("wis_status"));

				list.add(wisVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Unable to load database. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (IOException ie) {
			throw new RuntimeException("An IOException occured. " + ie.getMessage());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		WisDAO_JDBC dao = new WisDAO_JDBC();

		// INSERT
		WisVO wisVO1 = new WisVO();
		wisVO1.setMem_no(11000005);
		wisVO1.setWis_title("我想吃彗星炒飯!!!");
		try {
			String wisCnt = readFileToString("items/RM.txt");
			wisVO1.setWis_cnt(wisCnt);
		} catch (IOException ie) {
			ie.printStackTrace();
			ie.getMessage();
		}
		wisVO1.setCat_no(102);
		wisVO1.setWis_to("0");
		wisVO1.setStart_date(java.sql.Date.valueOf("2017-4-20"));
		wisVO1.setEnd_date(java.sql.Date.valueOf("2017-5-20"));
		wisVO1.setWis_like(10);
		wisVO1.setWis_status("0");
		dao.insert(wisVO1);

		// // DELETE
//		 dao.delete(31000007);

		// UPDATE
		// WisVO wisVO2 = new WisVO();
		// wisVO2.setWis_no(31000001);
		// wisVO2.setMem_no(11000002);
		// wisVO2.setWis_title("我想學做彗星炒飯!!!");
		// try {
		// String wisCnt = readFileToString("items/RM.txt");
		// wisVO2.setWis_cnt(wisCnt);
		// } catch (IOException ie) {
		// ie.printStackTrace();
		// ie.getMessage();
		// }
		// wisVO2.setCat_no(102);
		// wisVO2.setWis_to("0");
		// wisVO2.setStart_date(java.sql.Date.valueOf("2017-4-20"));
		// wisVO2.setEnd_date(java.sql.Date.valueOf("2017-5-20"));
		// wisVO2.setWis_like(10);
		// wisVO2.setWis_status("1");
		//
		// dao.update(wisVO2);

		// SELECT
		// WisVO wisVO3 = dao.findByPrimaryKey(31000001);
		// System.out.print(wisVO3.getWis_no() + ",");
		// System.out.print(wisVO3.getMem_no() + ",");
		// System.out.print(wisVO3.getWis_title() + ",");
		// System.out.print(wisVO3.getWis_cnt() + ",");
		// System.out.print(wisVO3.getCat_no() + ",");
		// System.out.print(wisVO3.getWis_to() + ",");
		// System.out.print(wisVO3.getStart_date() + ",");
		// System.out.print(wisVO3.getEnd_date() + ",");
		// System.out.print(wisVO3.getWis_like() + ",");
		// System.out.println(wisVO3.getWis_status());
		// System.out.println("------------------------------");

		// // SELECT ALL
		 List<WisVO> list = dao.getAll();
		 for (WisVO aWis : list) {
		 System.out.print(aWis.getWis_no() + ",");
		 System.out.print(aWis.getMem_no() + ",");
		 System.out.print(aWis.getWis_title() + ",");
		 System.out.print(aWis.getWis_cnt() + ",");
		 System.out.print(aWis.getCat_no() + ",");
		 System.out.print(aWis.getWis_to() + ",");
		 System.out.print(aWis.getStart_date() + ",");
		 System.out.print(aWis.getEnd_date() + ",");
		 System.out.print(aWis.getWis_like() + ",");
		 System.out.println(aWis.getWis_status());
		 System.out.println("------------------------------");
		 }

	}

	public static String readString(Clob clob) throws IOException, SQLException {
		BufferedReader br = new BufferedReader(clob.getCharacterStream());
		StringBuilder sb = new StringBuilder();
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}

	public static String readFileToString(String path) throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder sb = new StringBuilder();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();
		return sb.toString();
	}

}
