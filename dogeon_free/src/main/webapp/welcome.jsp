<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Service Programming Homework</title>
<link rel="stylesheet" href="resources/cinema.css" type="text/css"></link>
</head>
<body>
	<header> TUK CINEMA </header>
	<br>
	<br>
	<hr>
	<%
	String loginId = null;
	if (session.getAttribute("loginId") != null) {
		loginId = (String) session.getAttribute("loginId");
	}
	%>
	<div class=menu>
		<nav class="clearfix">
			<ul class="clearfix">
				<li><a href="http://localhost:8080/dogeon_free/welcome.jsp"
					target="_self">메인 페이지</a></li>
				<li><a
					href="http://localhost:8080/dogeon_free/UserServlet?cmd=list"
					target="_self">전체 회원 목록 보기 </a></li>

				<li><a
					href="http://localhost:8080/dogeon_free/MovieServlet?cmd=list"
					target="_self">상영 영화 목록 보기 </a></li>
				<%
				if (loginId == null) {
				%>
				<li><a
					href="http://localhost:8080/dogeon_free/UserServlet?cmd=login"
					target="_self"> 로그인</a></li>
				<%
				} else {
				%>
				<li><strong>${loginId} 님 반갑습니다.</strong></li>
				<li><a
					href="http://localhost:8080/dogeon_free/ReservationServlet?cmd=list"
					target="_self"> 예매 내역 확인</a></li>
				<li><a
					href="http://localhost:8080/dogeon_free/UserServlet?cmd=logout"
					target="_self"> 로그아웃</a></li>
				<%
				}
				%>
				<li><a
					href="http://localhost:8080/dogeon_free/UserServlet?cmd=join"
					target="_self"> 회원가입</a></li>
			</ul>
		</nav>
	</div>
	<br>
	<br>
	<br>
	<div>
		<iframe width="1120" height="630"
			src="https://www.youtube.com/embed/kihrFxwdMb4?autoplay=1"
			title="YouTube video player" frameborder="0"
			allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
			allowfullscreen></iframe>
	</div>
</body>
</html>
