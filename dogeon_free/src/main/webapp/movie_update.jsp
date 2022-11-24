<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Service Programming Homework</title>
<link rel="stylesheet" href="resources/cinema.css" type="text/css"></link>
</head>
<body>
	<header>Movie Update</header>
	<HR>
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
	<div align="center">

		<form
			action="http://localhost:8080/dogeon_free/MovieServlet?cmd=update"
			method="post">
			<%
			MovieVO movie = (MovieVO) request.getAttribute("movie");
			%>
			<fieldset>
				<legend>Movie Update</legend>
				<ul class=update>
					<li>Movie ID : <input type="text" name="movieid"
						value=<%=movie.getMovieid()%> readonly></li>
					<br>
					<li>Movie Name : <input type="text" name="moviename"
						value='${movie.getMoviename()}' autofocus></li>
					<br>
					<li>Movie Genre : <input type="text" name="moviegenre"
						value='${movie.getMoviegenre()}'></li>
					<br>
					<li>Room : <input type="text" name="room"
						value='${movie.getRoom()}'></li>
					<br>
					<li>Movie Time : <input type="text" name="movietime"
						value='${movie.getMovietime()}'></li>
				</ul>
			</fieldset>
			<br>
			<fieldset>
				<input type="submit" name="submit" value="최종 수정"> <input
					type="reset" name="reset" value="다시 작성">
			</fieldset>
		</form>
	</div>
</body>
</html>