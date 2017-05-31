<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wis.model.*"%>
<%@ page import="javax.imageio.*"%>
<%@ page import="java.awt.image.*"%>

<%-- 取出 Controller CatServlet.java已存入request的catVO物件--%>
<%
	WisVO wisVO = (WisVO) request.getAttribute("wisVO");

	// 	byte[] cat_img = catVo.getCat_img();
%>

<%-- <% try { --%>
// BufferedImage img = null; // img = ImageIO.read(new
ByteArrayInputStream(cat_img)); // } catch (IOException e) { //
e.printStackTrace(); // } // drawPicture(img);
<%-- %> --%>
<html>
<head>
<title>分類資料 - listOneCat.jsp</title>
</head>
<body bgcolor='white'>
	<table border='1' cellpadding='5' cellspacing='0' width='800'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>分類標籤資料 - ListOneAdm.jsp</h3> <a href="wis_selectPage.jsp"><img
					src="<%=request.getContextPath()%>/images/back1.gif" width="100"
					height="32" border="0">回首頁</a>
			</td>
		</tr>
	</table>

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
		<tr align='center' valign='middle'>
			<td>${wisVO.wis_no}</td>
			<td>${wisVO.mem_no}</td>
			<td>${wisVO.wis_title}</td>
			<td>${wisVO.wis_cnt}</td>
			<td>${wisVO.cat_no}</td>
			<td>${wisVO.wis_to}</td>
			<td>${wisVO.start_date}</td>
			<td>${wisVO.end_date}</td>
			<td>${wisVO.wis_like}</td>
			<td>${wisVO.wis_status}</td>
		</tr>
	</table>

</body>
</html>
