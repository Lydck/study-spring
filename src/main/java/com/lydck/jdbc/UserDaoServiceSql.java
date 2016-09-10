package com.lydck.jdbc;

public class UserDaoServiceSql {
	public static final String ADD_USER = "insert into user(name, score, last_login_time) values(?, ?, ?)";
}
