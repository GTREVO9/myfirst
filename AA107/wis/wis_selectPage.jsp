<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>ezShare Wish: Home</title>
</head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>ezShare Wish: Home</h3>
				<font color=red>( MVC )</font></td>
		</tr>
	</table>

	<p>This is the Home page for ezShare Wish: Home</p>


	<!-- 分成若干區塊 -->

	<h3>資料查詢:</h3>
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

	<ul>
		<li><a href='listAllWis.jsp'>List</a> all Wishes.</li>
		<br>
		<br>

		<li>
			<FORM METHOD="post" ACTION="wis.do">
				<b>輸入許願池編號 (如31000001):</b> <input type="text" name="wis_no">
				<input type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<jsp:useBean id="wisSvc" scope="page" class="com.wis.model.WisService" />

		<li>
			<FORM METHOD="post" ACTION="wis.do">
				<b>選擇許願池編號:</b> <select size="1" name="wis_no">
					<c:forEach var="wisVO" items="${wisSvc.all}">
						<option value="${wisVO.wis_no}">${wisVO.wis_no}
					</c:forEach>
				</select> <input type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="wis.do">
				<b>選擇許願者編號:</b> <select size="1" name="wis_no">
					<c:forEach var="wisVO" items="${wisSvc.all}">
						<option value="${wisVO.wis_no}">${wisVO.mem_no}
					</c:forEach>
				</select> <input type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</FORM>
		</li>
	</ul>


	<h3>許願池管理</h3>

	<ul>
		<li><a href='addWis.jsp'>Add</a> a new Wish.</li>
	</ul>

</body>

</html>
