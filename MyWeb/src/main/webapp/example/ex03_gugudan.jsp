<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- ex03_gugudan.jsp -->
<style>
	table{
		width: 80%;
		margin: 3em auto;
	}
	td{
		text-align:center;
		background-color: pink;
	}
	th{
		background-color: gray;
		color: white;
	}
</style>

<h1>구구단</h1>



<table style="width:200px; margin:10px">
	<tr>
	<% 
	for(int dan=2; dan<10;dan++){
	%>
		<th><%=dan %>단</th>
	
	<%
	}
	%>
	</tr>
	<% 
	for(int i=1; i<10;i++){
	%>
	<tr>
		<% 
			for(int j=2; j<10;j++){
		%>
		<td><%=j %>x<%=i %>=<%=i*j %></td>
		<%
		}
		%>
	</tr>
	<%
	}
	%>

</table>
