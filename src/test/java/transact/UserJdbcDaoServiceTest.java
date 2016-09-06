package transact;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lydck.domain.User;
import com.lydck.transact.UserJdbcDaoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("jdbcWithoutTx.xml")
public class UserJdbcDaoServiceTest {
	
	@Autowired
	private UserJdbcDaoService userService;
	
	@Test
	public void addUser() {
		User user = new User();
		user.setName("lydck");
		user.setScore(99);
		userService.addUser(user);
	}
}
