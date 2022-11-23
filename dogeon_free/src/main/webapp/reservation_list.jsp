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
				<td>예매 번호</td>
				<td>UserId</td>
				<td>MovieId</td>
				<td>MovieName</td>
				<td>SeatNumber</td>
				<td>관리</td>
			</tr>

			<%
			pageContext.setAttribute("loginId", session.getAttribute("loginId"));
			pageContext.setAttribute("admin", session.getAttribute("admin"));
			List<ReservationVO> reservationList = (List<ReservationVO>) request.getAttribute("reservationList");
			for (ReservationVO vo : reservationList) {
				pageContext.setAttribute("userid", vo.getUserid());
			%>
			<tr>
				<td><%=vo.getReserveid()%></td>
				<td><%=vo.getUserid()%></td>
				<td><%=vo.getMovieid()%></td>
				<td><%=vo.getMoviename()%></td>
				<td><%=vo.getSeatnumber()%></td>
				<td><c:if test="${loginId == userid && !admin}">
						<a
							href="http://localhost:8080/dogeon_free/ReservationServlet?cmd=delete&reserveid=<%=vo.getReserveid()%>"
							target="_self"> 예매 취소</a>
					</c:if> <c:if test="${admin}">
						<a
							href="http://localhost:8080/dogeon_free/ReservationServlet?cmd=delete&reserveid=<%=vo.getReserveid()%>"
							target="_self"> 예매 취소</a>
					</c:if></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>