package board.filter;

import java.io.IOException;
import java.net.URLDecoder;

import board.DTO.PersistenceLogins;
import board.DTO.Users;
import board.Service.PersistenceLoginsService;
import board.Service.PersistenceLoginsServiceImpl;
import board.Service.UserService;
import board.Service.UserServiceImpl;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(description = "자동 로그인 등, 인증 처리 필터", urlPatterns = { "/*" })
public class LoginFilter extends HttpFilter implements Filter {
	
	private static final long serialVersionUID = 6470731114379833406L;
	
	PersistenceLoginsService persistenceLoginsService;
	UserService userService;
       
    public LoginFilter() {
        super();
    }

    public void init(FilterConfig fConfig) throws ServletException {
    	persistenceLoginsService = new PersistenceLoginsServiceImpl();
    	userService = new UserServiceImpl();
    }
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 쿠키 확인
		// 1. 자동 로그인 여부
		// 2. 인증 토큰
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Cookie[] cookies = httpRequest.getCookies();
		
		String rememberMe = null;		// 자동 로그이 여부
		String token = null;			// 인증 토큰
		
		if( cookies != null ) {
			for (Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				String cookieValue = URLDecoder.decode( cookie.getValue(), "UTF-8") ;
				switch (cookieName) {
					case "rememberMe"	: rememberMe = cookieValue; break;
					case "token"		: token = cookieValue; break;
				}
			}
		}
		System.out.println("LoginFilter...");
		System.out.println("rememberMe : " + rememberMe);
		System.out.println("token : " + token);
		
		// 로그인 여부 확인
		HttpSession session = httpRequest.getSession();
		String loginId = (String) session.getAttribute("loginId");
		Users loginUser = (Users) session.getAttribute("loginUser");
		
		// 이미 로그인 됨
		if( loginId != null && loginUser != null ) {
			chain.doFilter(request, response);
			System.out.println("로그인된 사용자 : " + loginId);
			return;
		}	
		
		
		// 자동 로그인 & 토큰 ok
		if( rememberMe != null && token != null ) {
			System.out.println("rememberMe : " + rememberMe);
			System.out.println("token : " + token);
			PersistenceLogins persistenceLogins = persistenceLoginsService.selectByToken(token);
			System.out.println("persistenceLogins : " + persistenceLogins);
			boolean isValid = persistenceLoginsService.isValid(token);
			// 토큰이 존재, 유효
			if( persistenceLogins != null && isValid ) {
				loginId = persistenceLogins.getUsername();
				loginUser = userService.selectByUsername(loginId);
				System.out.println("loginId : " + loginId);
				System.out.println("loginUser : " + loginUser);
				// 로그인 처리
				session.setAttribute("loginId", loginId);
				session.setAttribute("loginUser", loginUser);
				System.out.println("자동 로그인 성공 : " + loginUser);
			}
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}
}