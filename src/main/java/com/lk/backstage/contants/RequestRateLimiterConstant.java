package com.lk.backstage.contants;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;

import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import es.moki.ratelimitj.inmemory.request.InMemorySlidingWindowRequestRateLimiter;

/**  
 * Title: RequestRateLimiterConstant
 * Description: 
 * @author linkan  
 * @date 2019年1月17日  
 */
public class RequestRateLimiterConstant {
	
	private final static Set<RequestLimitRule> rules = Collections.singleton(RequestLimitRule.of(Duration.ofMinutes(60),4)); 
	public final static RequestRateLimiter LOGIN_RATE_LIMITER = new InMemorySlidingWindowRequestRateLimiter(rules); 
}
