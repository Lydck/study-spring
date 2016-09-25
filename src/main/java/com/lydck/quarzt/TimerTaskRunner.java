package com.lydck.quarzt;

import java.util.Date;
import java.util.Timer;

public class TimerTaskRunner {
	public static void main(String[] args) {
		Timer timer = new Timer();
		SimpleTimerTask simpleTimerTask = new SimpleTimerTask();
		timer.scheduleAtFixedRate(simpleTimerTask, new Date(), 5000);
	}
}
