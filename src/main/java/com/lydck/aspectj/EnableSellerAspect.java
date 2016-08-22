package com.lydck.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

import com.lydck.aop.Seller;

@Aspect
public class EnableSellerAspect {
	@DeclareParents(value="com.lydck.Waiter", defaultImpl=Seller.class)
	public Seller seller;
}
