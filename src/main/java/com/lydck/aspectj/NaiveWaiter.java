package com.lydck.aspectj;

public class NaiveWaiter implements Waiter{

	@Override
	public void greetTo(String clientName) {
		System.out.println("Naive waiter greetTo " + clientName);
	}

	@Override
	public void serviceTo(String clientName) {
		System.out.println("Naive waitet serviceTo " + clientName);
	}
	
	public void smile(String clientName, int times) {
		System.out.println("Naive waiter smile to " + clientName + times + "times.");
	}
	
}
