package com.lydck.quarzt;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class PickNewsJob implements Job {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd : hh:mm:ss");
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("在" + dateFormat.format(new Date()) + "拔取新闻");
	}
	
	public static void main(String[] args) throws Exception {
		
		//Schedule
		SimpleScheduleBuilder schedule = SimpleScheduleBuilder.repeatSecondlyForTotalCount(100, 5);
		
		//JobDetail
		JobDetail jobDetail = JobBuilder.newJob(PickNewsJob.class).withIdentity("pickNewsJob1", "lydck").build();
		
		//Trigger
		SimpleTrigger simpleTrigger = TriggerBuilder
				.newTrigger()
				.withIdentity("pickNewsJobTrigger1", "lydck")
				.withSchedule(schedule)
				.startNow()
				.build();
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, simpleTrigger);
        scheduler.start();
	}
}
