package aspectj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.lydck.aop.Waiter;

@RunWith(JUnit4.class)
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
