<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wis.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    WisDAO_interface dao = new WisDAO_JNDI();
    List<WisVO> list = dao.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有許願池 - listAllWis.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有許願池- listAllWis.jsp</h3>
		<a href="wis_selectPage.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>許願池編號</th>
		<th>會員編號</th>
		<th>許願標題</th>
		<th>許願內容</th>
		<th>標籤分類編號</th>
		<th>許願類型</th>	
		<th>發起日期</th>	
		<th>結束日期</th>	
		<th>當前人氣</th>	
		<th>許願狀態</th>	
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="wisVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(wisVO.wis_no==param.wis_no) ? 'bgcolor=#cfc':''}>
			<td>${wisVO.wis_no}</td>
			<td>${wisVO.mem_no}</td>
			<td>${wisVO.wis_title}</td>
<!-- 			wis_cnt -->
			<td width="300">${wisVO.wis_cnt}</td>
<!-- 			wis_cnt -->
			<td>${wisVO.cat_no}</td>
			<td>${wisVO.wis_to}</td>
			<td>${wisVO.start_date}</td>
			<td>${wisVO.end_date}</td>
			<td>${wisVO.wis_like}</td>
			<td>${wisVO.wis_status}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/wis/wis.do">
			    <input type="submit" value="修改">
			    <input type="hidden" name="wis_no" value="${wisVO.wis_no}">
				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">		   
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> 
			    <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/wis/wis.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="wis_no" value="${wisVO.wis_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
			
			
			<td id="titleCont">
					<a href="<%=request.getContextPath()%>/wish_report/addWish_Report.jsp?wis_no=${wisVO.wis_no}&wis_cnt=${wisVO.wis_cnt}&whichPage=<%=whichPage%>&requestURL=<%=request.getServletPath()%>" data-toggle="modal">檢舉</a>
																
			</td>
			
			<td id="titleCont">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/wish_report/addWish_Report.jsp">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="wis_no" value="${wisVO.wis_no}">
			    <input type="hidden" name="wis_cnt" value="${wisVO.wis_cnt}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    </FORM>							
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

<br>本網頁的路徑:<br><b>

   <font color=blue>request.getContextPath():</font> <%= request.getContextPath()%><br>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>


</body>
</html>
