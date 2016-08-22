package com.lydck.util;

public class B {
	public String propertyA;
	private String propertyB;
	public static String propertyC;
	public String getPropertyA() {
		return propertyA;
	}

	public void setPropertyA(String propertyA) {
		this.propertyA = propertyA;
	}
	public String getPropertyB() {
		return propertyB;
	}
	public void setPropertyB(String propertyB) {
		this.propertyB = propertyB;
	}
	public static String getPropertyC() {
		return propertyC;
	}
	public static void setPropertyC(String propertyC) {
		B.propertyC = propertyC;
	}
}
