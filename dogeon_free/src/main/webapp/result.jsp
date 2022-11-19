<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<br> <a
			href="http://localhost:8080/dogeon_free/UserServlet?cmd=list"
			target="_self">전체 회원 목록 보기 </a>
	</div>
</body>
</html>