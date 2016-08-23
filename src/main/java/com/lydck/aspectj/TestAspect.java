package com.lydck.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TestAspect {
	@AfterReturning("@annotation(com.lydck.aspectj.NeedTest) and execution(* greetTo(..))")
	public void needTestFun() {
		System.out.println("needTestFun() executed!");
	}
}
