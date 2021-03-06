<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--security 호출 태그 (buile.gradle에 의존성 주입해줘야함)--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	String path = request.getContextPath();
%>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/header.css" />
</head>
<body>

	<%--세션 설정 ( 전역에서 사용 가능 ) 호출 태그를 위해 taglib link 필요--%>
	<sec:authentication property="principal" var="session" />
	<c:if test="${session ne 'anonymousUser'}">
		<sec:authentication property="principal.authInfo" var="user"/>
		<sec:authentication property="principal.authInfo.authValue.role" var="userType"/>
	</c:if>

	<header>
		<h2>
			<a href="/main">Zagoga</a>
		</h2>
		<div class="imgbox"></div>
		<div class="navbox">
			<div class="dropdown">
				<button class="dropbtn" onclick="location.href='/main'">HOME</button>
			</div>
			<div class="dropdown">
				<button class="dropbtn">예약 하기</button>
				<div class="dropdown-content">
					<a href="/ghouse/getGhouseList">전체 보기</a> <a href="/ghouse/getGhouseList?local=서울">서울 특별시</a> <a href="/ghouse/getGhouseList?local=부산">부산 광역시</a>
					<a href="/ghouse/getGhouseList?local=인천">인천 광역시</a> <a href="/ghouse/getGhouseList?local=대전">대전 광역시</a> <a href="/ghouse/getGhouseList?local=대구">대구광역시</a>
					<a href="/ghouse/getGhouseList?local=광주">광주 광역시</a> <a href="/ghouse/getGhouseList?local=경기">경기도</a> <a href="/ghouse/getGhouseList?local=경남">경상도</a>
					<a href="/ghouse/getGhouseList?local=강원">강원도</a> <a href="/ghouse/getGhouseList?local=충남">충청도</a> <a href="/ghouse/getGhouseList?local=전남">전라도</a>
					<a href="/ghouse/getGhouseList?local=제주">제주도</a>
				</div>
			</div>
			<c:choose>
				<c:when test = "${userType eq 'HOST'}">
					<c:if test="${user.h_active ne 1}">
						<script>
							alert("승인대기중입니다.");
							location.href="/logout";
						</script>
					</c:if>
					<div class="dropdown">
						<button class="dropbtn" onclick="location.href='/host/mypage_host/${user.h_no}'">마이 페이지</button>
						<div class="dropdown-content">
							<a href="/host/mypage_host_customerList">예약자 조회</a>
							<a href="/host/host_myGhouse/${user.h_no}">회원 정보 변경</a>
							<a href="/host/mypage_house_info/${user.h_no}">게스트하우스 정보 변경</a>
							<a href="/ghouse/write">게스트하우스 등록</a>
						</div>
					</div>
				</c:when>
				<c:when test="${userType eq 'USER' }">
					<div class="dropdown">
						<button class="dropbtn" onclick="location.href='/user/mypage_user/${user.u_no}'">마이 페이지</button>
						<div class="dropdown-content">
							<a href="/user/mypage_user_info">회원정보 변경</a>
							<a href="/user/mypage_user_booking_list/${user.u_no}">예약 조회</a>
						</div>
					</div>
				</c:when>
				<c:when test="${userType eq 'ADMIN' }">
					<div class="dropdown">
						<button class="dropbtn" onclick="location.href='/admin/user_list'">USERLIST</button>
						<div class="dropdown-content">
							<a href="/admin/user_booking_list">예약 정보 조회</a>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn" onclick="location.href='/admin/host_list'">HOSTLIST</button>
					</div>
				</c:when>
			</c:choose>

			<c:choose>
				<c:when test="${empty user}">
					<div class="dropdown">
						<button class="dropbtn" onclick="location.href='/login'">login</button>
					</div>
				</c:when>
				<c:otherwise>
					<div class="dropdown">
						<button class="dropbtn" onclick="location.href='/logout'">logout</button>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<script>
			function check(id) {
				var win = window.open('mypage_check?id=' + id, '','width=400px,height=200px')
			}
		</script>
	</header>
	<!-- 공간 비우기용-->
	<div class="space"></div>
</body>
</html>