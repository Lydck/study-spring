package com.lydck.orm;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lydck.domain.User;

@Service("userBatisDaoService")
public class UserBatisDaoService {
	
	@Autowired
	private SqlSessionTemplate batisTemplate;
	
	public User getUser(User user) {
		return batisTemplate.selectOne(UserBatisDaoService.class.getName() + ".getUser", user);
	}
}
