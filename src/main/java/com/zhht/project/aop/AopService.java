package com.zhht.project.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopService {
	@Pointcut("execution(* com.zhht.quartz.cluster..*.*(..))")
	public void pointCutMethod() {

	}

	@Before("pointCutMethod()")
	public void before(JoinPoint jpoint) {
		System.out.println(System.currentTimeMillis() + "==============before");
	}

	@After("pointCutMethod()")
	public void after(JoinPoint jpoint) {
		System.out.println(System.currentTimeMillis() + "==============after");
	}

	@AfterReturning("pointCutMethod()")
	public void afterReturning(JoinPoint jpoint) {
		System.out.println(System.currentTimeMillis() + "==============afterReturning");
	}

	@AfterThrowing("pointCutMethod()")
	public void afterThrowning(JoinPoint jpoint) {
		System.out.println("==============afterThrowning");
	}

	@Around("pointCutMethod()")
	public void around(ProceedingJoinPoint proceedJpoint) throws Throwable {
		System.out.println(System.currentTimeMillis() + "==============around begin");
		/*RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		System.out.println("请求地址：" + request.getRemoteHost());
		System.out.println("请求方法：" + (proceedJpoint.getTarget().getClass().getName() + "." + proceedJpoint.getSignature().getName() + "()"));
		for (int i = 0; i < proceedJpoint.getArgs().length; i++) {
			System.out.println("请求参数：" + proceedJpoint.getArgs()[i]);
		}

		Object result = proceedJpoint.proceed();
		if (result instanceof String) {
			System.out.println("返回值：" + result);
		}*/
		proceedJpoint.proceed();
		System.out.println("请求方法：" + (proceedJpoint.getTarget().getClass().getName() + "." + proceedJpoint.getSignature().getName() + "()"));
		System.out.println(System.currentTimeMillis() + "==============around end");
	}

}
