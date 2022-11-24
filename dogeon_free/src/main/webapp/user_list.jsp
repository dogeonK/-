<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*, java.util.List"%>
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
	<h3> 회원 목록 </h3>
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
	<div>
		<table>
			<tr class="trName">
				<td class="trName">계정</td>
				<td class="trName">이름</td>
				<td class="trName">계좌번호</td>
				<td class="trName">핸드폰</td>
				<td class="trName">메일주소</td>
				<td class="trName">UserType</td>
				<td class="trName">관리</td>
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
						<a class='manage'
							href="http://localhost:8080/dogeon_free/UserServlet?cmd=update&userid=<%=vo.getUserid()%>"
							target="_self"> 수정</a>
						<a class='manage'
							href="http://localhost:8080/dogeon_free/UserServlet?cmd=delete&userid=<%=vo.getUserid()%>"
							target="_self"> 삭제</a></td>
				</c:if>

				<c:if test="${loginId == userid && !admin}">
					<a class='manage'
						href="http://localhost:8080/dogeon_free/UserServlet?cmd=update&userid=<%=vo.getUserid()%>"
						target="_self"> 수정</a>
					<a class='manage'
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