package my.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
	Web.xml에 서블렛을 등록하는 대신 @WebServlet이라는 어노테이션을 사용 
	@WebWervlet("/url-pattern") : #주의: url-pattern은 unique한 값을 설정해야함 
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// req를 이용해서 사용자가 입력한 값을 꺼내기
		// String getParameter(input name)
		String uid = req.getParameter("id");
		String upw = req.getParameter("pw");
		System.out.println(uid +"/" + upw); // 톰캣 콘솔에 출력 
		
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		// 유효성 체크 
		if(uid == null || upw == null || uid.trim().isEmpty() || upw.trim().isEmpty()) {
			out.println("<script>");
			out.println("alert('아이디와 비밀번호를 입력하세요.');");
			out.println("history.back();");
			out.println("</script>");
			return;
		}
		
		out.println("<h1 style='color:lightblue'>");
		out.println(uid +"님 환영합니다~");
		out.println("</h1>");
		out.close();
		
	}

}
