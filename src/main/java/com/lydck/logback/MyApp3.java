package com.lydck.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyApp3 {
	final static Logger logger = LoggerFactory.getLogger(MyApp3.class);

	public static void main(String[] args) {
		logger.info("Entering application.");
		new Foo().foo();
		logger.debug("Exiting application");
	}
}
