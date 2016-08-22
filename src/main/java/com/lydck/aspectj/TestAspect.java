package com.lydck.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TestAspect {
	@AfterReturning("this(com.lydck.aspectj.Seller)")
	public void thisTest() {
		System.out.println("thisTest() executed!");
	}
}
