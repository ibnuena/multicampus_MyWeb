package member.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDAO;
import member.model.MemberVO;

@WebServlet("/MemberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();// html을 내보낼 거면 이거, 2byte(문자열) 기반 출력 스트림 
		// ServletOutputStream sos = res.getOutputStream(); // 이미지 내보낼 거면 이거, 1byte 기반 출력 스트림 
		
		// 1. 사용자가 입력한 값 받기 
		String id = req.getParameter("id");
		
		out.println("<b>" +id +"<b>" +"<br>");
		System.out.println("uid: " +id +"<br>");
		
		// 2. 유효성 체크 (name, id, pw)
		if(id == null || id.trim().isEmpty()) {
			res.sendRedirect("member/mypage.html");
			// 페이지 이동을 시킴 -> 브라우저의 url을 member/mypage.html로 변경
			// 서버에 세롭개 요청을 보내는 방식으로 페이지 이동 
			return;
		}
		
		System.out.println("여기를 수행할까요?");
		// 4. MemberDAO 객체 생성 후 insertMember() 호출 -> int 값 반환해
		MemberDAO userDao = new MemberDAO();

		try {
			MemberVO user = userDao.findById(id.trim());
//			out.println("user: " +user +", name: " +user.getName() +"<br>");
		
			// 결과값을 받아서 request 또는 session에 저장해서
			req.setAttribute("user", user); // key, value 쌍으로 저장
			
			
			// 화면단으로 이동시킨다 -> edit.jsp
			/* # 페이지 이동 방식
			 * <1> redirect방식으로 이동 =>sendRedirect(이동할 페이지) 이용
			 * 		- 지정된 페이지로 서버에 새롭게 요청을 보내서 응답하는 방식
			 *      - 브라우저의 url을 이동할 페이지로 바꿔서 새로운 요청을 보낸다
			 *        따라서 request에 저장한 값이 있으면 꺼내서 사용할 수 없다.(왜? 새로운 request를 보내므로)
			 *      - 브라우저 url은 이동한 페이지의 url을 나타낸다.  
			 * <2> forward방식으로 이동 => RequestDispatcher의 forward() 이용
			 *      - 서버 내부에서 지정된 페이지로 이동한다
			 *      - 이동된 페이지에서는 request를 함께 공유한다
			 *        따라서 request에 저장한 값이 있으면 이동된 페이지에서 꺼내서 사용할 수 있다
			 *      - 브라우저의 url은 이전 url을 가리킨다.  
			 * */
		
			// redirect
//			res.sendRedirect("member/edit.jsp");
			
			
			RequestDispatcher disp = req.getRequestDispatcher("member/edit.jsp"); // 이동할 페이지 지정
			disp.forward(req, res); // forward 방식으로 이동
			
			
			
		} catch(SQLException e) {
//			out.println("<b>서버 에러: " +e.getMessage() +"</b></br>");
			throw new ServletException(e); // 브라우저에 스택기록 출력
//			e.printStackTrace();
		} 
		out.close();
		
	}

}
