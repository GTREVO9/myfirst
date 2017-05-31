<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wis.model.*"%>
<%@ page import="java.util.*"%>

<% 
// 	Map<String, String> map = new LinkedHashMap<String, String>();
// 	map.put("想學", "0");
// 	map.put("想教", "1");	
// 	pageContext.setAttribute("map0", map);




%>

<html>
<head>
<title>課程許願池 - addWis.jsp</title>
<script type="text/JavaScript" src="../ckeditor/ckeditor.js"></script>
</head>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='500'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><a href="wis_selectPage.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1"> 回首頁 </a></td></tr></table>

<h4>分類資料:<font color=red><b>*</b></font>為必填欄位</h4>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message.value}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="wis.do" name="form1" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="mem_no" size="45"
			 value="${param.mem_no}"/></td><td>${errorMsgs.mem_no}</td>
	</tr>
	<tr>
		<td>許願標題:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="wis_title" size="45"
			 value="${param.wis_title}"/></td><td>${errorMsgs.wis_title}</td>
	</tr>
	<tr>
		<td>許願內容:<font color=red><b>*</b></font></td>
		<td><textarea rows="30" cols="50" name="wis_cnt">${errorMsgs.wis_cnt}</textarea></td>
		<td>${errorMsgs.wis_cnt}</td>
	</tr>
	<tr>
		<td>標籤分類:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="cat_no" size="45"
			 value="${param.cat_no}"/></td><td>${errorMsgs.cat_no}</td>
	</tr>
	<tr>
		<td>許願類型:<font color=red><b>*</b></font></td>
		<td>
		<select name="wis_to">
        	<c:forEach var="map0_item" items="${map0}" > 
          	<option value="${map0_item.value}" ${(map0_item.value== param.adm_status) ? 'selected':'' }>${map0_item.key}
         	</c:forEach>
         </select>
         </td>
	</tr>
	<tr>
		<td>發起日期:<font color=red><b>*</b></font></td>
		<td><input type="date" name="start_date" size="45"
			 value="${param.start_date}"/></td><td>${errorMsgs.start_date}</td>
	</tr>
	<tr>
		<td>結束日期:<font color=red><b>*</b></font></td>
		<td><input type="date" name="end_date" size="45"
			 value="${param.end_date}"/></td><td>${errorMsgs.end_date}</td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>

</body>

</html>

<script type="text/javascript">
    window.onload = function()
    {
        CKEDITOR.replace( 'wis_cnt' );
    };
</script>

