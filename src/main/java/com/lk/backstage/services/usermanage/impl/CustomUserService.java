package com.lk.backstage.services.usermanage.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lk.backstage.entity.usermanage.Role;
import com.lk.backstage.entity.usermanage.SecurityUser;
import com.lk.backstage.entity.usermanage.User;
import com.lk.backstage.services.usermanage.UserService;


/**  
 * Title: CustomUserService
 * Description: 自定义用户服务类
 * @author linkan  
 * @date 2019年1月11日  
 */
@Service
public class CustomUserService implements UserDetailsService {
	
	@Resource
	private UserService userService;
	
	/* 
	 * Title: loadUserByUsername
	 * Description: 
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException  
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)  
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {	
		List<User> userList = userService.findByName(userName);
		if(userList.size()==0)
			throw new UsernameNotFoundException("用户名不存在，请重新输入");
		User user = userList.get(0);
		String password = user.getPassword();
		boolean enabled = user.getEnabled()==1?true:false;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = user.getLocked()==0?true:false;
		//获取用户角色
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (userName != null) {
			//获取用户关联角色
	 		List<Role> roleList = userService.findRoleByName(userName);		
	 		if(roleList!=null && roleList.size()>0) {
	 			for (Role role : roleList) {
	 				String roleName = role.getRoleName();
	 				authorities.add(new SimpleGrantedAuthority(roleName));
	 			}
	 		}
		}
		
		SecurityUser securityUser = new SecurityUser(
				userName, 
				password, 
				enabled, 
				accountNonExpired, 
				credentialsNonExpired, 
				accountNonLocked, 
				authorities);
		securityUser.setUserId(user.getUserId());
		securityUser.setUserName(userName);
		securityUser.setPassword(password);
		securityUser.setAddress(user.getAddress());
		securityUser.setEmail(user.getEmail());
		securityUser.setName(user.getName());
		securityUser.setCreateTime(user.getCreateTime());
		securityUser.setModifyTime(user.getModifyTime());
		securityUser.setEnabled(user.getEnabled());
		securityUser.setLocked(user.getLocked());
		securityUser.setSex(user.getSex());
		securityUser.setProfile_picture(user.getProfile_picture());
		securityUser.setBirthday(user.getBirthday());
		securityUser.setDepartment(user.getDepartment());
		return securityUser;
	}

}
