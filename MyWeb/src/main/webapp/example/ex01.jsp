<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- page directive element -->

<h1>처음 해보는 JSP</h1>

<!-- script element -->
<%
// scriptlet 태그
	int a = 10;
	int b = 20;
	// 내장 객체 사용 가능
	out.println("<h2>" + a + "+" + b + "=" + (a+b) + "</h2>");
	// out : JspWriter 타입의 출력 스트림
%>

<%-- <% int c=10; %> --%>
<%-- jsp 주석 --%>
<%!
// declaration : 메서드를 구성할 수 있다
	String str = "나는 멤버변수(인스턴스 변수)";
	public String sayHello(String name){
		return "<h2>Hello " + name + "</h2>";
	}
%>
<%-- expression : 출력식
		<%=변수 %> == out.println(변수);
 --%>
 
<%=str %><br>
<!-- sayHello() 호출해서 결과값 출력 -->
<%= sayHello("Tom") %>

<%-- 
JSP가 서블릿으로 변환되어 실행된다
이클립스에서 서블릿 코드가 생성되는 곳
C:\Users\admin\eclipse-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\MyWeb\org\apache\jsp\example

 --%>

 