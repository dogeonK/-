<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*, java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Service Programming Homework</title>
<link rel="stylesheet" href="resources/cinema.css" type="text/css"></link>
</head>
<body>
	<%
	String loginId = null;
	if (session.getAttribute("loginId") != null) {
		loginId = (String) session.getAttribute("loginId");
	}
	%>
	<header> TUK CINEMA </header>
	<h3>영화 목록</h3>
	<hr>
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
	<div>
		<br> <br> <br>
		<table>
			<tr class="trName">
				<td class="trName">영화 이름</td>
				<td class="trName">영화 장르</td>
				<td class="trName">상영관</td>
				<td class="trName">상영 시간</td>
				<td class="trName">관리</td>
			</tr>

			<%
			pageContext.setAttribute("loginId", session.getAttribute("loginId"));
			pageContext.setAttribute("admin", session.getAttribute("admin"));
			List<MovieVO> movieList = (List<MovieVO>) request.getAttribute("movieList");
			for (MovieVO vo : movieList) {
			%>
			<tr>
				<td><%=vo.getMoviename()%></td>
				<td><%=vo.getMoviegenre()%></td>
				<td><%=vo.getRoom()%></td>
				<td><%=vo.getMovietime()%></td>
				<td><c:if test="${loginId != null}">
						<a class='manage'
							href="http://localhost:8080/dogeon_free/ReservationServlet?cmd=reservation&movieid=<%=vo.getMovieid()%>"
							target="_self"> 예매</a>
					</c:if> <c:if test="${admin}">
						<a class='manage'
							href="http://localhost:8080/dogeon_free/MovieServlet?cmd=update&movieid=<%=vo.getMovieid()%>"
							target="_self"> 수정</a>
						<a class='manage'
							href="http://localhost:8080/dogeon_free/MovieServlet?cmd=delete&movieid=<%=vo.getMovieid()%>"
							target="_self"> 삭제</a>
					</c:if></td>
			</tr>
			<%
			}
			%>
		</table>
		<c:if test="${admin}">
			<br><br>
			<a class='manage' href="http://localhost:8080/dogeon_free/movie_add.html"
				target="_self">상영 영화 추가하기 </a>
		</c:if>
	</div>
</body>
</html>