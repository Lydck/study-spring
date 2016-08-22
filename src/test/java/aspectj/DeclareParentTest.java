package aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lydck.aop.Waiter;

public class DeclareParentTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("DeclareParent.xml");
		Waiter waiter = (Waiter) context.getBean("waiter");
		waiter.greetTo("John");
		
	}

}
