package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

//@WebFilter("/TestFilter")		// 필터 패턴 경로
public class TestFilter extends HttpFilter implements Filter {
       
    /**
     * 생성자
     */
    public TestFilter() {
        super();
    }



	/**
	 * 필터를 초기화하는 메소드
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("TestFilter 초기화...");
	}
	
	/**
	 * 필터에서 처리할 작업을 작성하는 메소드
	 */
	public void doFilter( ServletRequest request
						 ,ServletResponse response
						 ,FilterChain chain) throws IOException, ServletException {
		System.out.println("TestFilter 필터 처리 작업...");
		
		// 필터 체인의 다음 필터로 요청과 응답을 넘겨주는 메소드
		chain.doFilter(request, response);
	}
	/**
	 * 필터가 제거될 때 호출되는 메소드
	 */
	public void destroy() {
		System.out.println("TestFilter 제거...");
	}
}




