package listener;

import java.util.concurrent.atomic.AtomicInteger;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 세션이 생성될 때
     * - 방문자 수를 1씩 증가
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	ServletContext application = se.getSession().getServletContext();
    	AtomicInteger visitorCount = (AtomicInteger) application.getAttribute("visitorCount");
    	if( visitorCount == null ) {
    		visitorCount = new AtomicInteger(0);
    	}
    	// 방문자 수 1 증가
    	int currentCount = visitorCount.incrementAndGet();
    	application.setAttribute("visitorCount", visitorCount);
    	System.out.println("현재 방문자 수 : " + currentCount);
    }

    /**
     * 세션이 종료될 때
     * - 방문자 수를 1씩 감소
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	ServletContext application = se.getSession().getServletContext();
    	AtomicInteger visitorCount = (AtomicInteger) application.getAttribute("visitorCount");
    	int count = 0;
    	if( visitorCount != null ) {
    		count = visitorCount.decrementAndGet();
    		System.out.println("사용자가 나갔습니다.");
    	}
    	// 방문자 수 1 증가
    	application.setAttribute("visitorCount", count);
    	System.out.println("현재 방문자 수 : " + count);
    }
	
}






