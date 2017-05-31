<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wis.model.*"%>
<%
	WisVO wisVO = (WisVO) request.getAttribute("wisVO"); 
%>
<html>
<head>
<title>課程許願池 - wis_updateInput.jsp</title>
<script type="text/JavaScript" src="../ckeditor/ckeditor.js"></script>
</head>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#cfc' align='center' valign='middle' height='20'>
		<td>
		<a href="wis_selectPage.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs_1}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="wis.do" name="form1" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>許願池編號:</td>
		<td><%=wisVO.getWis_no()%></td>
	</tr>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="mem_no" size="45" value="<%=wisVO.getMem_no()%>"/>
		</td><td>${errorMsgs.mem_no}</td>
	</tr>
	<tr>
		<td>許願池標題:</td>
		<td><input type="TEXT" name="wis_title" size="45" value="<%=wisVO.getWis_title()%>" /></td>
		<td>${errorMsgs.wis_title}</td>
	</tr>
	<tr>
		<td>許願內容:<font color=red><b>*</b></font></td>
		<td><textarea rows="30" cols="50" name="wis_cnt">${errorMsgs.wis_cnt}</textarea></td>
		<td>${errorMsgs.wis_cnt}</td>
	</tr>
	<tr>
		<td>標籤分類:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="cat_no" size="45" value="<%=wisVO.getCat_no()%>"/></td>
		<td>${errorMsgs.cat_no}</td>
	</tr>
	<tr>
		<td>許願類型:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="wis_to" size="45" value="<%=wisVO.getWis_to()%>"/></td>
		<td>${errorMsgs.wis_to}</td>
	</tr>
	<tr>
		<td>發起日期:<font color=red><b>*</b></font></td>
		<td><input type="date" name="start_date" size="45" value="<%=wisVO.getStart_date()%>"/></td>
		<td>${errorMsgs.start_date}</td>
	</tr>
	<tr>
		<td>結束日期:<font color=red><b>*</b></font></td>
		<td><input type="date" name="end_date" size="45" value="<%=wisVO.getEnd_date()%>"/></td>
		<td>${errorMsgs.end_date}</td>
	</tr>	
</table>
<br>

<input type="hidden" name="action" value="update">
<input type="hidden" name="wis_no" value="<%=wisVO.getWis_no()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--原送出修改的來源網頁路徑,從request取出後,再送給Controller準備轉交之用-->
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--只用於:listAllCat.jsp-->
<input type="submit" value="送出修改"></FORM>

</body>
</html>

<script type="text/javascript">
    window.onload = function()
    {
        CKEDITOR.replace( 'wis_cnt' );
    };
</script>
