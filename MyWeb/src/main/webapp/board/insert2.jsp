<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/error.jsp" %>

<!-- 로그인 체크 모듈 include (소스를 포함시키자)------------ -->
<%@ include file="/login/loginCheckModule.jsp" %>
<!-- --------------------------------------------- -->
    
<jsp:useBean id="vo" class="bbs.model.BbsVO" scope="page"/>
<%-- BbsVO vo = new BbsVO()와 동일 --%>

<jsp:setProperty name="vo" property="*"/>

<%-- 
input name과 VO객체의 property명이 같을 경우 "*"로 처리할 수 있다
vo.setTitle(request.getParameter("title")); 
--%>

<jsp:useBean id="dao" class="bbs.model.BbsDAO" scope="session" />
<%-- 
	scope : 객체가 살아있는 유효 범위
	[1] page : 하나의 페이지에서만 유효
	[2] request : 하나의 요청(request)을 처리하는 동안 유효
	[3] session : 하나의 브라우저를 사용하는 동안 유효
	[4] application : 서버 시작 ~ 서버 종료시까지 객체가 살아있음
	
	page < request < session < application
--%>

<%
	int n = dao.insertBbs(vo);
	response.sendRedirect("list.jsp");
%>
