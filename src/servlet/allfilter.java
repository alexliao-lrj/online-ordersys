package servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class allfilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/"; 
		//访问的jsp/servlet名称
		String origin = request.getServletPath();
		
		String urlFrom = request.getHeader("referer");
		//不是从ordersys/访问（当前地址头），login.jsp除外，就跳转到login.jsp
		if ((null != urlFrom && urlFrom.startsWith(basePath)) || origin.equals("/login.jsp")) {
			arg2.doFilter(arg0, arg1);
		} else {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
