package com.lydck.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EventTest {
	private static Logger logger = LoggerFactory.getLogger(EventTest.class);
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
		DemoPublisher publisher = context.getBean(DemoPublisher.class);
		publisher.publish("这是一条bean发出的消息");
		
		logger.info("消息发送成功");
		context.close();
	}

}
