package com.compositequery.wcmt_report.hibernate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


import com.wcmt_report.model.Wcmt_ReportVO;

import hibernate.util.HibernateUtil;

//1.萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
//2.為了避免影響效能:所以動態產生萬用SQL的部分，本範例無意採用MetaData的方式，
//		也只針對個別的Table自行視需要而個別製作之



public class CompositeQuery_Wcmt_Report {
	
	public static Criteria get_Wcmt_Report_anyDB(Criteria query, String columnName,String value){
			
		if ("wcrep_no".equals(columnName) || "wcmt_no".equals(columnName) || "mem_no".equals(columnName)  || "adm_no".equals(columnName))    //用於Integer
		query.add(Restrictions.eq(columnName, new Integer(value)));    
	   
	else if ("wcrep_title".equals(columnName) || "wcrep_cont".equals(columnName) || "wcrep_status".equals(columnName) || "wcrep_result".equals(columnName))  //用於varchar
		query.add(Restrictions.like(columnName, "%"+value+"%"));
	else if ("wcrep_time".equals(columnName))                           //用於date
		query.add(Restrictions.eq(columnName, java.sql.Date.valueOf(value)));
		
		return query;
				
	}
	


	public static List<Wcmt_ReportVO> getAllC(Map<String, String[]> map) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<Wcmt_ReportVO> list = null;
		try {
	
			Criteria query = session.createCriteria(Wcmt_ReportVO.class);
			Set<String> keys = map.keySet();
			int count = 0;
			
			for (String key : keys) {
				String value = map.get(key)[0];
				if (value!=null && value.trim().length()!=0 && !"action".equals(key)) {
					count++;					
					query = get_Wcmt_Report_anyDB(query, key, value);
					System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
			
			query.addOrder( Order.asc("wcrep_no") );
			list = query.list();
			
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			throw ex; //System.out.println(ex.getMessage());
		} finally {
			session.close();
//			HibernateUtil.getSessionFactory().close();
		}
		
		return list;
	
	}
	


	
	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("wcrep_no", new String[] { "83000001" });
		map.put("wcmt_no", new String[] { "32000001" });
		map.put("mem_no", new String[] { "11000001" });
		map.put("wcrep_title", new String[] { "JQuery" });
		map.put("wcrep_cont", new String[] { "its teach have a big head" });
		map.put("wcrep_time", new String[] { "2017-04-17" });
		map.put("wcrep_status", new String[] { "0" });
		map.put("adm_no", new String[] { "13000001" });
		map.put("wcrep_result", new String[] { "not report" });
		map.put("action", new String[] { "getXXX" }); //注意Map裡面會含有action的key
		
		List<Wcmt_ReportVO> list = getAllC(map);
		for (Wcmt_ReportVO wReport : list) {
			System.out.print(wReport.getWcrep_no() + ",");
			System.out.print(wReport.getWcmt_no() + ",");
			System.out.print(wReport.getMem_no() + ",");
			System.out.print(wReport.getWcrep_title() + ",");
			System.out.print(wReport.getWcrep_cont() + ",");
			System.out.print(wReport.getWcrep_time() + ",");		
			System.out.print(wReport.getWcrep_status() + ",");
			System.out.print(wReport.getAdm_no() + ",");
			System.out.print(wReport.getWcrep_result() + ",");
			
			// 注意以下三行的寫法 (優!)			
			System.out.println();
		}
	
	}






}
