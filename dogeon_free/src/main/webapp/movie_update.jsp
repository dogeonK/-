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
	<div align="center">
		<header>Movie Update</header>
		<HR>
		<form
			action="http://localhost:8080/dogeon_free/MovieServlet?cmd=update"
			method="post">
			<%
			MovieVO movie = (MovieVO) request.getAttribute("movie");
			%>
			<fieldset>
				<legend>Information Update</legend>
				<ul>
					<li>Movie ID : <input type="text" name="movieid" value=<%=movie.getMovieid()%>
						readonly></li>
					<li>Movie Name : <input type="text" name="moviename"
						value='${movie.getMoviename()}' autofocus></li>
					<li>Movie Genre : <input type="text" name="moviegenre"
						value='${movie.getMoviegenre()}'></li>
					<li>Room : <input type="text" name="room"
						value='${movie.getRoom()}'></li>
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