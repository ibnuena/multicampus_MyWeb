<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/inc/top.jsp" />
<style>
	li{
		list-style: none;
	}
</style>
		<!-- content -->
		<div class="container">
			<h1>Index Page</h1>
			<hr>
			
			<ul>
				<li><a href="example/ex09_dbcpOracle.jsp">DBCP-jdbc/myoracle</a></li>
				<li><a href="example/ex09_dbcpMySQL.jsp">DBCP-jdbc/mysql</a></li>
				<li><a href="index.html">서블릿 관련-회원 관리</a></li>
				<li><a href="login/sessionTest.jsp">session Test</a></li>
				<li><a href="login/cookieTest.jsp">cookie Test</a></li>
				<li><a href="login/cookieList.jsp">쿠키 목록</a></li>
			</ul>
		</div>
		
<jsp:include page="/inc/foot.jsp" />
		