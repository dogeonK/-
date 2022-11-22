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
	<header> Member List </header>
	<hr>
	<div>
		<a href="http://localhost:8080/dogeon_free/welcome.jsp" target="_self">메인
			페이지 이동 </a>
		<table>
			<tr>
				<td>계정</td>
				<td>이름</td>
				<td>계좌번호</td>
				<td>핸드폰</td>
				<td>메일주소</td>
				<td>UserType</td>
				<td>관리</td>
			</tr>

			<%
			List<UserVO> userList = (List<UserVO>) request.getAttribute("userList");
			for (UserVO vo : userList) {
			%>
			<tr>
				<td><%=vo.getUserid()%></td>
				<td><%=vo.getUsername()%></td>
				<td><%=vo.getAnum()%></td>
				<td><%=vo.getMobile()%></td>
				<td><%=vo.getEmail()%></td>
				<%
				pageContext.setAttribute("type", vo.getUsertype());
				pageContext.setAttribute("userid", vo.getUserid());
				pageContext.setAttribute("loginId", session.getAttribute("loginId"));
				pageContext.setAttribute("admin", session.getAttribute("admin"));
				%>
				<td><c:if test="${type == 0}">
						관리자
					</c:if> <c:if test="${type == 1}">
						회원
					</c:if></td>

				<td><c:if test="${admin}">
						<a
							href="http://localhost:8080/dogeon_free/UserServlet?cmd=update&userid=<%=vo.getUserid()%>"
							target="_self"> 수정</a>
						<a
							href="http://localhost:8080/dogeon_free/UserServlet?cmd=delete&userid=<%=vo.getUserid()%>"
							target="_self"> 삭제</a></td>
				</c:if>

				<c:if test="${loginId == userid && !admin}">
					<a
						href="http://localhost:8080/dogeon_free/UserServlet?cmd=update&userid=<%=vo.getUserid()%>"
						target="_self"> 수정</a>
					<a
						href="http://localhost:8080/dogeon_free/UserServlet?cmd=delete&userid=<%=vo.getUserid()%>"
						target="_self"> 삭제</a>
					</td>
				</c:if>
			</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>