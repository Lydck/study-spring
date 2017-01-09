package com.lydck.event;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("prototype")
public class DemoListener implements ApplicationListener<DemoEvent> {
	private static Logger logger = LoggerFactory.getLogger(DemoListener.class);

	@Override
	public void onApplicationEvent(DemoEvent event) {
		String msg = event.getMsg();
		logger.info("我" + this + "收到了消息：" + msg);
		try {
			TimeUnit.SECONDS.sleep(10);
			logger.info("收到了消息我休息了10s");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
