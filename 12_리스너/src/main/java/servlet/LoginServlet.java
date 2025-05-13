package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.LoginManager;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plain; charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 로그인 처리
		boolean isAuthenticated = username != null && !username.isEmpty();
		
		if( !isAuthenticated ) {
			response.getWriter().println("로그인 실패!");
			return;
		}
		
		LoginManager loginManager = LoginManager.getInstance();
		
		// 중복 로그인 방지
		if( loginManager.isUsing(username) ) {
			response.getWriter().println("이미 로그인된 계정입니다.");
			System.out.println("중복 로그인!");
		} else {
			HttpSession session =  request.getSession();
			loginManager.addUser(username, session.getId());
			session.setAttribute("username", username);
			response.getWriter().println("로그인 성공!");
		}
	}

}






