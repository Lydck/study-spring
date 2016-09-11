package com.lydck.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Service;

import com.lydck.domain.UserImage;

/**
 * 用户image操作Dao
 * @author Lydck
 *
 */
@Service("userImageDao")
public class UserImageDao {
	private final static Logger logger = LoggerFactory.getLogger(UserImageDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private LobHandler lobHandler;
	
	public void addUserAvatar(UserImage userImage) {
		logger.info("添加用户头像，userId: " + userImage.getUserId() + " avatarLength:" + userImage.getAvatar().length / 1024 + " KB");
		jdbcTemplate.execute(UserDaoServiceSql.ADD_USER_AVATAR, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
			
			@Override
			protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
				ps.setInt(1, userImage.getUserId());
				lobCreator.setBlobAsBytes(ps, 2, userImage.getAvatar());
			}
		});
	}
}
