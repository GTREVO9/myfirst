<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<%@ page import="java.util.*"%>
<%@ page import="com.wis.model.*"%>



<jsp:useBean id="listWish_Report_ByCompositeQuery" scope="request" type="java.util.List" />

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<%
    WisDAO_interface dao = new WisDAO_JNDI();
    List<WisVO> list = dao.getAll();
    pageContext.setAttribute("list",list);
%>

<html>

<head>
							<title>複合查詢 - listWish_Report_ByCompositeQuery.jsp</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

<style type="text/css">

#ccmtMemImg{ border-radius: 50%;  width:70px;  height:70px;}

.btnStyle {
    background-color: #337AB7;
    border-width: 5;
    border-color: #E9E9E9;
	color: #FFFFFF;
	position: relative;
	z-index: 1;
    cursor: hand;
    font-family: Tahoma,Georgia;
    font-size: 17px; font-weight: bold;}
}
</style>

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


<nav class="navbar navbar-default" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
						<span class="sr-only">選單切換</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">CSS可樂</a>
				</div>
				
				<!-- 手機隱藏選單區 -->
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<!-- 左選單 -->
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">關於我們</a></li>
						<li><a href="#">課程清單</a></li>
						<li><a href="#">討論區</a></li>
						<li><a href="#">關於我們</a></li>
					</ul>
				
					<!-- 搜尋表單 -->
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="請輸入關鍵字">
						</div>
						<button type="submit" class="btn btn-default">搜尋</button>
					</form>
				
					<!-- 右選單 -->
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">Amos 您好</a></li>
						<li><a href="#">登出</a></li>
						<li><a href="#">個人設定</a></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">繁體中文 <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">繁體中文</a></li>
								<li><a href="#">English</a></li>
								<li><a href="#">日本語</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- 手機隱藏選單區結束 -->
			</div>
		</nav>


			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-4">
						
							<div class="list-group">
								<a href="#" class="list-group-item active">清單項目 1</a>
								<a href="#" class="list-group-item">清單項目 2</a>
								<a href="#" class="list-group-item">清單項目 3</a>
							</div>


							<div class="list-group">
								<a href="#" class="list-group-item active">
									<h4 class="list-group-item-heading">標題</h4>
									<p class="list-group-item-text">內容文字</p>
								</a>
								<a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">標題</h4>
									<p class="list-group-item-text">內容文字</p>
								</a>
								<a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">標題</h4>
									<p class="list-group-item-text">內容文字</p>
								</a>
							</div>

							<div class="list-group">
								<a href="#" class="list-group-item active"><span class="badge">10</span>清單項目 1</a>
								<a href="#" class="list-group-item"><span class="badge">10</span>清單項目 2</a>
								<a href="#" class="list-group-item"><span class="badge">10</span>清單項目 3</a>
							</div>
					</div>
					<div class="col-xs-12 col-sm-8">
						
							<ol class="breadcrumb">
								<li>
									<a href="#">首頁</a>
								</li>
								<li>
									<a href="#">最新消息</a>
								</li>
								<li class="active">媽我上電視了</li>
							</ol>			
							
							<%@ include file="pages/page1_ByCompositeQuery.file" %> 	
					
							
							
							<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">討論區檢舉</h3>
					  </div>
					  <div class="panel-body">
					    
					    <table class="table table-hover">
					    	
					    	<thead>
					    		<tr>
					    			<th>檢舉者</th>
					    			<th>檢舉標題</th>
					    			<th>檢舉原因</th>				    			
					    			<th>許願池內容</th>
					    			<th>檢舉時間</th>
					    			<th>刪除內容</th>				    		
					    						    			
					    		</tr>
					    	</thead>
					    	<tbody>
					 					<c:forEach var="wishptVO" items="${listWish_Report_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					    		<tr >
					    			
					    			<td>
					    			
					    				<c:forEach var="memberVO" items="${memberSvc.all}">
					    					<c:if test="${memberVO.mem_no == wishptVO.mem_no}">
					    						<img src="<%= request.getContextPath()%>/MemberDBReader?mem_no=${memberVO.mem_no}"  id="ccmtMemImg"><br><br>
					    						<p memName>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${memberVO.mem_fname} </p>
					    					</c:if>
					    				</c:forEach>
					    			
					    			</td>
					    			<td >${wishptVO.wrep_title}</td>
					    			<td >${wishptVO.wrep_cont}</td>
					    			
					    		
					    			<td >
					    				<c:forEach var="wisVO" items="${list}">
											<c:if test="${wishptVO.wis_no == wisVO.wis_no}">			
														${wisVO.wis_cnt}
											</c:if>			
										</c:forEach>
									</td>
									
									<td>${wishptVO.wrep_time}</td>																							    							    								    							    								    			
					    			
									<td >					    			
					    				<c:forEach var="wisVO" items="${list}">
											<c:if test="${wishptVO.wis_no == wisVO.wis_no}">			
			
			  									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/wis/wis.do">
			    									<input type="submit" onclick="ccmtRptDelete()" value="刪除" class="btnStyle" >
			    									<input type="hidden" name="wis_no" value="${wisVO.wis_no}">
			    									<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    									<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    									<input type="hidden" name="action" value="delete_wis">
												</FORM>						
										</c:if>
													<%-- <a href="<%=request.getContextPath()%>/course_comment/course_comment.do?ccmt_no=${ccmtVO.ccmt_no}&action=delete_ccmt">${ccmtVO.ccmt_no}</a>	--%>																					
										</c:forEach>							    			
					    			</td>
					    							    		
					    		</tr>
					    		
					    		</c:forEach>			    		
					    	</tbody>
					    </table>
					    
					    <%@ include file="pages/page2_ByCompositeQuery.file" %>

					  </div>
					</div>
									
					</div>
				</div>
			</div>

<script type="text/javascript">
	
	function ccmtRptDelete(){
		return window.confirm("確定刪除");
	}
	
	</script>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>