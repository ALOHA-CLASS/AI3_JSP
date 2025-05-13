package listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import util.LoginManager;

@WebListener
public class SessionListener implements HttpSessionListener {

    public SessionListener() {
        System.out.println("세션 리스너 객체 생성");
    }

    public void sessionCreated(HttpSessionEvent se)  { 
         String sessionId = se.getSession().getId();
         se.getSession().setMaxInactiveInterval(60);  // 세션 유효시간을 : 1분
         System.out.println("세션 생성 - SESSIONID : " + sessionId);
         System.out.println("-------------------------");
         LoginManager.getInstance().allUsers();
    }

    public void sessionDestroyed(HttpSessionEvent se)  {
    	String sessionId = se.getSession().getId();
        System.out.println("세션 종료 - SESSIONID : " + sessionId);
        // 로그인 사용자 정보 제거
        LoginManager.getInstance().removeUser(sessionId);
        System.out.println("-------------------------");
        LoginManager.getInstance().allUsers();
    }
    
	
}
