package com.lydck.quarzt;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

public class JDBCJobStoreRunner {
	public static void main(String[] args) throws Exception {

		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		GroupMatcher<JobKey> anyGroup = GroupMatcher.anyGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(anyGroup);
		JobKey j = null;
		for (JobKey jobKey : jobKeys) {
			// scheduler.resumeJob(jobKey);
			if (jobKey.getName().equals("pickNewsJob1")) {
				scheduler.pauseJob(jobKey);
				j = jobKey;
			}
		}
		scheduler.start();
		TimeUnit.SECONDS.sleep(10);
		System.out.println("pickNewsJob1 休息好了开始执行");
		scheduler.resumeJob(j);

		/*
		 * // 调度器 Scheduler scheduler =
		 * StdSchedulerFactory.getDefaultScheduler();
		 * 
		 * // 获取调度器中所有的触发器组 List<String> triggerGroups =
		 * scheduler.getTriggerGroupNames();
		 * 
		 * // 重新恢复在lydck组中，名为pickNewsJobTrigger1触发器的运行 for (int i = 0; i <
		 * triggerGroups.size(); i++) {//
		 * 这里使用了两次遍历，针对每一组触发器里的每一个触发器名，和每一个触发组名进行逐次匹配 if
		 * ("lydck".equals(triggerGroups.get(i))) { GroupMatcher<TriggerKey>
		 * triggerKey = GroupMatcher.triggerGroupEquals(triggerGroups.get(i));
		 * Iterator<TriggerKey> iterator =
		 * scheduler.getTriggerKeys(triggerKey).iterator(); while
		 * (iterator.hasNext()) { TriggerKey next = iterator.next(); String name
		 * = next.getName(); if (name != null &&
		 * name.equals("pickNewsJobTrigger1")) {// 根据名称判断
		 * scheduler.resumeTrigger(next);
		 * scheduler.resumeJob(JobKey.jobKey("pickNewsJob1", "lydck")); } } } }
		 */
	}
}
