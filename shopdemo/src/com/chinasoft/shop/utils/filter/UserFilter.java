package com.chinasoft.shop.utils.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.chinasoft.shop.user.domain.User;




@WebFilter(urlPatterns={"/cart.jsp","/usercenter/*","/admin/*"})
public class UserFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		User sessionUser = (User)req.getSession().getAttribute("user");
		if (sessionUser==null) {
			req.setAttribute("errorMsg", "ÄãµÇÂ¼Ã»ÓÐ£¿¶º±Æ£¡");
			req.getRequestDispatcher("/login.jsp").forward(req, response);
		}else{
			chain.doFilter(req, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}