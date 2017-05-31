<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ccmt_report.model.*"%>
<%@ page import="com.ccmt_report.controller.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.member.controller.*"%>
<%@ page import="com.course_comment.model.*"%>


<jsp:useBean id="listCourse_Comment_ByCompositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="courseSvc" scope="page" class="com.course.model.CourseService" />



<!DOCTYPE html>
<html lang="">
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Title Page</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
			<![endif]-->

			<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
			<script src="jquery/left_nav.js"></script>
			<link rel="stylesheet" href="Course_Comment.main.css">
			<link href="css/bootstrap.css" rel="stylesheet" media="screen">
			<link href="style/dashboard.css" rel="stylesheet">
			<link href="css/thin-admin.css" rel="stylesheet" media="screen">
			
			


<style type="text/css">


#ccmtMemImg  {
    border-radius: 50%;  width:150px;  height:150px;										
}
		
#titleName{
	background-color:#8CD7D2;
	/*border:10px solid red;*/		
}

#titleCont{
	
	line-height:200px;
}

#titlebtn{
    background-color: #8CD7D2;
    border-width: 4;
    border-color: #E9E9E9;
    margin-left:3%;
    margin-top:88px;
	position: relative;
	z-index: 1;
    cursor: hand;
    font-family: Tahoma,Georgia;
    font-size: 14px; font-weight: bold;

}

p[memName]{
	color:#979797; font-size:25px; 	
}

#modal-id{
			 display: block;
		}


</style>




	
<body bgcolor='white'>
			
		
		<%@ include file="pages/page1_ByCompositeQuery.file" %>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					
					<div class="page-header">
					  <h1>課程討論區<small>/ 最新文章</small></h1>
					</div>
					<div class="panel panel-default">				
					
		<nav class="navbar navbar-default" role="navigation">
			<div class="container">
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<!-- 右選單 -->
					<ul class="nav navbar-nav navbar-right">
					<c:forEach var="ccmtVO" items="${listCourse_Comment_ByCompositeQuery}" begin="0" end="0" step="1">
						<li><a href="<%=request.getContextPath()%>/course_comment/addCourse_Comment_Queryjsp.jsp?crs_no=${ccmtVO.crs_no}&whichPage=<%=whichPage%>&requestURL=<%=request.getServletPath()%>" data-toggle="modal"><i class="glyphicon glyphicon-pencil"></i>發文</a></li>
						<li><a href=></a></li>
							</c:forEach>
												
					</ul>
				</div>
			</div>
		</nav>
		
						  				  
					  <div class="panel-body">
					    
					    <table class="table table-hover">
					    	
					    	<thead>
					    		<tr id="titleName">
					    			<th>作者</th>
					    			<th>主題</th>
					    			<th>日期</th>
					    			<th>檢舉</th>
					    			
					    		</tr>
					    	</thead>
					    	<tbody>
					    		
								
								<c:forEach var="ccmtVO" items="${listCourse_Comment_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					    		
					    		<tr align='center' valign='middle' ><!--將修改的那一筆加入對比色而已-->
					    			
					    			<td>
					    				<c:forEach var="memberVO" items="${memberSvc.all}">
					    					<c:if test="${memberVO.mem_no == ccmtVO.mem_no}">
					    						<img src="<%= request.getContextPath()%>/MemberDBReader?mem_no=${memberVO.mem_no}"  id="ccmtMemImg"><br><br>
					    						<p memName> 【 ${memberVO.mem_fname} - ${memberVO.mem_lname}】 </p>
					    					</c:if>
					    				</c:forEach>
					    			</td>
					    			
					    			
					    			<td id="titleCont">
					    				${ccmtVO.ccmt_cont }				 	
					    			</td>
					    			
					    			<td id="titleCont">${ccmtVO.ccmt_posttime}</td>
					    			
					    	<td id="titleCont">
								<a href="<%=request.getContextPath()%>/ccmt_report/addCcmt_reportQuery.jsp?ccmt_no=${ccmtVO.ccmt_no}&ccmt_cont=${ccmtVO.ccmt_cont}&whichPage=<%=whichPage%>&requestURL=<%=request.getServletPath()%>" data-toggle="modal">檢舉</a>
																
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
		
		
		
		
		
		<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
    <font color=blue>request.getRequestURI():</font> <%= request.getRequestURI()%><br>
   
		
		
		
		

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>