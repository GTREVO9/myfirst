<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course_comment.model.*"%>


<jsp:useBean id="courseSvc" scope="page" class="com.course.model.CourseService" />





<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>



<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
  <font color='red'>請修正以下錯誤:
  <ul>
  <c:forEach var="message" items="${errorMsgs}">
    <li>${message}</li>
  </c:forEach>
  </ul>
  </font>
</c:if>


  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Course/course.do" >
       <b><font color=orange>選擇討論區:</font></b>
       <select size="1" name="crs_no">
         <c:forEach var="courseVO" items="${courseSvc.all}" > 
          <option value="${courseVO.crs_no}">${courseVO.crs_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="listCcmt_ByCourse_A">
     </FORM>
  </li>


<table border='1' bordercolor='#CCCCFF' width='800'>



	<tr>
		<th>課程編號</th>
		<th>課程名稱</th>
		
		
		<th>查詢課程討論區</th>
		<th>查詢課程討論區</th>
	</tr>
		
		
		
		<c:forEach var="courseVO" items="${courseSvc.all}">
		<tr align='center' valign='middle'>
		
			<td>${courseVO.crs_no}</td>
			<td>${courseVO.crs_name}</td>
			
			
			<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Course/course.do">
			    <input type="submit" value="送出查詢"> 
			    <input type="hidden" name="crs_no" value="${courseVO.crs_no}">
			    <input type="hidden" name="action" value="listCcmt_ByCourse_A">
			
			</td></FORM>
					
			<td>${courseVO.crs_name}</td>
				
		</tr>
	</c:forEach>

</table>


<ul>
	<li>
		 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course_comment/course_comment.do" name="form1">
					<b><font color=blue>萬用複合查詢:</font></b> <br>
					 
					 <b>輸入員工編號:</b>
       				 <input type="text" name="ccmt_no" value="23000001"><br>
       				 
        	
        
        			 <b><font color=orange>選擇討論區:</font></b>
      				 <select size="1" name="crs_no">
         					<c:forEach var="courseVO" items="${courseSvc.all}" > 
          						<option value="${courseVO.crs_no}">${courseVO.crs_name}
         					</c:forEach>   
       				</select><br>
      
    	<br>
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listCourse_Comment_ByCompositeQuery">
				
		</FORM>
	</li>
</ul>






<table border='1' bordercolor='#CCCCFF' width='800'>



	<tr>
		<th>課程編號</th>
		<th>課程名稱</th>
		
		
		<th>查詢課程討論區</th>
		<th>查詢課程討論區</th>
	</tr>
		
		
		
		<c:forEach var="courseVO" items="${courseSvc.all}">
		<tr align='center' valign='middle'>
		
			<td>${courseVO.crs_no}</td>
			<td>${courseVO.crs_name}</td>
			
			
			<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course_comment/course_comment.do">
			    <input type="submit" value="送出查詢"> 
			    <input type="hidden" name="crs_no" value="${courseVO.crs_no}">
			   
			    <input type="hidden" name="action" value="listCourse_Comment_ByCompositeQuery">
			
			</td></FORM>			
			<td>${courseVO.crs_name}</td>		
		</tr>
	</c:forEach>

</table>





<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
<ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/wish_report/wish_report.do" name="form1">
        <b><font color=blue>萬用複合查詢:許願池檢舉</font></b> <br>                                        		        
       
        
        
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listWish_Report_ByCompositeQuery">
     </FORM>
  </li>
</ul>







</body>
</html>