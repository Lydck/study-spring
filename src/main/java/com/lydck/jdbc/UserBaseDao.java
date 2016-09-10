package com.lydck.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.lydck.domain.User;

@Service("userBaseDao")
public class UserBaseDao {
	private static final Logger logger = LoggerFactory.getLogger(UserBaseDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
}
