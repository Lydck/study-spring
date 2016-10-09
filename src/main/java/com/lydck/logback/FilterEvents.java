package com.lydck.logback;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class FilterEvents {
	public static void main(String[] args) {
		Logger logger = (Logger) LoggerFactory.getLogger(FilterEvents.class);
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		try {
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(lc);
			lc.reset();
			configurator.doConfigure("C:\\workspace\\study-spring\\src\\main\\resources\\duplicateMessage.xml");
		} catch (JoranException je) {
			je.printStackTrace();
		}
		for (int i = 0; i < 15; i++) {
			logger.info("FilterEvents");
		}
	}
}
