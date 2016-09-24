package com.lydck.orm;

import org.springframework.stereotype.Service;

import com.lydck.domain.User;

@Service("userDao")
public class UserDao extends BaseDao<User> {
	
}
