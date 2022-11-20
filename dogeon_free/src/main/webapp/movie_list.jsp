<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*, java.util.List"%>
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
		<a href="http://localhost:8080/dogeon_free/welcome.html"
			target="_self">메인 페이지 이동 </a>
		<table>
			<tr>
				<td>영화 이름</td>
				<td>영화 장르</td>
				<td>상영관</td>
				<td>상영 시간</td>
				<td>관리</td>
			</tr>

			<%
			List<MovieVO> movieList = (List<MovieVO>) request.getAttribute("movieList");
			for (MovieVO vo : movieList) {
			%>
			<tr>
				<td><%=vo.getMoviename()%></td>
				<td><%=vo.getMoviegenre()%></td>
				<td><%=vo.getRoom()%></td>
				<td><%=vo.getMovietime()%></td>
				<td><a
					href="http://localhost:8080/dogeon_free/MovieServlet?cmd=update&movieid=<%=vo.getMovieid()%>"
					target="_self"> 수정</a> <a
					href="http://localhost:8080/dogeon_free/MovieServlet?cmd=delete&movieid=<%=vo.getMovieid()%>"
					target="_self"> 삭제</a></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>