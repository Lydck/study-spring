package com.lydck.transact;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("userThreadService")
public class UserThreadService {
	
	Log loger = LogFactoryImpl.getLog(UserThreadService.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ScoreService scoreService;
	
	public void loginThread(String userName) {
		loger.info("用户登录...");
		updateLastLoginTime(userName);
		loger.info("启动另外线程登录增加用户积分");
		new MyThread(scoreService, userName, 1).start();
	}

	public void updateLastLoginTime(String userName) {
		String sql = "update user set last_login_time = ? where name = ?";
		jdbcTemplate.update(sql, new Date(), userName);
	}
	
	private class MyThread extends Thread {
		private ScoreService scoreService;
		private String userName;
		private int toAdd;
		public MyThread(ScoreService scoreService, String userName, int toAdd) {
			this.scoreService = scoreService;
			this.userName = userName;
			this.toAdd = toAdd;
		}
		public void run() {
			loger.info(this + "调用scoreService增加用户积分");
			scoreService.addScore(userName, toAdd);
		}
	}
}
