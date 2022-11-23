<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*, tukorea.web.club.service.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Service Programming Homework</title>
<link rel="stylesheet" href="resources/student.css" type="text/css"></link>
</head>
<body>
	<%
	MovieVO movie = (MovieVO) request.getAttribute("movie");
	ReservationService service = new ReservationService();
	%>
	<div align="center">
		<header>Member Update</header>
		<HR>
		<form
			action="http://localhost:8080/dogeon_free/ReservationServlet?cmd=reservation&movieid=<%=movie.getMovieid()%>"
			method="post">
			<fieldset>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<%
				for (char c = 'A'; c <= 'Z'; c++) {
				%>
				<small><%=c%></small>&nbsp;&nbsp;
				<%
				}
				%>
				<br>
				<%
				for (int i = 1; i <= 6; i++) {
				%>
				<%=i%>
				<%
					for (char c = 'A'; c <= 'Z'; c++) {
						if(service.isReserve(movie.getMovieid(), i, c)){
				%>
				<input type="checkbox" name="seatnumber" value="<%=c%>-<%=i%>"
					disabled>
				<%		} else { %>
				<input type="checkbox" name="seatnumber" value="<%=c%>-<%=i%>">
				<%
						} 
				%>
				<%
					}
				%>
				<br>
				<%=i == 3 ? "<br>" : ""%>
				<%
				}
				%>


			</fieldset>
			<br>
			<fieldset>
				<input type="submit" name="submit" value="좌석 선택"> <input
					type="reset" name="reset" value="다시 선택">
			</fieldset>
		</form>
	</div>
</body>
</html>