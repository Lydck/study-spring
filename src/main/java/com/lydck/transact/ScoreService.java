package com.lydck.transact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("scoreService")
public class ScoreService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addScore(String userName, int toAdd) {
		String sql = "update user set score = score + ? where name = ?";
		if(userName.equals("lydck"))
			throw new RuntimeException("出异常啦");
		jdbcTemplate.update(sql, toAdd, userName);
	}
}
