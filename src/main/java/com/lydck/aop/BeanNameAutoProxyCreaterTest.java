package com.lydck.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanNameAutoProxyCreaterTest {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"BeanNameAutoProxy.xml"}); 
		Waiter waiter = (Waiter) context.getBean("waiter");
		Seller seller = (Seller) context.getBean("seller");
		waiter.greetTo("lydck");
		seller.greetTo("lydck");
	}
}
