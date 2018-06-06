package com.thiyagu.disruptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The aspect implementation to disrupt the execution of the methods annotated
 * with @restController.
 * 
 * @author thiyagu
 *
 */
@Aspect
@Component
public class DisruptorAspect {

	/**
	 * The logger instance
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DisruptorAspect.class);

	/**
	 * The disruptor service instance
	 */
	@Autowired
	private DisruptorService disruptorService;

	/**
	 * The pointcut for classes annotated with @restController.
	 */
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void classAnnotatedWithControllerPointcut() {
	}

	/**
	 * The pointcut for all method execution
	 */
	@Pointcut("execution(* *.*(..))")
	public void allPublicMethodPointcut() {
	}

	/**
	 * The advice to call the disrupt service
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("classAnnotatedWithControllerPointcut() && allPublicMethodPointcut()")
	public Object intercept(ProceedingJoinPoint pjp) throws Throwable {
		LOGGER.debug(LOGGER.isDebugEnabled() ? "RestController class and public method detected: " + pjp.getSignature()
				: null);

		disruptorService.disrupt();

		return pjp.proceed();
	}
}
