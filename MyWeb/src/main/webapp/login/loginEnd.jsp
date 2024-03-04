<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.*" %>

<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String saveId = request.getParameter("saveId"); // 아이디 저장 체크박스
	System.out.println("saveId : " + saveId);
	
	if(id==null || pw==null || id.trim().isBlank() || pw.trim().isBlank()){
		response.sendRedirect("login.jsp");
		return;
	}
	
	MemberDAO userDao = new MemberDAO();
	int result=userDao.loginCheck(id, pw);
	
	// 회원 인증 성공시 세션에 id 저장
	if(result>0){
		// session 내장객체 : HttpSession 타입
		session.setAttribute("loginId", id);
		// 하나의 브라우저를 사용하는 동안 또는 세션 타임아웃(30) 되기 전까지
		// 세션에 저장한 정보는 유지됨
		
		Cookie ck = new Cookie("uid", id);
		if(saveId!=null){ // saveId에 체크했다면 -> 쿠키에 "uid"라는 키값으로 로그인한 사람의 아이디 저장, 유효시간 7주
			ck.setMaxAge(7*24*60*60);
		}
		else{ // saveId에 체크하지 않았다면 -> 쿠키 삭제
			ck.setMaxAge(0);
		}
		ck.setPath("/");
		// response에 쿠키 추가
		response.addCookie(ck);

		// 쿠키 꺼내기 -> login.jsp에서 한다
	}
	String msg = (result>0) ? "환영합니다 " + id + "님" : "아이디 또는 비밀번호가 일치하지 않습니다.";
	String loc = (result>0) ? "../index.jsp" : "javascript:history.back()";
%>
<script>
	alert('<%=msg%>');
	location.href='<%=loc%>';
</script>