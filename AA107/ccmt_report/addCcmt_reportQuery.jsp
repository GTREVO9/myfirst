<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>

<%-- prefix是放標籤庫,c是核心功能標籤--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ccmt_report.model.*"%>
<%@ page import="com.course_comment.model.*"%>


<% 
Ccmt_ReportVO ccmtRptVO = (Ccmt_ReportVO) request.getAttribute("ccmtRptvo");
%>

<% 
Course_CommentVO ccmtVO = (Course_CommentVO) request.getAttribute("ccmtVO");
%>





<html>

<head>


							<title>新增檢舉 - addCcmt_report.jsp</title>
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

<body bgcolor='white'>



<%-- 錯誤表列--%>

<%-- test= 是boolean條件式為'真'才會執行--%>

<c:if test="${not empty errorMsgs}">
	<font color='red'> 請修正以下錯誤
		<ul>
			
			<%-- var是放集合collection的變數--%>
			<%-- items是放集合collection物件--%>
			
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
					  <h1>協助我們瞭解發生狀況<small>   </small></h1>
					</div>


					<div class="panel panel-default">
					  <div class="panel-heading" id="head">
					    <h3 class="panel-title" id="title">發生了什麼事?</h3>
					  </div>
					  <div class="panel-body">
					 	<FORM METHOD="post" ACTION="ccmt_report.do" name="form1">   
					    	<table class="table table-hover">
					    	
					    	<thead>
					    		<jsp:useBean id="ccmtSvc" scope="page" class="com.course_comment.model.Course_CommentService" />
						 	  <%--	
					    		<tr >
					    			<td id="TableLeft" >檢舉討論區內容:<font color=red><b>*</b></font></td>
					    				<td><select size="1" name="ccmt_no">
												<c:forEach var="ccmtVO" items="${ccmtSvc.all}" begin="0" end="0" step="1">
													<option value="${ccmtVO.ccmt_no}" ${(ccmtRptvo.ccmt_no==ccmtVO.ccmt_no)? 'selected':'' } ><%= request.getParameter("ccmt_cont")%>
												</c:forEach>
											</select></td>
					    		</tr>
		 --%> 
		 
		 							<tr >
					    			<td id="TableLeft" >檢舉討論區內容:<font color=red><b>*</b></font></td>
					    				<td>
												<c:forEach var="ccmtVO" items="${ccmtSvc.all}" >
													<c:if test="${ccmtVO.ccmt_no == param.ccmt_no}">
					    							<select size="1" name="ccmt_no">
					    								<option value="${ccmtVO.ccmt_no}" ${(ccmtRptvo.ccmt_no==ccmtVO.ccmt_no)? 'selected':'' } >${ccmtVO.ccmt_cont}				    						
					    							</select>				    						
					    						</c:if>
													
													
												</c:forEach>
										</td>
					    		</tr>
					  				
						 	
								 	
					    	
					    	</thead>
					    	<tbody>
					    		<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
					    		<tr>
					    			<td id="TableLeft" >檢舉會員:<font color=red><b>*</b></font></td>
					    				<td><select size="1" name="mem_no">
					    						<option value="">
												<c:forEach var="memberVO" items="${memberSvc.all}">
													<option value="${memberVO.mem_no}" ${(ccmtVO.mem_no==memberVO.mem_no)? 'selected':'' } >${memberVO.mem_fname}
												</c:forEach>
											</select></td>
					    			
					    		</tr>

								<tr>
										<td>檢舉標題:</td>
										<td><input type="TEXT" name="crep_title" size="45"
										value="<%= (ccmtRptVO==null)? "" : ccmtRptVO.getCrep_title()%>" /></td>
								</tr>
																													    						    														
								<tr>
					    			<td id="TableLeft">檢舉原因</td>
					    			<td>
					    				<textarea name="crep_cnt" rows="20" cols="105" wrap="VIRTUAL" value="<%= (ccmtRptVO==null)? " " :ccmtRptVO.getCrep_cnt() %>"></textarea>
					    			</td>
					    			
					    		</tr>
					    		
					    		
					    		
	
	
		    		
		    			
					    		
					    		
					    		<tr>
					    			<td id="TableLeft"></td>
					    			<td>			
										<input type="submit" class="btnStyle" value="送出檢舉" id="submitCcmtRpt">
										<input type="hidden" name="action" value="submitCcmtRpt_insert">
										<input type="hidden" name="crep_no" value="${ccmtRptVO.crep_no}">
										<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後，再送給controller準備轉交用 -->
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