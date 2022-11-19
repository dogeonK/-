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
		<header>Member Update</header>
		<HR>
		<form
			action="http://localhost:8080/dogeon_free/UserServlet?cmd=update"
			method="post">
			<%
			UserVO user = (UserVO) request.getAttribute("user");
			%>
			<fieldset>
				<legend>Information Update</legend>
				<ul>
					<li>ID : <input type="text" name="userid" value=<%=user.getUserid()%>
						readonly></li>
					<li>PASSWORD : <input type="password" name="passwd"
						value=<%=user.getPasswd()%> autofocus></li>
					<li>USERNAME : <input type="text" name="username"
						value=<%=user.getUsername()%>></li>
					<li>ACCOUNTNUMBER : <input type="text" name="snum"
						value=<%=user.getAnum()%>></li>
					<li>MOBILE : <input type="text" name="mobile"
						value=<%=user.getMobile()%>></li>
					<li>EMAIL : <input type="text" name="email"
						value=<%=user.getEmail()%>></li>
					<li>USERTYPE : <input type="text" name="depart"
						value=<%=user.getUsertype()%>></li>
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