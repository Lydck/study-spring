package com.lydck.logback;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.joran.spi.JoranException;

public class PatternSample {
	public static void main(String[] args) {
		System.setProperty("root", "logger.contains(\"chapters.layouts\") && message.contains(\"who calls thee\")");
		Logger logger = (Logger) LoggerFactory.getLogger("root");
		LoggerContext context = logger.getLoggerContext();

		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(context);
		context.reset();

		try {
			configurator.doConfigure("C:\\workspace\\study-spring\\src\\main\\resources\\callerEvaluatorConfig.xml");
		} catch (JoranException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 5; i++) {
			if (i == 3) {
				logger.debug("who calls thee?");
			} else {
				logger.debug("I know me " + i);
			}
		}
		PatternLayoutEncoder encoder = new PatternLayoutEncoder();
		encoder.setContext(context);
		encoder.setPattern("%c %d %F %M %L %cn %-5level [%thread]: %message%n");
		encoder.start();

		ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<ILoggingEvent>();
		appender.setContext(context);
		appender.setEncoder(encoder);
		appender.start();

		logger.addAppender(appender);
		logger.debug("Message 1");
		logger.warn("Message 2");
	}
}
