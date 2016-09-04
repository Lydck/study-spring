package com.lydck.util;

public class A {
	public String propertyA;
	private String propertyB;
	public static String propertyC;
	public Weekday workDay;
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
		A.propertyC = propertyC;
	}
	public Weekday getWorkDay() {
		return workDay;
	}
	public void setWorkDay(Weekday workDay) {
		this.workDay = workDay;
	}

}
