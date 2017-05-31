<%@page import="com.wish_comment.model.Wish_CommentDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wis.model.*"%>
<%@ page import="com.wish_comment.model.*"%>

<%
    WisDAO_interface dao = new WisDAO_JNDI();
    List<WisVO> list = dao.getAll();
    pageContext.setAttribute("list", list);    
%>

<html>
<head>
	<title>藝起學 - 課程許願池</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="css/main.css" />
	<link rel="stylesheet" href="css/materialize.css" />
  	<link rel="stylesheet" href="css/wish_pool.css" />
  	<link href="css/header+footer.css" type="text/css" rel="stylesheet" media="screen,projection"/>
 	<script type="text/JavaScript" src="ckeditor/ckeditor.js"></script>

</head>
<body>
<!-- header -->
  <header class="hide-on-small-only">
    <div class="container_rh clearfix ">
      <a href="#"><img id="logo" src="images/logo_aa.jpg" alt="logo" class="img-responsive"></a>
      <a href="#">募資專區</a>
      <a href="#">課程專區</a>
      <a href="#" class="active">課程許願池</a>
      <a href="#">教材專區</a>
      <a href="#">TA專區</a>
      <a href="#" id="login_panel" class="shrink">登入</a>
      <a href="#">會員專區</a>
      <a href="#">我要開課!!</a>
      <a href="#">
        <div id="cart">
          <img src="images/shopping_004.png" alt="Shopping Cart">
          <p class="img_description">我的購物車</p>
        </div>
      </a>
    </div>
  </header>

  <!-- Wrapper -->
  <div id="wrapper">
    <div class="container" id="btnContainer" >
      <div class="row" >
        <!-- 我想教 -->
        <div id="teachBtn">
          <a class="modal-trigger waves-effect waves-light btn-large" href="#modal_teach">我想教...</a>
        </div> 
        <div id="modal_teach" class="modal modal-fixed-footer">
        <form method="post" action="wis.do" name="form0" enctype="multipart/form-data">
          <div class="modal-content">
            <h4>我想教...</h4><br>
              <div class="input-field col s12">
                <input name="wis_title" type="text" class="validate" value=${(empty errorMsgs)? "" : param.wis_title}>
                <label for="wis_title">我想教...</label>
              </div>
              <div class="input-field col s12">
              ${errorMsgs.wis_cnt_teach}
                <textarea id="wis_cnt_teach" rows="5" cols="50" name="wis_cnt_teach">
                ${(errorMsgs.wis_cnt_teach == null)? '可以讓學生...' : errorMsgs.wis_cnt_teach}</textarea>
              </div>
          </div>
          <div class="modal-footer">
            	<input type="hidden" name="mem_no" value="11000002"> 
            	<input type="hidden" name="wis_to" value="1"> 
            	<input type="hidden" name="action" value="insert">
              	<input type="submit" id="teachBtn_submit" class="modal-action modal-close waves-effect waves-orange btn-flat" value="送出">
              <a href="#!" class="modal-action modal-close waves-effect waves-light btn-flat ">取消</a>       
          </div>
          </form> 
        </div>
        <!-- 我想學 -->
        <div id="learnBtn">
          <a class="modal-trigger waves-effect waves-light btn-large orange white-text" href="#modal_learn">我想學...</a>
        </div>
        <div id="modal_learn" class="modal modal-fixed-footer">
		<form method="post" action="wis.do" name="form1" enctype="multipart/form-data">
          <div class="modal-content">
            <h4>我想學...</h4><br>            
            <div class="input-field col s12">
                <input name="wis_title" type="text" class="validate" value=${(empty errorMsgs)? "" : param.wis_title}>
                <label for="wis_title">我想學...</label>
              </div>
              <div class="input-field col s12">
				<textarea id="wis_cnt_learn" rows="5" cols="50" name="wis_cnt_learn">
				${(errorMsgs.wis_cnt_learn == null)? '希望可以做到...' : errorMsgs.wis_cnt_learn}</textarea>
              </div>
            </div>
            <div class="modal-footer">
                <input type="hidden" name="mem_no" value="11000002"> 
                <input type="hidden" name="wis_to" value="0"> 
            	<input type="hidden" name="action" value="insert">
              	<input type="submit" id="learnBtn_submit" class="modal-action modal-close waves-effect waves-orange btn-flat" value="送出">
              	<a href="#!" class="modal-action modal-close waves-effect waves-light btn-flat ">取消</a>     
            </div>
			</form>   
        </div>
        
      </div>
    </div>

    <!-- Main -->
    <div id="main" style="border: 1px solid #fff">
     <div class="inner" >
      <section class="tiles">
      
<c:forEach var="wisVO" items="${list}">	
       <!-- 以下是一塊 -->
       <article class="${(wisVO.wis_to == '0')? 'learn' : 'teach' }">
        <span id="sfire1" class="image"><img src="images/pic10.jpg" alt="" /></span>
        <a href="#modal${wisVO.wis_no}"><h4>${wisVO.wis_title}</h4>
          <div class="content">
            <p>${wisVO.wis_cnt}</p>
            <p>有${(wisVO.wis_like == 0)? 0 : wisVO.wis_like}人收藏此許願</p>
          </div>
        </a>
      </article>
<!-- 以下許願池留言 -->
      <div id="modal${wisVO.wis_no}" class="modal modal-fixed-footer">
        <div class="modal-content">
          <h4>${wisVO.wis_title}</h4>
          <p>${wisVO.wis_cnt}</p>
          <a class="waves-effect waves-light btn teal" href="#modal_wrep${wisVO.wis_no}">檢舉許願池</a>          
          <input type="hidden" id="wis_no_forLike${wisVO.wis_no}" value="${wisVO.wis_no}">
          <input type="hidden" id="mem_no_forLike${wisVO.wis_no}" value="11000002">
          <input type="button" class="waves-effect waves-light btn orange" id="${wisVO.wis_no}" name="btnLike" value="收藏這個許願">
          <div class="col s12 m6">
<jsp:useBean id="wcmtSvc" scope="page" class="com.wish_comment.model.Wish_CommentService"/>
<c:forEach var="wcmtVO" items="${wcmtSvc.all}">
<c:if test="${wisVO.wis_no == wcmtVO.wis_no }">
            <div class="card teal darken-1">
              <div class="card-content white-text">
                <span class="card-title">"${wcmtVO.mem_no}"</span>
                <p>${wcmtVO.wcmt_cont}</p>
              </div>
              <div class="card-action">
                  <a class="waves-effect waves-light btn teal" href="#modal_wcrep${wcmtVO.wcmt_no}">檢舉留言</a>
              </div>
            </div>      
</c:if>
</c:forEach>
           </div>
          </div>
         <div class="modal-footer">
<form method="post" action="wish_comment.do" name="formWcmt">
           <textarea rows="5" cols="50" name="wcmt_cont">請輸入</textarea>
           <input type="hidden" name="wis_no" value="${wisVO.wis_no}">
           <input type="hidden" name="mem_no" value="11000002">
           <input type="hidden" name="action" value="insert">
           <a href="#!" class="modal-action modal-close waves-effect waves-light btn-flat">看完了</a>
           <input type="submit" class="modal-action modal-close waves-effect waves-teal btn-flat" value="在這裡留言">
</form>
         </div>
        </div>
<!-- 以上許願池留言 -->           
<!-- 以下許願池留言檢舉 -->           
<jsp:useBean id="wcmtSvc_2" scope="page" class="com.wish_comment.model.Wish_CommentService"/>
<c:forEach var="wcmtVO_2" items="${wcmtSvc_2.all}">
<!-- <form method="post" action="wcrep.do" name="formWcrep"> -->
         <div id="modal_wcrep${wcmtVO_2.wcmt_no}" class="modal bottom-sheet">
           <div class="modal-content">
            <h4>檢舉許願池留言</h4>
            <div class="input-field col s6">
              <input name="wcrep_title" type="text" class="validate">
              <label for="wcrep_title">檢舉標題</label>
            </div>
            <div class="input-field col s6">
              <input name="wcrep_cont" type="text" class="validate">
              <label for="wcrep_cont">檢舉內容</label>
            </div>
           </div>
          <div class="modal-footer">
         	<input type="hidden" name="wcrep_no" value="${wcmtVO_2.wcmt_no}">
          	<input type="hidden" name="mem_no" value="11000002">
          	<input type="hidden" name="action" value="insert">
            <a href="#!" class="modal-action modal-close waves-effect waves-light btn-flat">取消</a>
            <input type="submit" class="modal-action modal-close waves-effect waves-teal btn-flat" value="送出">
          </div>
         </div>
<!-- </form>     -->
</c:forEach>
<!-- 以上許願池留言檢舉 -->                   
<!-- 以下許願池檢舉 -->
<form method="post" action="wrep.do" name="formWrep">
        <div id="modal_wrep${wisVO.wis_no}" class="modal bottom-sheet">
          <div class="modal-content">
            <h4>檢舉許願池</h4>
            <div class="input-field col s6">
              <input name="wrep_title" id="wrep_title${wisVO.wis_no}" type="text" class="validate">
              <label for="wrep_title">檢舉標題</label>
            </div>       
            <div class="input-field col s6">
              <input name="wrep_cont" type="text" class="validate">
              <label for="wrep_cont">檢舉內容</label>
            </div>
          </div>
          <div class="modal-footer">
          	<input type="hidden" name="wis_no" value="${wisVO.wis_no}">
          	<input type="hidden" name="mem_no" value="11000002">
          	<input type="hidden" name="action" value="insert">
            <a href="#!" class="modal-action modal-close waves-effect waves-light btn-flat">取消</a>
            <input type="submit" class="modal-action modal-close waves-effect waves-teal btn-flat" value="送出檢舉">
          </div>
        </div>
</form>
<!-- 以上許願池檢舉 -->
<!-- 以上是一塊 -->
</c:forEach>

      </section>
    </div>
  </div>
</div>

<!-- footer -->
<footer class="page-footer teal">
  <div class="no-padding">
    <div class="row" style="margin-bottom:0;">
      <div class="col s12 m3 l2 ft-item">
        <a href="#"><h5>關於我們</h5></a>
      </div>
      <div class="col s12 m3 l2 ft-item">
        <a href="#"><h5>聯絡客服</h5></a>
      </div>
    </div>
  </div>
  <div class="footer-copyright">
  </div>
</footer> 

<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript" src="js/wish_pool.js"></script>

</body>
</html>

<c:if test="${errorMsgs.wis_cnt_learn != null}">
<script>
$(document).ready(function(){
    $('#modal_learn').modal('open');
  });
</script>
</c:if>

<c:if test="${errorMsgs.wis_cnt_teach != null}">
<script>
$(document).ready(function(){
    $('#modal_teach').modal('open');
  });
</script>
</c:if>

<c:if test="${errorMsgs.wcmt_cont != null}">
<script>
$(document).ready(function(){
	Materialize.toast('留言未送出!!!\n請注意內容請勿空白', 4000);
  });
</script>
</c:if>

<c:if test="${errorMsgs.wrep_title != null || errorMsgs.wrep_cont != null}">
<script>
$(document).ready(function(){
	Materialize.toast('許願池檢舉未送出!!!\n請注意標題與內容皆須填寫', 4000);
  });
</script>
</c:if>

<c:if test="${errorMsgs.wcrep_title != null || errorMsgs.wcrep_cont != null}">
<script>
$(document).ready(function(){
	Materialize.toast('留言檢舉未送出!!!\n請注意標題與內容皆須填寫', 4000);
  });
</script>
</c:if>

