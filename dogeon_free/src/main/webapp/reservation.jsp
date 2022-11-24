<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="tukorea.web.club.domain.*, tukorea.web.club.service.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Service Programming Homework</title>
<link rel="stylesheet" href="resources/cinema.css" type="text/css"></link>
<script type="text/javascript">
	function clickOnlyOne(itself){
		const checkboxes = document.getElementsByName("seatnumber");
		checkboxes.forEach((checkbox) =>{
			checkbox.checked =false;
		})
		itself.checked = true;
	}
</script>
</head>
<body>
	<%
	MovieVO movie = (MovieVO) request.getAttribute("movie");
	ReservationService service = new ReservationService();
	%>
	<header>
		${movie.getMoviename()} <br>좌석 선택
	</header>
	<HR>
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
	<div align="center">

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
					if (service.isReserve(movie.getMovieid(), i, c)) {
				%>
				<input type="checkbox" name="seatnumber" value="<%=c%>-<%=i%>"
					disabled>
				<%
				} else {
				%>
				<input type="checkbox" name="seatnumber" onclick="clickOnlyOne(this)" value="<%=c%>-<%=i%>">
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