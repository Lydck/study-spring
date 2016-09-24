package orm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lydck.domain.User;
import com.lydck.orm.UserBatisDaoService;
import com.lydck.orm.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:orm/mybatisTemplate.xml")
public class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void get() {
		User parameter = new User();
		parameter.setId(7);
		parameter.setScore(99);
		User user = userDao.get(UserBatisDaoService.class.getName() + ".getUser", parameter);
		System.out.println(user);
	}
}
