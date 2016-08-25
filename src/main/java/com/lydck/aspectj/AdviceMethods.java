package com.lydck.aspectj;

import org.aspectj.lang.JoinPoint;

/**增强方法类
 * @author Lydck
 *
 */
public class AdviceMethods {
	public void preGreeting() {
		System.out.println("How are You!");
	}
	
	public void bindParams(JoinPoint pjp) {
		System.out.println("----------bindParams()-----------");
		Object[] args = pjp.getArgs();
		for(Object param : args) {
			System.out.println(param);
		}
		System.out.println("----------bindParams()-----------");
	}
}
