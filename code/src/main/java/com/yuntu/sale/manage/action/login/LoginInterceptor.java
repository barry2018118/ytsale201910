package com.yuntu.sale.manage.action.login;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception exc) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,  
            Object handler, ModelAndView modelAndView) throws Exception {
	}
	/**
	 * handler执行之前调用这个方法
	 */
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		 // 获取Session  
		 HttpSession session = request.getSession();  
		 Object user = session.getAttribute("session_user");  
		 if(user != null){  
			 return true;  
		 }  
		 if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
			 response.getWriter().write("refresh");
			 return false;
		 }
		 // 不符合条件的，跳转到登录界面  
		 response.sendRedirect(request.getContextPath()+"/login");
		 return false;  
	 }  
}