package com.lydck.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class TestAspect {
	@AfterReturning("@annotation(com.lydck.aspectj.NeedTest) and execution(* greetTo(..))")
	public void needTestFun() {
		System.out.println("needTestFun() executed!");
	}
	
	@After("within(com.lydck.aspectj.*) && execution(* greetTo(..))")
	public void greetToFun() {
		System.out.println("greetToFun() executed!");
	}
	
	@Before("!target(com.lydck.aspectj.NaiveWaiter) && execution(* serviceTo(..))")
	public void notServeinNaiveWaiter() {
		System.out.println("notServeinNaiveWaiter() executed!");
	}
	
	@AfterReturning("target(com.lydck.aspectj.Waiter) || target(com.lydck.aspectj.Seller)")
	public void waiterOrSeller() {
		System.out.println("waiterOrSeller() executed!");
	}
	
	@Around("execution(* greetTo(..))")
	public void joinPointAccess(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("------joinPointAccess-------");
		System.out.println("arg[0]: " + pjp.getArgs()[0]);
		System.out.println("signature: " + pjp.getSignature().toString());
		pjp.proceed();
		System.out.println("------joinPointAccess-------");
	}
	
	@After("target(com.lydck.aspectj.Seller) && args(goods, clientName)")
	public void bindJoinPointParams(String goods, String clientName) {
		System.out.println("---------bindJoinPointParams--------");
		System.out.println("goods: " + goods);
		System.out.println("clientName: " + clientName);
		System.out.println("---------bindJoinPointParams--------");
	}
	
	@Before("this(seller)")
	public void bindProxyObj(Seller seller) {
		System.out.println("----------bindProxyObj----------");
		System.out.println(seller.getClass().getName());
		System.out.println("----------bindPrxyObj-----------");
	}
	
	@AfterReturning(value="target(com.lydck.aspectj.Seller)", returning="retVal")
	public void bindreturnValue(int retVal) {
		System.out.println("----------bindReturnValue-----------");
		System.out.println("return value:" + retVal);
		System.out.println("----------bindReturnValue------------");
	}
}
