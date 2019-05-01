package com.lk.backstage.security;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.lk.backstage.contants.RequestRateLimiterConstant;
import com.lk.backstage.entity.usermanage.User;
import com.lk.backstage.entity.usermanage.UserLockedRecord;
import com.lk.backstage.services.usermanage.UserService;
import com.lk.backstage.utils.DateUtils;

/**  
 * Title: LoginAuthenticationProvider
 * Description: 
 * @author linkan  
 * @date 2019年1月14日  
 */
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginAuthenticationProvider.class);

	@Autowired
	private UserService userService;
	
	public LoginAuthenticationProvider(UserDetailsService userDetailsService) {
        super();
        setUserDetailsService(userDetailsService);
    }
	
	@Override
	public Authentication authenticate(Authentication authentication)
	             throws AuthenticationException {
		 try {
			Authentication auth = super.authenticate(authentication);
            return auth;
         }  catch (BadCredentialsException e) {
     		String userName = authentication.getName();
     		
     		List<User> userList = userService.findByName(userName);
     		if(userList.size()!=1)
     			throw e;
     		
     		User user = userList.get(0);
     		String userId = user.getUserId();
    		boolean reachLimit = RequestRateLimiterConstant.LOGIN_RATE_LIMITER.overLimitWhenIncremented(userName);
    		
    		if(reachLimit){
    			UserLockedRecord userLockedRecord = new UserLockedRecord();
    			userLockedRecord.setUserId(userId);
    			
    			Date lockedTime = new Date();
    			Date unlockedTime = DateUtils.addHours(1);
    			
    			userLockedRecord.setLockedTime(lockedTime);
    			userLockedRecord.setUnlockedTime(unlockedTime);
    			userService.recordLocked(userLockedRecord);
    			logger.info("用户账户锁定");
    			throw new BadCredentialsException("密码输入错误5次，用户已经锁定");
    		}
            throw e;
         }
	}
}
