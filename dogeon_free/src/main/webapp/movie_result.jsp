<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Service Programming Homework</title>
<link rel="stylesheet" href="resources/student.css" type="text/css"></link>
</head>
<body>
	<header>
		TUKOREA 2022 <br> Web Service Programming Community
	</header>
	<p id=sect><%=request.getAttribute("greetings")%><br>
	<div>
		<%
		MovieVO movie = (MovieVO) request.getAttribute("movie");
		%>
		<table>
			<tr>
				<td>영화 이름</td>
				<td>영화 장르</td>
				<td>상영관</td>
				<td>상영 시간</td>
			</tr>
			<tr>
				<td><%=movie.getMoviename()%></td>
				<td><%=movie.getMoviegenre()%></td>
				<td><%=movie.getRoom()%></td>
				<td><%=movie.getMovietime()%></td>
			</tr>
		</table>
		<br> <a
			href="http://localhost:8080/dogeon_free/MovieServlet?cmd=list"
			target="_self">상영 영화 목록 보기 </a>
	</div>
</body>
</html>