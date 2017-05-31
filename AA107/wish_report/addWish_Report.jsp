<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%-- prefix�O����Үw,c�O�֤ߥ\�����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.wis.model.*"%>
<%@ page import="com.wish_report.model.*"%>

<%
    WisDAO_interface dao = new WisDAO_JNDI();
    List<WisVO> list = dao.getAll();
    pageContext.setAttribute("list",list);
%>


<% 
Wish_ReportVO wishptVO = (Wish_ReportVO) request.getAttribute("wishptVO");
%>



<html>
<head>


							<title>�s�W�\�@�����| - addWish_Report.jsp</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">





<style type="text/css">


#head{
	background-color:#8CD7D2;	/*border:10px solid red;*/		
}
#title{
 	font-weight: bold;	font-size: 25px;
}

#TableLeft{
	width:20%;
	
}
.btnStyle {
    background-color: #8CD7D2;
    border-width: 6;
    border-color: #E9E9E9;
    margin-left:30%;
    margin-top:10px;
	position: relative;
	z-index: 1;
    cursor: hand;
    font-family: Tahoma,Georgia;
    font-size: 18px; font-weight: bold; 
    }

</style>

</head>

<body>

<%-- test= �Oboolean���󦡬�'�u'�~�|����--%>

<c:if test="${not empty errorMsgs}">
	<font color='red'> �Эץ��H�U���~
		<ul>			
			<%-- var�O�񶰦Xcollection���ܼ�--%>
			<%-- items�O�񶰦Xcollection����--%>			
			<c:forEach var="message" items="${errorMsgs}">		
				<li> ${message} </li>		
			</c:forEach>
		</ul>	
	</font>
</c:if>




<div class="container">
			<div class="row">
			
				<div class="col-xs-12 col-sm-12">
					
					<div class="page-header">
					  <h1>��U�ڭ��A�ѵo�ͪ��p<small>   </small></h1>
					</div>


					<div class="panel panel-default">
					  <div class="panel-heading" id="head">
					    <h3 class="panel-title" id="title">�o�ͤF�����?</h3>
					  </div>
					  <div class="panel-body">
					 	<FORM METHOD="post" ACTION="wish_report.do" name="form1">   
					    	<table class="table table-hover">
					    	
					    	<thead>
					    		<jsp:useBean id="wisSvc" scope="page" class="com.wis.model.WisService" />	 
		 						<tr >
					    				<td id="TableLeft" >���|�\�@�����e:<font color=red><b>*</b></font></td>					    			
					    				<td>
												<c:forEach var="wisVO" items="${list}" >
													<c:if test="${wisVO.wis_no == param.wis_no}">
					    								<select size="1" name="wis_no">	
					    									<option value="${wisVO.wis_no}" ${(wishptVO.wis_no==wisVO.wis_no)? 'selected':'' } >${wisVO.wis_cnt}				    						
					    								</select>				    						
					    							</c:if>																						
												</c:forEach>
										</td>					    							    							    		
					    		</tr>
					    							    							    							  										 									 					  	
					    	</thead>
					    	<tbody>
					    		<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
					    		<tr>
					    			<td id="TableLeft" >���|�|��:<font color=red><b>*</b></font></td>
					    				<td><select size="1" name="mem_no">
					    						<option value="">
												<c:forEach var="memberVO" items="${memberSvc.all}">
													<option value="${memberVO.mem_no}" ${(ccmtVO.mem_no==memberVO.mem_no)? 'selected':'' } >${memberVO.mem_fname}
												</c:forEach>
											</select></td>
					    			
					    		</tr>

								<tr>
										<td>���|���D:</td>
										<td><input type="TEXT" name="wrep_title" size="45"
										value="<%= (wishptVO==null)? "" : wishptVO.getWrep_title()%>" /></td>
								</tr>
										
							
																													    						    														
								
								
								<tr>
					    			<td id="TableLeft">���|��]</td>
					    			
					    			<td>
					    				<textarea name="wrep_cont" rows="20" cols="105" wrap="VIRTUAL" 
					    					value="<%= (wishptVO==null)? " " :wishptVO.getWrep_cont() %>"></textarea>
					    			</td>
					    
					    		</tr>
					    		
					    		
					    	    <tr>
					    			<td id="TableLeft">���|���</td>					    			
					    			<td>		
										<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>										
										 <input class="cal-TextBox"
										onFocus="this.blur()" size="9" readonly type="text" name="wrep_time" 
											value="<%= (wishptVO==null)? date_SQL : wishptVO.getWrep_time() %>">
										
									</td>				    							    						    							    					    		
					    		</tr>
					    						    		
					    		
					    		<tr>
					    			<td id="TableLeft"></td>
					    			<td>			
										<input type="submit" class="btnStyle" value="�e�X���|" id="submitCcmtRpt">
										<input type="hidden" name="action" value="submitWishRpt_insert">
										<input type="hidden" name="wrep_no" value="${wishptVO.wrep_no}">
										<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--������e�X�ק諸�ӷ��������|��A�A�e��controller�ǳ����� -->
										         
									</td>
					    			
					    		</tr>
					    	</tbody>
					    </table>
					  </FORM>

					  </div>
					</div>
				</div>
			</div>
		</div>



<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>