package com.jykj.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

@WebFilter(
	filterName="CommonFilter",
	dispatcherTypes={DispatcherType.REQUEST, DispatcherType.FORWARD},
	initParams={
		@WebInitParam(name="encode",value="utf-8"),
		@WebInitParam(name="dbType",value="oracle")
	},
	urlPatterns={"/*"}
)
public class CommonFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		//response.setCharacterEncoding("utf-8");
		chain.doFilter(new MyRequest((HttpServletRequest)req), response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String encode = config.getInitParameter("encode");
		String dbType = config.getInitParameter("dbType");
		
		String filterName = config.getFilterName();
		System.out.println("filterName-->"+filterName);
		System.out.println("encode-->"+encode);
		System.out.println("dbType-->"+dbType);
	}

}


class MyRequest extends HttpServletRequestWrapper{

	public MyRequest(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		String method = getMethod();
		if("get".equalsIgnoreCase(method)) {
			String value = super.getParameter(name);
			try {
				value = new String(value.getBytes(),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return value;
		} else if("post".equalsIgnoreCase(method)) {
			try {
				super.getRequest().setCharacterEncoding("utf-8");
				return super.getParameter(name);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return super.getParameter(name);
	}
	
	
	public static void main(String[] args) {
	}
}
