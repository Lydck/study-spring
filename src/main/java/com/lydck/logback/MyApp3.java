package com.lydck.logback;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyApp3 {
	final static Logger logger = LoggerFactory.getLogger(MyApp3.class);

	public static void main(String[] args) {
		logger.info("Entering application.中文");
		new Foo().foo();
		logger.debug("Exiting application");
		Object[] arg = new Object[] {"sldf", 2, new Date()};
		logger.info("参数数组：{}", arg);
	}
}
