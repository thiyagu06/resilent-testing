package com.thiyagu.disruptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DisruptorAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(DisruptorAspect.class);
	
	@Autowired
	private DisruptorService disruptorService;

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void classAnnotatedWithControllerPointcut() {
	}
	
    @Pointcut("execution(* *.*(..))")
    public void allPublicMethodPointcut() {
    }
    

	@Around("classAnnotatedWithControllerPointcut() && allPublicMethodPointcut()")
	public Object intercept(ProceedingJoinPoint pjp) throws Throwable {
		LOGGER.debug(LOGGER.isDebugEnabled() ? "RestController class and public method detected: " + pjp.getSignature()
				: null);
		
		disruptorService.disrupt();
		
		return pjp.proceed();
	}
}
