package com.lydck.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Foo {
	final static Logger logger = LoggerFactory.getLogger(Foo.class);
	public void foo() {
		logger.debug("something foo...");
	}
}
