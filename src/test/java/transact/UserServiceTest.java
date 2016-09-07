package transact;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lydck.transact.UserService;
import com.lydck.transact.UserThreadService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("jdbcWithTx.xml")
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserThreadService userThreadService;
	
	@Test
	public void login() {
		userService.login("lydck");
	}
	
	@Test
	public void loginThread() {
		userThreadService.loginThread("lydck");
	}
}
