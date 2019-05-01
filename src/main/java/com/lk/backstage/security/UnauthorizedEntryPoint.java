package com.lk.backstage.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**  
 * Title: UnauthorizedEntryPoint
 * Description: 
 * @author linkan  
 * @date 2019年1月23日  
 */
@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

	/* (non-Javadoc)  
	 * Title: commence
	 * Description: 
	 * @param request
	 * @param response
	 * @param authException
	 * @throws IOException
	 * @throws ServletException  
	 * @see org.springframework.security.web.AuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)  
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		 if(isAjaxRequest(request)){
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
	     }else{
	        response.sendRedirect("/login");
	     }
	}
	
	 public static boolean isAjaxRequest(HttpServletRequest request) {
	    String ajaxFlag = request.getHeader("X-Requested-With");
	    return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
	 }

}
