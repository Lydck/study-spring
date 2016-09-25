package com.lydck.quarzt;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements Job {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(context.getTrigger().getDescription() + " triggered time is:" + dateFormat.format(new Date()));
	}

}
