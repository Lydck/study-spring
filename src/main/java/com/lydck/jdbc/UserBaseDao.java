package com.lydck.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.stereotype.Service;

import com.lydck.domain.User;

@Service("userBaseDao")
public class UserBaseDao {
	private static final Logger logger = LoggerFactory.getLogger(UserBaseDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private MySQLMaxValueIncrementer keyGenerator;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public boolean addUser(User user) {
		logger.info("创建user，入参：" + user);
		return jdbcTemplate.update(UserDaoServiceSql.ADD_USER, user.getName(), user.getScore(), user.getLastLoginTime()) == 1;
	}
	
	/**创建user,返回主键
	 * @param user
	 * @return
	 */
	public int addUserRetKey(User user) {
		logger.info("创建user，返回主键，入参：" + user);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(UserDaoServiceSql.ADD_USER);
				ps.setString(1, user.getName());
				ps.setInt(2, user.getScore());
				ps.setTimestamp(3, new Timestamp(user.getLastLoginTime().getTime()));
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public boolean addUsers(List<User> users) {
		logger.info("批量创建user，入参：" + users);
		jdbcTemplate.batchUpdate(UserDaoServiceSql.ADD_USER, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				User user = users.get(i);
				ps.setString(1, user.getName());
				ps.setInt(2, user.getScore());
				ps.setTimestamp(3, user.getLastLoginTime() == null ? null : new Timestamp(user.getLastLoginTime().getTime()));
			}
			
			@Override
			public int getBatchSize() {
				return users.size();
			}
		});
		return true;
	}
	
	public User queryUserById(int id) {
		logger.info("根据用户ID查询user，id: " + id);
		
		User user = new User();
		jdbcTemplate.query(UserDaoServiceSql.QUERY_USER_BY_ID, new Object[] {id}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setScore(rs.getInt("score"));
				user.setLastLoginTime(rs.getTimestamp("last_login_time"));
			}
		});
		return user.getId() == 0 ? null : user;
	}
	
	public List<User> queryUsersByName(String name) {
		logger.info("根据name模糊查询user，name:" + name);
		List<User> users = new ArrayList<>();
		jdbcTemplate.query(UserDaoServiceSql.QUERY_USERS_BY_NAME, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, "%" + name + "%");
			}
		}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setScore(rs.getInt("score"));
				user.setLastLoginTime(rs.getTimestamp("last_login_time"));
				users.add(user);
			}
		});
		return users.size() == 0 ? null : users;
	}
	public List<User> getUsers(int fromId, int total){
		logger.info("根据ID查询user， name:" + fromId + " total:" + total);
		return jdbcTemplate.query(UserDaoServiceSql.QUERY_USERS_BY_ID, new Object[] {fromId, fromId + total}, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setScore(rs.getInt("score"));
				user.setLastLoginTime(rs.getTimestamp("last_login_time"));
				return user;
			}
		});
	}
	
	public int getUserCount() {
		logger.info("获取user总数");
		return jdbcTemplate.queryForObject(UserDaoServiceSql.QUERY_USER_COUNT, Integer.class);
	}
	
	public int getNameSum(final String name) {
		logger.info("调用存储过程查询相同名字的user数");
		
		return jdbcTemplate.execute(UserDaoServiceSql.CALL_PRO_NAME_SUM, new CallableStatementCallback<Integer>() {

			@Override
			public Integer doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.setString(1, name);
				cs.registerOutParameter(2, Types.INTEGER);
				cs.execute();
				return cs.getInt(2);
			}
		});
	}
	
	public int getUserId() {
		logger.info("获取user的自增键");
		return keyGenerator.nextIntValue();
	}
	
	public void logon(final int userId) {
		logger.info("用户登录，更新最后登录时间");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("lastLoginTime", new Date());
		namedParameterJdbcTemplate.update(UserDaoServiceSql.USER_LOGON, paramMap);
	}
}
