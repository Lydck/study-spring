package com.lydck.aspectj;

public class SmartSeller implements Seller {

	@Override
	public int sell(String goods, String clientName) {
		System.out.println("Smart seller selling " + goods + " to " + clientName);
		return 0;
	}
	
	protected int showGoods(String goods) {
		System.out.println("Smart seller show " + goods);
		return 0;
	}

}
