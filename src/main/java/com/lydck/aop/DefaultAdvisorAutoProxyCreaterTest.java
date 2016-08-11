package com.lydck.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DefaultAdvisorAutoProxyCreaterTest {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"DefaultAdvisorAutoProxy.xml"}); 
		Waiter waiter = (Waiter) context.getBean("waiter");
		Seller seller = (Seller) context.getBean("seller");
		waiter.serveTo("lydck");
		waiter.greetTo("lydck");
		seller.greetTo("lydck");
	}
}
