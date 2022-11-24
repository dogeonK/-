<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Service Programming Homework</title>
<link rel="stylesheet" href="resources/cinema.css" type="text/css"></link>
</head>
<body>
	<header> TUK CINEMA </header>
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
	<p id=sect><%=request.getAttribute("greetings")%><br>
	<div>
		<%
		UserVO user = (UserVO) request.getAttribute("user");
		%>
		<table>
			<tr>
				<td>계정</td>
				<td>이름</td>
				<td>계좌번호</td>
				<td>핸드폰</td>
				<td>메일주소</td>
				<td>UserType</td>
			</tr>
			<tr>
				<td><%=user.getUserid()%></td>
				<td><%=user.getUsername()%></td>
				<td><%=user.getAnum()%></td>
				<td><%=user.getMobile()%></td>
				<td><%=user.getEmail()%></td>
				<%
				pageContext.setAttribute("type", user.getUsertype());
				%>
				<td><c:if test="${type == 0}">
						관리자
					</c:if> <c:if test="${type == 1}">
						회원
					</c:if></td>
			</tr>
		</table>
		<br>
	</div>
</body>
</html>