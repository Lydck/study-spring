package aspectj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lydck.aop.Waiter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("AutoAspectJBean.xml")
public class AtuoAspectJTest {
	
	@Test
	public void AutoAspectJ() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("AutoAspectJBean.xml");
		Waiter waiter = (Waiter) context.getBean("waiter");
		waiter.greetTo("Lydck");
		waiter.serveTo("Lydck");
	}
}
