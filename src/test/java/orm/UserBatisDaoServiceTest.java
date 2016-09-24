package orm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lydck.domain.User;
import com.lydck.orm.UserBatisDaoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:orm/mybatisTemplate.xml")
public class UserBatisDaoServiceTest {
	
	@Autowired
	private UserBatisDaoService userDao;
	
	@Test
	public void getUser() {
		User user = new User();
		user.setId(7);
		user.setScore(99);
		user = userDao.getUser(user);
		System.out.println(user);
	}
}
