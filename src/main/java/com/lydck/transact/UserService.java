package com.lydck.transact;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
	
	Logger loger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ScoreService scoreService;
	
	public void login(String userName) {
		loger.info("用户登录...");
		updateLastLoginTime(userName);
		loger.info("登录增加用户积分");
		scoreService.addScore(userName, 1);
	}

	public void updateLastLoginTime(String userName) {
		String sql = "update user set last_login_time = ? where name = ?";
		jdbcTemplate.update(sql, new Date(), userName);
	}
}
