package com.lydck.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

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
	
	public UserImage getUserAvatar(final int userId) {
		logger.info("根据user_id获取user头像，userId：" + userId);
		UserImage userImage = new UserImage();
		jdbcTemplate.query(UserDaoServiceSql.GET_USER_AVATAR, new Object[] {userId}, new AbstractLobStreamingResultSetExtractor<UserImage>() {

			@Override
			protected void streamData(ResultSet rs) throws SQLException, IOException, DataAccessException {
				InputStream blobAsBinaryStream = lobHandler.getBlobAsBinaryStream(rs, 2);
				if(blobAsBinaryStream != null) {
					userImage.setUserId(rs.getInt(1));
					byte[] avatar = FileCopyUtils.copyToByteArray(blobAsBinaryStream);
					userImage.setAvatar(avatar);
				}
			}
			
		});
		return userImage;
	}
}
