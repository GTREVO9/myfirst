<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>

<%-- prefix是放標籤庫,c是核心功能標籤--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.course_comment.model.*"%>



<% 
Course_CommentVO ccmtVO = (Course_CommentVO) request.getAttribute("ccmtVO");
%>



<html>

<head>


							<title>文章新增 - addCourse_Comment.jsp</title>
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
					  <h1>頁面標題<small>真是好標題啊</small></h1>
					</div>


					<div class="panel panel-default">
					  <div class="panel-heading" id="head">
					    <h3 class="panel-title" id="title">留言發問</h3>
					  </div>
					  <div class="panel-body">
					 	<FORM METHOD="post" ACTION="course_comment.do" name="form1">   
					    	<table class="table table-hover">
					    	
					    	<thead>
					    		<jsp:useBean id="courseSvc" scope="page" class="com.course.model.CourseService" />
					    		
					   	<%--
					    		<tr >
					    			<td id="TableLeft" >課程名稱:<font color=red><b>*</b></font></td>
					    				<td><select size="1" name="crs_no">
												 <option value="">
												<c:forEach var="courseVO" items="${courseSvc.all}">
													<option value="${courseVO.crs_no}" ${(ccmtVO.crs_no==courseVO.crs_no)? 'selected':'' } >${courseVO.crs_name}
												</c:forEach>
											</select></td>
					    		</tr>
					    	
					    					抓值測試用
					    												
										 <input class="cal-TextBox"
										onFocus="this.blur()" size="9" readonly type="text" name="crs_no" value="<%=request.getParameter("crs_no")%>">				    	
					     
					     
					     
					      --%>		
					    		<tr >
					    			<td id="TableLeft" >課程名稱:<font color=red><b>*</b></font></td>
					    				<td>
												 
											<c:forEach var="courseVO" items="${courseSvc.all}">
					    						<c:if test="${courseVO.crs_no == param.crs_no}">
					    							<select size="1" name="crs_no">
					    								<option value="${courseVO.crs_no}" ${(ccmtVO.crs_no==courseVO.crs_no)? 'selected':'' } >${courseVO.crs_name}				    						
					    							</select>				    						
					    						</c:if>
					    					</c:forEach>
										</td>
					    		</tr>
					    		
					    	
					    	
					    		
					    	
					    	</thead>
					    	<tbody>
					    		<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
					    		<tr>
					    			<td id="TableLeft" >會員姓名:<font color=red><b>*</b></font></td>
					    					 
					    				<td><select size="1" name="mem_no">
												<option value="">
												<c:forEach var="memberVO" items="${memberSvc.all}">
													<option value="${memberVO.mem_no}" ${(ccmtVO.mem_no==memberVO.mem_no)? 'selected':'' } >${memberVO.mem_fname}
												</c:forEach>
											</select></td>
					    			
					    		</tr>
					    		<tr>
					    			<td id="TableLeft">文章內容</td>
					    			<td>
					    				<textarea name="ccmt_cont" rows="20" cols="105" wrap="VIRTUAL" value="<%= (ccmtVO==null)? " " : ccmtVO.getCcmt_cont() %>"></textarea>
					    			</td>
					    			
					    		</tr>
					    		
					    		<tr>
					    			<td id="TableLeft">文章發表時間</td>
					    			
					    			<td>
			
										<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>										
										 <input class="cal-TextBox"
										onFocus="this.blur()" size="9" readonly type="text" name="ccmt_posttime" value="<%= (ccmtVO==null)? date_SQL : ccmtVO.getCcmt_posttime() %>">
										
										</td>				    			
					    		
					    		
					    		
					    					    		
					    		</tr>
					    		
				
					    		
					    		
					    		
					    		
					    		
					    		<tr>
					    			<td id="TableLeft"></td>
					    			<td>			
										<input type="submit" class="btnStyle" value="送出新增" id="submitBtn">
										<input type="hidden" name="action" value="submitQuery_insert">
										<input type="hidden" name="ccmt_no" value="${ccmtVO.ccmt_no}">
										<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後，再送給controller準備轉交用 -->
										<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>"><!--用於:istAllEmp.jsp 與 複合查詢 listEmps_ByCompositeQuery.jsp-->
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


<br>送出修改的來源網頁路徑:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br>
   <font color=blue>request.getParameter("whichPage"):</font> <%= request.getParameter("whichPage")%>
</body>
</html>