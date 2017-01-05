package com.lydck.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {

	RealService realService;
	
	public ProxyHandler(RealService realService) {
		super();
		this.realService = realService;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("代理service之前");
		return method.invoke(realService, args);
	}

}
