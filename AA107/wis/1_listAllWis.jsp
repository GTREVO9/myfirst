<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wis.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    WisDAO_interface dao = new WisDAO_JNDI();
    List<WisVO> list = dao.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE HTML>
<html>
<head>
	<title>藝起學 - 課程許願池</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="css/main.css" />
	<link rel="stylesheet" href="css/materialize.css" />
	<script type="text/JavaScript" src="ckeditor/ckeditor.js"></script>
</head>
<body>
	<!-- Wrapper -->
	<div id="wrapper">
	<!-- Main -->
		<div id="main">
			<div class="inner">
				<section class="tiles">
<c:forEach var="wisVO" items="${list}">	
					<!-- 以下是一塊 -->
					<article class="style1">
						<span id="sfire1" class="image"><img src="images/pic01.jpg" alt="" /></span>
						<a href="#modal${wisVO.wis_no}"><h5>${wisVO.wis_title}</h5>
							<div class="content">
								<p>${wisVO.wis_cnt}</p>
							</div>
						</a>
					</article>
					<div id="modal${wisVO.wis_no}" class="modal modal-fixed-footer">
						<div class="modal-content">
							<h4>${wisVO.wis_title}</h4>
							<p>${wisVO.wis_cnt}</p>
							<div class="col s12 m6">
								<div class="card blue-grey darken-1">
									<div class="card-content white-text">
										<span class="card-title">Card Title</span>
										<p>I am a very simple card. I am good at containing small bits of information.
											I am convenient because I require little markup to use effectively.</p>
										</div>
									</div>
								</div>
							</div>
						<div class="modal-footer">
							<textarea rows="5" cols="50" name="editor01">請輸入</textarea>
							<a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">看完了</a>
							<a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">留言</a>
						</div>
					</div>
					<!-- 以上是一塊 -->				
</c:forEach>
				</section>
			</div>
		</div>
	</div>


	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
  

</body>
</html>

<script type="text/javascript">
	$(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();
  });
// var options = [
		



// 			{selector: '#sfire1', offset: 0, callback: function(el) {
//         		Materialize.fadeInImage($(el));
//         	} },
//         	{selector: '#sfire2', offset: 0, callback: function(el) {
//         		Materialize.fadeInImage($(el));
//         	} },
//         	{selector: '#sfire3', offset: 0, callback: function(el) {
//         		Materialize.fadeInImage($(el));
//         	} },
//         	{selector: '#sfire4', offset: 10, callback: function(el) {
//         		Materialize.fadeInImage($(el));
//         	} },
//         	{selector: '#sfire5', offset: 200, callback: function(el) {
//         		Materialize.fadeInImage($(el));
//         	} }
		
//     ];


// 	Materialize.scrollFire(options);

 window.onload = function()
    {
        CKEDITOR.replace( 'editor01' );
    };


</script>