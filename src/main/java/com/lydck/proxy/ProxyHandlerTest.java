package com.lydck.proxy;

import java.lang.reflect.Proxy;

public class ProxyHandlerTest {
	public static void main(String[] args) {
		RealService service = new RealServiceImpl();
		ProxyHandler handler = new ProxyHandler(service);
		Class<? extends RealService> serviceClass = service.getClass();
		RealService proxyService = (RealService) Proxy.newProxyInstance(
				serviceClass.getClassLoader(), serviceClass.getInterfaces(), handler);
		proxyService.service();
		
	}
}
