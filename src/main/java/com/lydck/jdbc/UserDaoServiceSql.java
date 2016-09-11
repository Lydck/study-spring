package com.lydck.jdbc;

public class UserDaoServiceSql {
	/** 添加用户 */
	public static final String ADD_USER = "insert into user(name, score, last_login_time) values(?, ?, ?)";
	
	/** 根据userID查询user*/
	public static final String QUERY_USER_BY_ID = "select id, name, score, last_login_time from user where id = ?";
	
	/** 根据name模糊查询user*/
	public static final String QUERY_USERS_BY_NAME = "select id, name, score, last_login_time from user where name like ?";
	
	/** 根据ID查询user列表*/
	public static final String QUERY_USERS_BY_ID = "select id, name, score, last_login_time from user where id >= ? and id < ?";
	
	/** 查询user总数*/
	public static final String QUERY_USER_COUNT = "select count(1) from user";
	
	/** 调用存储过程查询相同名字的user数*/
	public static final String CALL_PRO_NAME_SUM = "{call p_get_name_sum(?,?)}";
	
	/** 添加user头像*/
	public static final String ADD_USER_AVATAR = "insert into user_image(user_id, avatar) values(?, ?)";
	
	/** 根据userId获取用户头像*/
	public static final String GET_USER_AVATAR = "select user_id, avatar from user_image where user_id = ?";
}
