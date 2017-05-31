package com.wcmt_report.model;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.hibernate.*;

import com.compositequery.wcmt_report.hibernate.CompositeQuery_Wcmt_Report;


import hibernate.util.HibernateUtil;


public class Wcmt_ReportHibernateDAO implements Wcmt_ReportDAO_interface{

	
	
	
	private static final String GET_ALL_STMT = "from Wcmt_ReportVO order by wcrep_no";
	
	
	@Override
	public void insert(Wcmt_ReportVO wcmtRptVO) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(wcmtRptVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	
	
	}

	@Override
	public void update(Wcmt_ReportVO wcmtRptVO) {
		// TODO Auto-generated method stub
	
	
	
	
	
	}

	@Override
	public void delete(Integer wcrep_no) {
		// TODO Auto-generated method stub
		
	
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        【此時多方(宜)可採用HQL刪除】
//			Query query = session.createQuery("delete EmpVO where empno=?");
//			query.setParameter(0, empno);
//			System.out.println("刪除的筆數=" + query.executeUpdate());

//        【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
//			EmpVO empVO = new EmpVO();
//			empVO.setEmpno(empno);
//			session.delete(empVO);

//        【此時多方不可(不宜)採用cascade聯級刪除】
//        【多方emp2.hbm.xml如果設為 cascade="all"或 cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除】
			Wcmt_ReportVO wcmtRptVO = (Wcmt_ReportVO) session.get(Wcmt_ReportVO.class, wcrep_no);
			session.delete(wcmtRptVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	
	
	
	}

	@Override
	public Wcmt_ReportVO findByPrimaryKey(Integer wcrep_no) {
		// TODO Auto-generated method stub
		
		Wcmt_ReportVO wcmtRptVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			wcmtRptVO = (Wcmt_ReportVO) session.get(Wcmt_ReportVO.class, wcrep_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
		return wcmtRptVO;	

	}

	@Override
	public List<Wcmt_ReportVO> getAll() {
		// TODO Auto-generated method stub
		
		List<Wcmt_ReportVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
		return list;	
	
	
	
	
	}

	@Override
	public List<Wcmt_ReportVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		
		List<Wcmt_ReportVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			list = CompositeQuery_Wcmt_Report.getAllC(map);
			
			session.getTransaction().commit();
			
			
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		
Wcmt_ReportHibernateDAO dao = new Wcmt_ReportHibernateDAO();
		
		//查詢-findByPrimaryKey(多方wcmt_report.xml必須設為lazy="false")(優!!)
		
		Wcmt_ReportVO wcmtRptVO = dao.findByPrimaryKey(83000001);
		System.out.print(wcmtRptVO.getWcrep_no() + ",");
		System.out.print(wcmtRptVO.getWcmt_no() + ",");
		System.out.print(wcmtRptVO.getMem_no() + ",");
		System.out.print(wcmtRptVO.getWcrep_title() + ",");
		System.out.print(wcmtRptVO.getWcrep_cont() + ",");
		System.out.println(wcmtRptVO.getWcrep_time());
		System.out.print(wcmtRptVO.getWcrep_status() + ",");
		System.out.println(wcmtRptVO.getAdm_no());
		System.out.println(wcmtRptVO.getWcrep_result());
		
		//查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//		List<Wcmt_ReportVO> list = dao.getAll();
//		for(Wcmt_ReportVO wReport : list){
//			System.out.print(wReport.getWcrep_no() + ",");
//			System.out.print(wReport.getWcmt_no() + ",");
//			System.out.print(wReport.getMem_no() + ",");
//			System.out.print(wReport.getWcrep_title() + ",");
//			System.out.print(wReport.getWcrep_cont() + ",");
//			System.out.println(wReport.getWcrep_time());
//			System.out.print(wReport.getWcrep_status() + ",");
//			System.out.print(wReport.getAdm_no());
//			System.out.print(wReport.getWcrep_result());
//			System.out.println();			
//			
//		}
//	
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("wcrep_no", new String[] { "83000001" });
//		map.put("wcmt_no", new String[] { "32000001" });
//		map.put("mem_no", new String[] { "11000001" });
//		map.put("wcrep_title", new String[] { "JQuery" });
//		map.put("wcrep_cont", new String[] { "its teach have a big head" });
//		map.put("wcrep_time", new String[] { "2017-04-17" });
//		map.put("wcrep_status", new String[] { "0" });
//		map.put("adm_no", new String[] { "13000001" });
//		map.put("wcrep_result", new String[] { "not report" });
//		map.put("action", new String[] { "getXXX" }); //注意Map裡面會含有action的key
//							
//		Set set = map.keySet();
//		
//		Iterator it = set.iterator();
//		while(it.hasNext()){
//			Object mykey = it.next();
//			System.out.println(mykey + "=" + map.get(mykey));
//			// 注意以下三行的寫法 (優!)			
//			System.out.println();
//		}
			
        //新增
//		Wcmt_ReportVO wcmtRptVO = new Wcmt_ReportVO();	
//		wcmtRptVO.setWcmt_no(32000001);
//		wcmtRptVO.setMem_no(11000010);
//		wcmtRptVO.setWcrep_title("JavaBeans");
//		wcmtRptVO.setWcrep_cont("How to Using JavaBeans?");
//		wcmtRptVO.setWcrep_time(java.sql.Date.valueOf("2005-01-01"));
//		wcmtRptVO.setWcrep_status("0");
//		wcmtRptVO.setAdm_no(13000004);
//		wcmtRptVO.setWcrep_result("its trouble");
//		dao.insert(wcmtRptVO);
//		
		
		//刪除(小心cascade - 多方wcmt_report.hbm.xml如果設為cascade="all"
		// 或 cascade="delete"將會刪除所有相關資料-包括所屬部門與部門的其他員工將會一拼刪除)
//		dao.delete(83000006);

		
		
		
		
		
	
	
	
	
	
	}
	
	
	

}
