package orm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lydck.domain.User;
import com.lydck.orm.UserHiberDaoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:orm/hibernateTemplate.xml")
public class UserHiberDaoServiceTest {
	
	@Autowired
	private UserHiberDaoService userDao;
	
	@Test
	public void addUser() {
		User user = new User();
		user.setName("乔峰");
		user.setScore(99);
		userDao.addUser(user);
	}
}
