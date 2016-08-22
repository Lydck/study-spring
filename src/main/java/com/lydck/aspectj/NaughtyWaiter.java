package com.lydck.aspectj;

public class NaughtyWaiter implements Waiter {

	@Override
	@NeedTest(true)
	public void greetTo(String clientName) {
		System.out.println("Naughty waiter greeTo " + clientName);
	}

	@Override
	public void serviceTo(String clientName) {
		System.out.println("Naughty waiter serviceTo " + clientName);
	}
	public void joke(String clientName, int times) {
		System.out.println("Naughty waiter joke to " + clientName + " times.");
	}
}
