package board.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

import board.DTO.PersistenceLogins;
import board.DTO.Users;
import board.Service.PersistenceLoginsService;
import board.Service.PersistenceLoginsServiceImpl;
import board.Service.UserService;
import board.Service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
       
	/**
	 * [GET]
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// url : /users/idCheck
		String root = request.getContextPath();
		String path = request.getPathInfo();		
		String page = "";
		
		// /idCheck - 아이디 중복 확인
		if( path.equals("/idCheck") ) {
			System.out.println("아이디 중복 확인...");
			String username = request.getParameter("username");
			boolean check = userService.idCheck(username);
			response.getWriter().print(check);
		}
		
		// /logout - 로그아웃
		if( path.equals("/logout") ) {
			System.out.println("로그아웃...");
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(root + "/");
		}
		
		
	}

	/**
	 * [POST]
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();			// /Board
		String path = request.getPathInfo();			// /idCheck
		// /join - 회원가입
		if( path.equals("/join") ) {
			System.out.println("회원가입 요청 처리...");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			Users user = Users.builder()
							  .id( UUID.randomUUID().toString() )
							  .username(username)
							  .password(password)
							  .name(name)
							  .email(email)
							  .build();
			int result = userService.join(user);
			// 회원가입 성공
			if( result > 0 ) {
				response.sendRedirect(root + "/");
			} 
			// 회원가입 실패
			else {
				response.sendRedirect(root + "/join.jsp?error=true");
			}
		}
		
		// /login - 로그인
		if( path.equals("/login") ) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			// 아이디 저장 ---------------------------------------------------
			String rememberId = request.getParameter("rememberId");
			Cookie cookieRememberId = new Cookie("rememberId", "");
			Cookie cookieUsername = new Cookie("username", "");
			cookieRememberId.setPath("/");
			cookieUsername.setPath("/");
			
			
			System.out.println("rememberId : " + rememberId);
			
			// 아이디 저장 체크 시 - 값 : on
			if( rememberId != null && rememberId.equals("on") ) {
				// 쿠키 생성
				cookieRememberId.setValue( URLEncoder.encode(rememberId, "UTF-8") );
				cookieUsername.setValue( URLEncoder.encode(username, "UTF-8") );
				// 쿠키 만료시간 설정 - 7일 (/초)
				cookieRememberId.setMaxAge(60*60*24*7); 
				cookieUsername.setMaxAge(60*60*24*7);
			}
			// 아이디 저장 체크 해제 시
			else {
				// 쿠키 삭제 - 쿠키 유효시간을 0으로 하고 응답
				cookieRememberId.setMaxAge(0);
				cookieUsername.setMaxAge(0);
			}
			// 응답에 쿠키 등록
			response.addCookie(cookieRememberId);
			response.addCookie(cookieUsername);
			// 아이디 저장 끝 ---------------------------------------------------
			
			// 자동 로그인 ---------------------------------------------------
			String rememberMe = request.getParameter("rememberMe");
			Cookie cookieRememberMe = new Cookie("rememberMe", "");
			Cookie cookieToken = new Cookie("token", "");
			
			// 쿠키 설정
			cookieRememberMe.setPath("/");
			cookieToken.setPath("/");
			// 쿠키 만료시간 설정 - 7일 (/초)
			cookieRememberMe.setMaxAge(60*60*24*7); 
			cookieToken.setMaxAge(60*60*24*7);		
			// 자동 로그인 체크 여부 
			if( rememberMe != null && rememberMe.equals("on") ) {
				// 자동 로그인 체크 시
				// - 토큰 발행
				PersistenceLoginsService persistenceLoginsService = new PersistenceLoginsServiceImpl();
				PersistenceLogins persistenceLogins = persistenceLoginsService.refresh(username);
				String token = null;
				if( persistenceLogins != null ) {
					token = persistenceLogins.getToken();
				}
				// - 쿠키 생성
				cookieRememberMe.setValue( URLEncoder.encode(rememberMe, "UTF-8") );
				cookieToken.setValue( URLEncoder.encode(token, "UTF-8") );
			} 
			else {
				// 자동 로그인 미체크 시
				// 쿠키 삭제
				cookieRememberMe.setMaxAge(0);
				cookieToken.setMaxAge(0);
			}
			response.addCookie(cookieRememberMe);
			response.addCookie(cookieToken);
			// 자동 로그인 끝 ---------------------------------------------------
			
			// 로그인 처리
			Users user = Users.builder()
							  .username(username)
							  .password(password)
							  .build();
			boolean result = userService.login(user);
			// 로그인 성공
			if( result ) {
				// 회원 조회
				Users loginUser = userService.selectByUsername(username);
				loginUser.setPassword(null);
				// 세션에 사용자 정보 등록
				HttpSession session = request.getSession();
				session.setAttribute("loginId", user.getUsername());
				session.setAttribute("loginUser", loginUser);
				response.sendRedirect(root + "/");
			} 
			// 로그인 실패
			else {
				response.sendRedirect(root + "/login.jsp?error=true");
			}
		}
	}

}