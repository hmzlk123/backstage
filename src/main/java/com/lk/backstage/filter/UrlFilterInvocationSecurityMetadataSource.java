package com.lk.backstage.filter;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.lk.backstage.entity.usermanage.Role;



/**  
 * Title: UrlFilterInvocationSecurityMetadataSource
 * Description: 
 * @author linkan  
 * @date 2019年1月23日  
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private com.lk.backstage.services.usermanage.MenuService menuService;
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		//获取请求地址
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        if ("/login".equals(requestUrl) || "/login?error=true".equals(requestUrl)) {
            return SecurityConfig.createList("visitor");
        }else{
        	List<Role> roleList = menuService.findRoleByUrl(requestUrl);
        	String roles= "";
        	for (Role role : roleList) {
        		if(StringUtils.isEmpty(roles))
        			roles = role.getRoleName();
        		else
        			roles = roles+","+role.getRoleName();
			}    
        	if(StringUtils.isEmpty(roles))
        		return SecurityConfig.createList("loginuser");
        	else
        		return SecurityConfig.createList(roles.split(","));
        }
	}


	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean supports(Class<?> clazz) {
		 return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
