package com.lydck.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("增强：" + target.getClass().getName() + "." + method.getName());
		String clientName = (String) args[0];
		System.out.println("How are you! Mr." + clientName);
	}

}
