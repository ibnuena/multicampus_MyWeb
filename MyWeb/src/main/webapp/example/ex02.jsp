<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="java.util.*, java.text.*" %>

<h1>반복문으로 오늘 날짜 5번 출력</h1>

<hr>

<%

	Date today = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	for(int i=0; i<5; i++){
%>

<h2> <%=i %>  오늘 날짜 : <%=sdf.format(today)%></h2>

<%
	}
%>
<hr>
<h2>구구단</h2>

<table border="1" style="width:200px">
	<% 
		for(int i=1; i<10;i++){
	%>
	<tr>
		<td style="text-align:center">8x<%=i %>=<%=i*8 %></td>
	</tr>
	<%
		}
	%>
</table>