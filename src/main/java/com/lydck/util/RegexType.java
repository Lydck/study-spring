package com.lydck.util;

/**
 * 常用正则表达式枚举
 * @author Lydck
 *
 */
public enum RegexType {
	/**
	 * 除字母数字外的特殊字符
	 */
	NO_SPECIAL_CHAR("((?=[\\x21-\\x7e]+)[^A-Za-z0-9])"),
	
	/**
	 * 邮箱地址
	 */
	EMAIL("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$"),
	
	/**
	 * 数字
	 */
	NUMBER("\\d+"),
	
	/**
	 * 无类型
	 */
	NONE(null);
	
	String regex;

	private RegexType(String regex) {
		this.regex = regex;
	}

}
