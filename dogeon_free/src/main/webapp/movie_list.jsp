<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*, java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Service Programming Homework</title>
<link rel="stylesheet" href="resources/student.css" type="text/css"></link>
</head>
<body>
	<header> Movie List </header>
	<hr>
	<div>
		<a href="http://localhost:8080/dogeon_free/welcome.jsp" target="_self">메인
			페이지 이동 </a>
		<table>
			<tr>
				<td>영화 이름</td>
				<td>영화 장르</td>
				<td>상영관</td>
				<td>상영 시간</td>
				<td>관리</td>
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
						<a
							href="http://localhost:8080/dogeon_free/ReservationServlet?cmd=reservation&movieid=<%=vo.getMovieid()%>"
							target="_self"> 예매</a>
					</c:if> <c:if test="${admin}">
						<a
							href="http://localhost:8080/dogeon_free/MovieServlet?cmd=update&movieid=<%=vo.getMovieid()%>"
							target="_self"> 수정</a>
						<a
							href="http://localhost:8080/dogeon_free/MovieServlet?cmd=delete&movieid=<%=vo.getMovieid()%>"
							target="_self"> 삭제</a>
					</c:if></td>
			</tr>
			<%
			}
			%>
		</table>
		<a href="http://localhost:8080/dogeon_free/ReservationServlet?cmd=list" target="_self">예매 목록보기</a><br>
		<c:if test="${admin}">
		<a href="http://localhost:8080/dogeon_free/movie_add.html" target="_self">상영 영화 추가하기 </a>
		</c:if>
	</div>
</body>
</html>