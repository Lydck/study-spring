package aspectj;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import com.lydck.aop.Waiter;
import com.lydck.aspectj.PreGreetingAspect;

public class AspectJProxyTest {
	public static void main(String[] args) {
		Waiter waiter = new Waiter();
		AspectJProxyFactory factory = new AspectJProxyFactory();
		// 设置目标对象
		factory.setTarget(waiter);
		//添加切面
		factory.addAspect(PreGreetingAspect.class);
		//生成代理
		Waiter waiterProxy = factory.getProxy();
		waiterProxy.greetTo("Lydck");
		waiterProxy.serveTo("Lydck");
	}
}
