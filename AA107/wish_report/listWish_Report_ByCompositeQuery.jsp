<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

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
							<title>�ƦX�d�� - listWish_Report_ByCompositeQuery.jsp</title>
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
							
							<%@ include file="pages/page1_ByCompositeQuery.file" %> 	
					
							
							
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
					    			<th>�\�@�����e</th>
					    			<th>���|�ɶ�</th>
					    			<th>�R�����e</th>				    		
					    						    			
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
			    									<input type="submit" onclick="ccmtRptDelete()" value="�R��" class="btnStyle" >
			    									<input type="hidden" name="wis_no" value="${wisVO.wis_no}">
			    									<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			    									<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
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
		return window.confirm("�T�w�R��");
	}
	
	</script>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>