package com.lydck.quarzt;

import java.util.List;

import org.quartz.Scheduler;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class JDBCJobStoreRunner {
	public static void main(String[] args) throws Exception {
		// 调度器
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		List<String> triggerGrops = scheduler.getTriggerGroupNames();
		int size = triggerGrops.size();
		for (int i = 0; i < size; i++) {
			scheduler.resumeTrigger(new TriggerKey("job1", triggerGrops.get(i)));
		}

	}
}
