package jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lydck.domain.User;
import com.lydck.jdbc.UserBaseDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:jdbc/jdbcTemplate.xml")
public class UserBaseDaoTest {

	@Autowired
	private UserBaseDao userDao;

	@Test
	public void addUser() {
		User user = new User();
		user.setName("Jack");
		user.setScore(88);
		user.setLastLoginTime(new Date());
		boolean result = userDao.addUser(user);
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void addUserRetKey() {
		User user = new User();
		user.setName("乔峰");
		user.setScore(99);
		user.setLastLoginTime(new Date());
		int userKey = userDao.addUserRetKey(user);
		System.out.println(userKey);
	}
	
	@Test
	public void addUsers() {
		List<User> users = new ArrayList<>();
		User user1 = new User(), user2 = new User();
		user1.setName("萧远山");
		user1.setScore(90);
		user1.setLastLoginTime(new Date());
		
		user2.setName("虚竹");
		user2.setScore(80);
		
		users.add(user1);
		users.add(user2);
		boolean addUsers = userDao.addUsers(users);
		System.out.println(addUsers);
	}
	
	@Test
	public void queryUserById() {
		User user = userDao.queryUserById(0);
		System.out.println(user);
	}
	
	@Test
	public void queryUsersByName() {
		List<User> users = userDao.queryUsersByName("乔峰");
		System.out.println(users);
	}
	
	@Test
	public void getUsers() {
		List<User> users = userDao.getUsers(1, 10);
		System.out.println(users);
	}
	
	@Test
	public void getUserCount() {
		int userCount = userDao.getUserCount();
		System.out.println(userCount);
	}
	
	@Test
	public void getNameSum() {
		int nameSum = userDao.getNameSum("乔峰");
		System.out.println(nameSum);
	}
}
