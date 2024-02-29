<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>welcome.jsp</h1>

<h2 style='color:red'>메세지 : <%=request.getAttribute("msg") %></h2>

<h2>메세지 : <%=session.getAttribute("msg") %></h2>