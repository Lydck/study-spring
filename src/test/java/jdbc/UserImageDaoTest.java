package jdbc;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.FileCopyUtils;

import com.lydck.domain.UserImage;
import com.lydck.jdbc.UserImageDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:jdbc/jdbcTemplate.xml")
public class UserImageDaoTest {
	
	@Autowired
	private UserImageDao userImageDao;
	
	@Test
	public void addUserAvatar() throws IOException {
		UserImage userImage = new UserImage();
		userImage.setUserId(7);
		
		ClassPathResource cpr = new ClassPathResource("jdbc/avatars/qiaofeng.jpg");
		byte[] avatar = FileCopyUtils.copyToByteArray(cpr.getFile());
		userImage.setAvatar(avatar);
		userImageDao.addUserAvatar(userImage);
	}
	
	@Test
	public void getUserAvatar() throws IOException {
		UserImage userAvatar = userImageDao.getUserAvatar(7);
		String projectDir = System.getProperties().getProperty("user.dir");
		File avatar = new File(projectDir + "/avators/", "qiaofeng.jpg");
		File fileParent = avatar.getParentFile();  
		if(!fileParent.exists()){  
		    fileParent.mkdirs();  
		}  
		avatar.createNewFile();
		FileCopyUtils.copy(userAvatar.getAvatar(), avatar);
	}
}
