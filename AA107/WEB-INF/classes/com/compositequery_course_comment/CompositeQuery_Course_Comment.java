package com.compositequery_course_comment;

import java.util.*;

public class CompositeQuery_Course_Comment {


	public static String get_Condition_ForOracle(String columnName, String value){
		
		String aCondition = null;
		
		if("ccmt_no".equals(columnName) || "crs_no".equals(columnName) || "mem_no".equals(columnName)) //用於其他
			aCondition = columnName + "=" + value;
		else if("ccmt_cont".equals(columnName) || "ccmt_status".equals(columnName)) //用於varchar
			aCondition = columnName + " like '%" + value + "%' ";
		else if("ccmt_posttime".equals(columnName))
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value +"'"; //用於Oracle的date
		return aCondition + " ";		
	}
	
	
	
	public static String get_WhereCondition(Map<String, String[]> map){
		
		Set<String> keys = map.keySet();
		
		StringBuffer whereCondition = new StringBuffer();
		
		int count = 0;
		
		for(String key : keys){
		String value = map.get(key)[0];
		if(value != null && value.trim().length() != 0 && !"action".equals(key)){
			count++;
			String aCondition = get_Condition_ForOracle(key, value.trim());
			
			if(count == 1)
				whereCondition.append(" where " + aCondition);
			else
				whereCondition.append(" and " + aCondition);
			
			System.out.println("有送出查詢資料的欄位數count = " + count);
		}
			
		}
		
		
		return whereCondition.toString();
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//配合req.getPara,eterMap()方法回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("ccmt_no", new String[] {"23000001"});
		map.put("crs_no", new String[] {"21000001"});
		map.put("mem_no", new String[] {"11000001"});
		map.put("ccmt_cont", new String[] {"How to printing Hello World?"});
		map.put("ccmt_status", new String[] {"1"});
		map.put("ccmt_posttime", new String[] {"2016-12-19"});
		map.put("action", new String[] {"getXXX"});//注意Map裡面會含有action的key

		String finalSQL = "select * from course_comment " 
				  + CompositeQuery_Course_Comment.get_WhereCondition(map)
				  + "order by ccmt_no";
	System.out.println("●●finalSQL = " + finalSQL);
	}

}
