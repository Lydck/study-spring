package com.lydck.quarzt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

public class CalendarExample {
	public static void main(String[] args) throws Exception {
		//调度器
		Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
		
		//法定假日都是以年为单位的
		AnnualCalendar holidays = new AnnualCalendar();
		
		//五一劳动节
		GregorianCalendar laborDay = new GregorianCalendar();
		laborDay.add(Calendar.MONTH, 5);
		laborDay.add(Calendar.DAY_OF_MONTH, 1);
		
		//国庆节
		GregorianCalendar nationalDay = new GregorianCalendar();
		nationalDay.add(Calendar.MONTH, 10);
		nationalDay.add(Calendar.DAY_OF_MONTH, 1);
		
		ArrayList<Calendar> calendars = new ArrayList<Calendar>();
		calendars.add(laborDay);
		calendars.add(nationalDay);
		
		//排除两个特定的节假日
		holidays.setDaysExcluded(calendars);
		defaultScheduler.addCalendar("holidays", holidays, false, false);
		
		//具体任务
		JobBuilder newJob = JobBuilder.newJob(SimpleJob.class);
		JobDetail job = newJob.withIdentity("job1", "lydck").build();
		
		//触发器
		Date dateOf = DateBuilder.dateOf(23, 59, 50, 30, 4);
		CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
		TriggerBuilder<CronTrigger> withSchedule = TriggerBuilder.newTrigger().withIdentity("trigger1", "lydck").startNow().withSchedule(cronSchedule);
		CronTrigger trigger = withSchedule.withDescription("每5秒钟执行一次").startAt(dateOf).build();
		
		defaultScheduler.scheduleJob(job, trigger);
		defaultScheduler.start();//调度启动
		
		
	}
}
