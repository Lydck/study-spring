package com.lydck.quarzt;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerRunner {
	public static void main(String[] args) throws Exception {
		//具体任务
		JobBuilder newJob = JobBuilder.newJob(SimpleJob.class);
		JobDetail job = newJob.withIdentity("job1", "lydck").build();
		
		//触发器
		CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
		TriggerBuilder<CronTrigger> withSchedule = TriggerBuilder.newTrigger().withIdentity("trigger1", "lydck").startNow().withSchedule(cronSchedule);
		CronTrigger trigger = withSchedule.withDescription("每5秒钟执行一次").build();
		
		//调度器
		Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
		defaultScheduler.scheduleJob(job, trigger);
		defaultScheduler.start();//调度启动
	}
}
