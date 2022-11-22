<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Service Programming Homework</title>
<link rel="stylesheet" href="resources/student.css" type="text/css"></link>
</head>
<body>
<%
	String loginId = null;
	if(session.getAttribute("loginId") != null){
		loginId = (String)session.getAttribute("loginId");
	}
%>
<%
	if(loginId == null){
%>
		<br> <a
		href="http://localhost:8080/dogeon_free/UserServlet?cmd=login"
		target="_self"> 로그인</a>
<%
	}
	else{
%>
		<strong>${loginId} 님</strong>
		<a href="http://localhost:8080/dogeon_free/UserServlet?cmd=logout"
		target="_self"> 로그아웃</a>
<%
	}
%>
	<header>
		TUKOREA 2022 <br> TUK BOX
	</header>
	<p id=sect>
		반갑습니다.<br> 웹 서비스 프로그래밍 학생들을 위한 커뮤니티입니다. <br> <a
			href="http://localhost:8080/dogeon_free/UserServlet?cmd=join"
			target="_self"> 회원가입</a>
		<br><br>
		전체 회원 목록입니다.<br><a
			href="http://localhost:8080/dogeon_free/UserServlet?cmd=list"
			target="_self">전체 회원 목록 보기 </a>
		<br><br>
		전체 영화 목록입니다.<br><a
			href="http://localhost:8080/dogeon_free/MovieServlet?cmd=list"
			target="_self">상영 영화 목록 보기 </a>
	</p>
</body>
</html>
