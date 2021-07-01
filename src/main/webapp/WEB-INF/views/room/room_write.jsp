<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="resources/css/write.css" type="text/css">
	<title>Insert title here</title>
	<script type="text/javascript" src="resources/js/guesthouse.js" charset="utf-8"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  </head>
  <body>
  <%@ include file="../header.jsp" %>
	<div class="total">
	  <form name="form" action="#" method="post">
	    <h1>방 소개</h1>
	    <div class="section">
		    <div class="title">
		     <h3>제목&nbsp;&nbsp;&nbsp;<input type="text" name="gh_name" id="gh_name" width="100"></h3>
		    </div>
	        <div class="title2">
			    <h4>이미지 업로드&nbsp;&nbsp;&nbsp;</h4>
			    <input type="file" name="gh_image" id="gh_image" size="10">
	        </div>
		<div class="detail">
						<h3>상세 내용</h3>
						<h4>최대 인원수&nbsp;&nbsp;&nbsp; 
							<select name="category" id="category">
								<option value='1'>1인실</option>
								<option value='2'>2인실</option>
								<option value='3'>4인실</option>
								<option value='4'>8인실</option>
								<option value='4'>16인실</option>
							</select>
						</h4>
				</div>
	    <div class="content">
	        <h4>소개</h4>
	        <textarea 
	        	style="overflow: auto;" 
	        	rows="20" cols="70" name="gh_detail" id="gh_detail">방소개 글</textarea>
	      </div>
	      <div class="button">
	        <input type="button" onclick="write_check_ok()" class="but" value="글 올리기">
	        <button type="reset" class="but">다시쓰기</button>
	        <span><button type="button" value="뒤로가기" onclick="history.back()" class="but">뒤로가기</button></span>
	      </div>
	    </div>
	  </form>
	  <div class="clear"></div>
	</div>
	 <%@ include file="../footer.jsp" %>
  </body>
</html>