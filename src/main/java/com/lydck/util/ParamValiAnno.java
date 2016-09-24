package com.lydck.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 入参validate注解
 * @author Lydck
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ParamValiAnno {
	/**
	 * 是否可以为空
	 */
	boolean nullable() default true;
	
	/**
	 * 最大长度
	 */
	int maxLength() default 0;
	
	/**
	 * 最小长度
	 */
	int minLength() default 0;
	
	/**
	 * 常用正则表达式
	 */
	RegexType regexType() default RegexType.NONE;
	
	/**
	 * 自定义正则表达式
	 */
	String regex() default "";
	
	/**
	 * 参数或者字段描述
	 */
	String description() default "";
}
