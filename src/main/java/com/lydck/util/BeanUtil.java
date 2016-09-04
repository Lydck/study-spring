package com.lydck.util;

import java.util.HashMap;

import net.sf.cglib.beans.BeanCopier;

public class BeanUtil {
	private static HashMap<String, BeanCopier> copier = new HashMap<String, BeanCopier>();

	public static void copyBean(Object source, Object target) {
		String key = source.getClass().getName() + target.getClass().getName();
		BeanCopier copyer = copier.get(key);
		if (copyer == null) {
			copyer = BeanCopier.create(source.getClass(), target.getClass(), false);
			copier.put(key, copyer);
		}
		copyer.copy(source, target, null);
	}

	public static void main(String[] args) {
		A a = new A();
		a.setPropertyA("propertyA");
		a.setPropertyB("propertyB");
		A.propertyC = "propertyC";
		a.setWorkDay(Weekday.FRIDAY);
		B b = new B();
		copyBean(a, b);
		System.out.println(b.propertyA);
		System.out.println(b.getPropertyB());
		System.out.println(B.propertyC);
		System.out.println(b.getWorkDay());
	}
}
