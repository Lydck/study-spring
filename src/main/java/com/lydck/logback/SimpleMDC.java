package com.lydck.logback;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class SimpleMDC {

	public static void main(String[] args) {
		MDC.put("first", "Dorothy");
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		try {
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(lc);
			lc.reset();
			configurator.doConfigure("C:\\workspace\\study-spring\\src\\main\\resources\\simpleMDC.xml");
		} catch (JoranException je) {
			je.printStackTrace();
		}
		Logger logger = (Logger) LoggerFactory.getLogger(SimpleMDC.class);
		MDC.put("last", "Parker");
		logger.info("Check enclosed.");
		logger.debug("The most beautiful two words in English.");
		MDC.put("first", "Richard");
		MDC.put("last", "Nixon");
		logger.info("I am not a crook.");
		logger.info("Attributed to the former US president. 17 Nov 1973.");
	}

}
