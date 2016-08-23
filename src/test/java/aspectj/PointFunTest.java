package aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lydck.aspectj.Seller;
import com.lydck.aspectj.Waiter;

public class PointFunTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("PointFunTest.xml");
		Waiter naiveWaiter = (Waiter) context.getBean("naiveWaiter");
		Seller smartSeller = (Seller) context.getBean("smartSeller");
		naiveWaiter.greetTo("lydck");
		naiveWaiter.serviceTo("lydck");
		((Seller)naiveWaiter).sell("lydck", "John");
		smartSeller.sell("book", "Tom");
	}
}
