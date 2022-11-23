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
		ReservationVO reservation = (ReservationVO) request.getAttribute("reservation");
		%>
		<table>
			<tr>
				<td>예매 번호</td>
				<td>UserId</td>
				<td>MovieId</td>
				<td>MovieName</td>
				<td>SeatNumber</td>
			</tr>
			<tr>
				<td><%=reservation.getReserveid()%></td>
				<td><%=reservation.getUserid()%></td>
				<td><%=reservation.getMovieid()%></td>
				<td><%=reservation.getMoviename()%></td>
				<td><%=reservation.getSeatnumber()%></td>
			</tr>
		</table>
		<br> <a
			href="http://localhost:8080/dogeon_free/ReservationServlet?cmd=list"
			target="_self">예매 목록 보기 </a>
	</div>
</body>
</html>