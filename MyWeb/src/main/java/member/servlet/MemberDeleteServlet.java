package member.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDAO;
import member.model.MemberVO;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/MemberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException{
		doGet(req, res);
	}
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		req.setCharacterEncoding("utf-8");
		
		
		String id = req.getParameter("id");
		
		
		out.println(id + "<br>");
		
		if(id == null || id.trim().isEmpty()) {
			res.sendRedirect("member/mypage.html");
			// sendRedirect 이해
			// 페이지 이동을 시킴 -> 브라우저의 url을 member/mypage.html로
			// 변경해서 서버에 새롭게 요청을 보내는 방식으로 페이지를 이동한다
			
			return;
		}
		
		MemberDAO userDao = new MemberDAO();
		
		try {
			int n = userDao.deleteMember(id);
			String msg = (n>0) ? "탈퇴 완료! 홈페이지로 이동합니다." : "회원탈퇴 실패 - id를 확인하세요"; 
			String loc = (n>0) ? "index.html" : "javascript:history.back()";
			out.println("<script>");
			out.println("alert('" + msg + "')");
			out.println("location.href='" + loc + "'");
			out.println("</script>");
			
			
		} catch(SQLException e) {
			out.println("<b>서버 에러 : " + e.getMessage() + "</b><br>");
			e.printStackTrace();
		}
		
		out.close();
		
		
		
		
		
	}


}
