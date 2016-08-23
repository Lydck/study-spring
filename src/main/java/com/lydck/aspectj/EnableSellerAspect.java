package com.lydck.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class EnableSellerAspect {
	@DeclareParents(value = "com.lydck.aspectj.NaiveWaiter", defaultImpl = SmartSeller.class)
	public static Seller seller;
}
