package aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lydck.aspectj.Seller;
import com.lydck.aspectj.Waiter;

public class SchemaAspectTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("SchemaAspect.xml");
		Waiter naiveWaiter = (Waiter) context.getBean("naiveWaiter");
		Waiter naughtyWaiter = (Waiter) context.getBean("naughtyWaiter");
		naiveWaiter.greetTo("lydck");
		naughtyWaiter.greetTo("lydck");
		((Seller) naiveWaiter).sell("Bean","John");
	}
}
