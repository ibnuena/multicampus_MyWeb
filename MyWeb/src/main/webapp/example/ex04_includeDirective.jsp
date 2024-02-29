<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>ex04_includeDirective 페이지</h1>

<p>
	include 디렉티브를 이용해서 ex03_gugudan.jsp 페이지를 아래에 포함시킵니다
	<br>
	include 지시어는 다른 파일(ex03_gugudan.jsp)의 소스코드를 현재 위치에 삽입한 후, 
	jsp 파일을 servlet으로 변환하고 컴파일하는 방식입니다.
</p>
<hr color="pink">

<%@ include file="ex03_gugudan.jsp" %>

<hr color="blue">
<%@ include file="ex01.jsp" %>
<%
	// String a = ""; // error. 이미 ex01.jsp에 a 변수가 있음
	out.println("str : " + str);
%>