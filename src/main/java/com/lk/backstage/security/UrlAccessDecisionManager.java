package com.lk.backstage.security;

import java.util.Collection;
import java.util.Iterator;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**  
 * Title: UrlAccessDecisionManager
 * Description: 
 * @author linkan  
 * @date 2019年1月23日  
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {


	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		 	Iterator<ConfigAttribute> iterator = configAttributes.iterator();
       		while (iterator.hasNext()) {
	            ConfigAttribute ca = iterator.next();
	            //当前请求需要的权限
	            String needRole = ca.getAttribute();
	            if ("visitor".equals(needRole)) {
	                return;
	            }else if("loginuser".equals(needRole)){
	            	if (authentication instanceof AnonymousAuthenticationToken) 
		                  throw new AccessDeniedException("未登录");
	            	return;
	            }else{
	                if (authentication instanceof AnonymousAuthenticationToken) {
	                  throw new AccessDeniedException("未登录");
	                } else{                
	                	//当前用户所具有的权限
	                	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	                	for (GrantedAuthority authority : authorities) {
		                    if ("admin".equals(authority.getAuthority()) || authority.getAuthority().equals(needRole)) 
		                        return;
	                	}
	                }
	            } 
       		}
	        throw new AccessDeniedException("权限不足!");
	}


	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}


	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
