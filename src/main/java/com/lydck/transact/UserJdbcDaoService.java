package com.lydck.transact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lydck.domain.User;

@Service("userService")
public class UserJdbcDaoService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean addUser(User user) {
		String sql = "insert into user(name, score) values (?,?)";
		return jdbcTemplate.update(sql, user.getName(), user.getScore()) == 1;
	}
}
