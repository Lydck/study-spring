package com.lydck.quarzt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class SimpleTimerTask extends TimerTask {
	private int count = 0;
	SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss.sss");
	@Override
	public void run() {
		System.out.println("开始任务");
		System.out.println("本次任务执行点为：" + dateFormat.format(new Date()));
		if(++count > 10)
			cancel();
	}
}
