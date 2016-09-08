package com.lydck.logback;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.util.StatusPrinter;

public class HelloLogBack {
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger("com.lydck.logback.HelloLogBack");
		logger.debug("HelloLogBack!");
//		ILoggerFactory iLoggerFactory = LoggerFactory.getILoggerFactory();
//		StatusPrinter.print((Context) (iLoggerFactory));
	}
}
