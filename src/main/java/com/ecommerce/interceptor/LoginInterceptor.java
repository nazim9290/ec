package com.ecommerce.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.entity.UserEntity;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// TODO Auto-generated method stub
		Object loginUser = request.getSession().getAttribute("loginUser");

		if (loginUser == null) {
			response.sendRedirect("/user/login");
			return false;
		} else {
			
			UserEntity userEntity = (UserEntity) loginUser;

			StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());

			if (requestURL.indexOf("admin") != -1) {
				if (userEntity.getUser_role().equals("admin")) {
					return true;
				} else {
					response.sendRedirect("/product");
					return false;
				}
			}
			
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
