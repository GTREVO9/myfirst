package com.compositequery_wish_report;


import java.util.*;

import com.compositequery_course_comment.CompositeQuery_Course_Comment;
public class CompositeQuery_Wish_Report {

		
	public static String get_WishReport_Condition(String columnName ,String value){
	String bCondition = null;
		
		if("wrep_no".equals(columnName) || "wis_no".equals(columnName) || "mem_no".equals(columnName) || "adm_no".equals(columnName))//用於其他
				bCondition = columnName + "=" + value;
		else if("wrep_title".equals(columnName) || "wrep_cont".equals(columnName) || "wrep_status".equals(columnName) || "wrep_result".equals(columnName))
				bCondition = columnName + " like '%" + value + "%' ";
		else if("wrep_time".equals(columnName))
				bCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		
		
		return bCondition + " ";
	
	}

	public static String getWishReport_WhereCondition(Map<String, String[]> map){
		
		Set<String> keys = map.keySet();
		StringBuffer WishReportCondition = new StringBuffer();
		
		int count = 0;
		
		for(String key : keys){
			String value = map.get(key)[0];
		if(value != null && value.trim().length() != 0 && !"action".equals(key)){
			count++;
			String bCondition = get_WishReport_Condition(key, value.trim());
			
			if(count == 1)
				WishReportCondition.append(" where " + bCondition);
			else
				WishReportCondition.append(" and " + bCondition);
			
			System.out.println("有送出查詢資料的欄位數count = " + count);
		}	
		
		}
		
		
		
		return WishReportCondition.toString();
	}
	



	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//配合req.getPara,eterMap()方法回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("wrep_no", new String[] {"82000001"});
		map.put("wis_no", new String[] {"31000001"});
		map.put("mem_no", new String[] {"11000001"});
		map.put("wrep_title", new String[] {"JQuery"});
		map.put("wrep_cont", new String[] {"I wnat to learn JQuery"});
		map.put("wrep_time", new String[] {"2016-12-31"});
		map.put("adm_no", new String[] {"13000001"});
		map.put("wrep_status", new String[] {"2"});
		map.put("wrep_result", new String[] {"its not wrong"});
		map.put("action", new String[] {"getXXX"});//注意Map裡面會含有action的key

		String finalSQL = "select * from wish_Report " 
				  + CompositeQuery_Wish_Report.getWishReport_WhereCondition(map)
				  + "order by crep_no";
		System.out.println("●●finalSQL = " + finalSQL);

	}
	}
