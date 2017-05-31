<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ccmt_report.model.*"%>



<%@ page import="com.course_comment.model.*"%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="courseSvc" scope="page" class="com.course.model.CourseService" />


<%--�����m�߱ĥ� EL ���g�k���� --%>

<% 
	Ccmt_ReportService ccmtRptSvc= new Ccmt_ReportService();
	List <Ccmt_ReportVO> list = ccmtRptSvc.getAll();
	pageContext.setAttribute("list",list);

%>

${ccmtRptVO.crep_no}

<jsp:useBean id="ccmtSvc" scope="page" class="com.course_comment.model.Course_CommentService"/>


<html>
<head>
							<title>��x�d�����| - listAllCcmt_report.jsp</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

<style type="text/css">

#head{ background-color:#8CD7D2;	/*border:10px solid red;*/}

#title{ font-weight: bold;	font-size: 25px;}

#TableLeft{	width:20%;}

#titleCont{	line-height:200px; }
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

#ccmtMemImg{ border-radius: 50%;  width:70px;  height:70px;}

</style>

</head>


<body bgcolor='white'>





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














<nav class="navbar navbar-default" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
						<span class="sr-only">������</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">CSS�i��</a>
				</div>
				
				<!-- ������ÿ��� -->
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<!-- ����� -->
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">����ڭ�</a></li>
						<li><a href="#">�ҵ{�M��</a></li>
						<li><a href="#">�Q�װ�</a></li>
						<li><a href="#">����ڭ�</a></li>
					</ul>
				
					<!-- �j�M��� -->
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="�п�J����r">
						</div>
						<button type="submit" class="btn btn-default">�j�M</button>
					</form>
				
					<!-- �k��� -->
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">Amos �z�n</a></li>
						<li><a href="#">�n�X</a></li>
						<li><a href="#">�ӤH�]�w</a></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">�c�餤�� <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">�c�餤��</a></li>
								<li><a href="#">English</a></li>
								<li><a href="#">�饻�y</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- ������ÿ��ϵ��� -->
			</div>
		</nav>


			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-4">
						
							<div class="list-group">
								<a href="#" class="list-group-item active">�M�涵�� 1</a>
								<a href="#" class="list-group-item">�M�涵�� 2</a>
								<a href="#" class="list-group-item">�M�涵�� 3</a>
							</div>


							<div class="list-group">
								<a href="#" class="list-group-item active">
									<h4 class="list-group-item-heading">���D</h4>
									<p class="list-group-item-text">���e��r</p>
								</a>
								<a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">���D</h4>
									<p class="list-group-item-text">���e��r</p>
								</a>
								<a href="#" class="list-group-item">
									<h4 class="list-group-item-heading">���D</h4>
									<p class="list-group-item-text">���e��r</p>
								</a>
							</div>

							<div class="list-group">
								<a href="#" class="list-group-item active"><span class="badge">10</span>�M�涵�� 1</a>
								<a href="#" class="list-group-item"><span class="badge">10</span>�M�涵�� 2</a>
								<a href="#" class="list-group-item"><span class="badge">10</span>�M�涵�� 3</a>
							</div>
					</div>
					<div class="col-xs-12 col-sm-8">
						
							<ol class="breadcrumb">
								<li>
									<a href="#">����</a>
								</li>
								<li>
									<a href="#">�̷s����</a>
								</li>
								<li class="active">���ڤW�q���F</li>
							</ol>			
							
							<%@ include file="pages/page1.file" %> 	
					
							
							
							<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">�Q�װ����|</h3>
					  </div>
					  <div class="panel-body">
					    
					    <table class="table table-hover">
					    	
					    	<thead>
					    		<tr>
					    			<th>���|��</th>
					    			<th>���|���D</th>
					    			<th>���|��]</th>				    			
					    			<th>�Q�װϯd��</th>
					    			<th>�R���d��</th>
					    						    			
					    		</tr>
					    	</thead>
					    	<tbody>
					 					<c:forEach var="ccmtRptVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">		
					    		<tr >
					    			
					    			<td>
					    			
					    				<c:forEach var="memberVO" items="${memberSvc.all}">
					    					<c:if test="${memberVO.mem_no == ccmtRptVO.mem_no}">
					    						<img src="<%= request.getContextPath()%>/MemberDBReader?mem_no=${memberVO.mem_no}"  id="ccmtMemImg"><br><br>
					    						<p memName>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${memberVO.mem_fname} </p>
					    					</c:if>
					    				</c:forEach>
					    			
					    			</td>
					    			<td >${ccmtRptVO.crep_title}</td>
					    			<td >${ccmtRptVO.crep_cnt}</td>
					    		
					    			<td >
					    				<c:forEach var="ccmtVO" items="${ccmtSvc.all}">
											<c:if test="${ccmtRptVO.ccmt_no == ccmtVO.ccmt_no}">			
														&nbsp;&nbsp;${ccmtVO.ccmt_cont}		
											</c:if>			
										</c:forEach>
									</td>																							    			
					    			<td >					    			
					    				<c:forEach var="ccmtVO" items="${ccmtSvc.all}">
											<c:if test="${ccmtRptVO.ccmt_no == ccmtVO.ccmt_no}">			
			
			  									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course_comment/course_comment.do">
			    									<input type="submit" onclick="ccmtRptDelete()" value="�R��" class="btnStyle" >
			    									<input type="hidden" name="ccmt_no" value="${ccmtVO.ccmt_no}">
			    									<input type="hidden" name="action" value="delete_ccmt">
												</FORM>						
										</c:if>
													<%-- <a href="<%=request.getContextPath()%>/course_comment/course_comment.do?ccmt_no=${ccmtVO.ccmt_no}&action=delete_ccmt">${ccmtVO.ccmt_no}</a>	--%>																					
										</c:forEach>							    			
					    			</td>
					    			
					    		</tr>
					    		
					    		</c:forEach>
					    		
					    		
					    	</tbody>
					    </table>
					    
					    <%@ include file="pages/page2.file" %>

					  </div>
					</div>
									
					</div>
				</div>
			</div>

	<script type="text/javascript">
	
	function ccmtRptDelete(){
		return window.confirm("�T�w�R��");
	}
	
	</script>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>