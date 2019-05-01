package com.lk.backstage.security;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.alibaba.druid.util.StringUtils;
import com.lk.backstage.entity.usermanage.MyPasswordEncoder;
import com.lk.backstage.entity.usermanage.SecurityUser;
import com.lk.backstage.filter.UrlFilterInvocationSecurityMetadataSource;
import com.lk.backstage.services.usermanage.impl.CustomUserService;




@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Resource
	public CustomUserService customUserService;
	
	@Autowired
	public UnauthorizedEntryPoint unauthorizedEntryPoint;
	
	@Autowired
	public UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
	 
	@Autowired
	public UrlAccessDecisionManager urlAccessDecisionManager;

	@Bean
	public LoginAuthenticationProvider loginAuthenticationProvider() {
		LoginAuthenticationProvider loginAuthenticationProvider = new LoginAuthenticationProvider(customUserService);
		loginAuthenticationProvider.setHideUserNotFoundExceptions(false);
		loginAuthenticationProvider.setPasswordEncoder(new MyPasswordEncoder());
		return loginAuthenticationProvider;
	}
	
	/**
	 * 
	 * Title: loginSuccessHandler
	 * Description:  登录成功处理
	 * @return
	 */
	@Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { 
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            		Authentication authentication) throws IOException, ServletException {
            	SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
                Collection<? extends GrantedAuthority> authCollection = authentication.getAuthorities();
                StringBuffer roleName = new StringBuffer();
                for (GrantedAuthority auth : authCollection) {
                	if(StringUtils.isEmpty(roleName))
                		roleName.append(auth.getAuthority());
                	else
                		roleName.append(","+auth.getAuthority());
    			}
                logger.info("用户: [" + securityUser.getName() + "], 登录成功 !  角色为：["+roleName+"]。");
                getRedirectStrategy().sendRedirect(request, response, "/");
            }
        };
    }
	
	/**
	 * 
	 * Title: logoutSuccessHandler
	 * Description:  登出处理
	 * @return
	 */
	@Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, 
            		Authentication authentication) throws IOException, ServletException {
                try {
                    SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
                    logger.info("用户: [" + securityUser.getName() + "], 注销成功 ! ");
                } catch (Exception e) {
                    logger.info("注销失败 , 异常: " + e.getMessage());
                }
                response.sendRedirect("/login");
            }
        };
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");
    }
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().headers().frameOptions().disable();
		http
		.exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
		.and()
		.authorizeRequests()
			.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
				@Override
                public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                    o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                    o.setAccessDecisionManager(urlAccessDecisionManager);
                    return o;
                }
			})
			.anyRequest().authenticated()
		.and().formLogin()	 	
			.loginPage("/login")
			.permitAll()
			.successHandler(loginSuccessHandler())
			//.successForwardUrl("/index")
			.failureUrl("/login?error=true")
		.and().logout()
			.permitAll()
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.logoutSuccessHandler(logoutSuccessHandler())
		.and().sessionManagement()
		 	.maximumSessions(10)
		 	.expiredUrl("/login");
		super.configure(http);
		
	}
	

}
