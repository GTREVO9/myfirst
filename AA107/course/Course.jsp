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



<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
  <font color='red'>�Эץ��H�U���~:
  <ul>
  <c:forEach var="message" items="${errorMsgs}">
    <li>${message}</li>
  </c:forEach>
  </ul>
  </font>
</c:if>


  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Course/course.do" >
       <b><font color=orange>��ܰQ�װ�:</font></b>
       <select size="1" name="crs_no">
         <c:forEach var="courseVO" items="${courseSvc.all}" > 
          <option value="${courseVO.crs_no}">${courseVO.crs_no}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="listCcmt_ByCourse_A">
     </FORM>
  </li>


<table border='1' bordercolor='#CCCCFF' width='800'>



	<tr>
		<th>�ҵ{�s��</th>
		<th>�ҵ{�W��</th>
		
		
		<th>�d�߽ҵ{�Q�װ�</th>
		<th>�d�߽ҵ{�Q�װ�</th>
	</tr>
		
		
		
		<c:forEach var="courseVO" items="${courseSvc.all}">
		<tr align='center' valign='middle'>
		
			<td>${courseVO.crs_no}</td>
			<td>${courseVO.crs_name}</td>
			
			
			<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Course/course.do">
			    <input type="submit" value="�e�X�d��"> 
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
					<b><font color=blue>�U�νƦX�d��:</font></b> <br>
					 
					 <b>��J���u�s��:</b>
       				 <input type="text" name="ccmt_no" value="23000001"><br>
       				 
        	
        
        			 <b><font color=orange>��ܰQ�װ�:</font></b>
      				 <select size="1" name="crs_no">
         					<c:forEach var="courseVO" items="${courseSvc.all}" > 
          						<option value="${courseVO.crs_no}">${courseVO.crs_name}
         					</c:forEach>   
       				</select><br>
      
    	<br>
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="listCourse_Comment_ByCompositeQuery">
				
		</FORM>
	</li>
</ul>






<table border='1' bordercolor='#CCCCFF' width='800'>



	<tr>
		<th>�ҵ{�s��</th>
		<th>�ҵ{�W��</th>
		
		
		<th>�d�߽ҵ{�Q�װ�</th>
		<th>�d�߽ҵ{�Q�װ�</th>
	</tr>
		
		
		
		<c:forEach var="courseVO" items="${courseSvc.all}">
		<tr align='center' valign='middle'>
		
			<td>${courseVO.crs_no}</td>
			<td>${courseVO.crs_name}</td>
			
			
			<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course_comment/course_comment.do">
			    <input type="submit" value="�e�X�d��"> 
			    <input type="hidden" name="crs_no" value="${courseVO.crs_no}">
			   
			    <input type="hidden" name="action" value="listCourse_Comment_ByCompositeQuery">
			
			</td></FORM>			
			<td>${courseVO.crs_name}</td>		
		</tr>
	</c:forEach>

</table>





<%-- �U�νƦX�d��-�H�U���-�i�H�N�W�� --%>
<ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/wish_report/wish_report.do" name="form1">
        <b><font color=blue>�U�νƦX�d��:�\�@�����|</font></b> <br>                                        		        
       
        
        
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="listWish_Report_ByCompositeQuery">
     </FORM>
  </li>
</ul>







</body>
</html>