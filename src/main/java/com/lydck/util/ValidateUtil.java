package com.lydck.util;

import java.lang.reflect.Field;

/**
 * 参数校验注解解析工具类
 * 
 * @author Lydck
 *
 */
public class ValidateUtil {
	public static String validate(Object param) {
		if(param == null) {
			return "入参不能为空";
		}
		String result = null;
		Class<? extends Object> clazz = param.getClass();
		// 获取所有field
		Field[] fields = clazz.getDeclaredFields();
		// 遍历field并校验参数注解信息
		for (Field field : fields) {
			// 属性是否可访问
			boolean accessible = field.isAccessible();
			if (!accessible) {
				field.setAccessible(true);
			}
			result = validate(field, param);
			if (!accessible) {
				field.setAccessible(false);
			}
			if (result != null) {
				return result;
			}
		}
		return result;
	}

	private static String validate(Field field, Object param) {
		// 获取field的注解信息
		ParamValiAnno paramAnno = field.getAnnotation(ParamValiAnno.class);
		if (paramAnno == null) {
			return null;
		}
		// 获取field的字面值
		String value = null;
		try {
			value = field.get(param) == null ? null : field.get(param).toString();
		} catch (Exception e) {
			// 异常返回
			return null;
		}
		// 获取入参字段描述描述信息
		String description = paramAnno.description();
		// 获取入参字段名
		String fieldName = field.getName();
		/* 参数解析工作开始 */
		if (!paramAnno.nullable()) {// 属性不允许为空
			if (value == null || value.equals("")) {
				return field.getName() + description;
			}
		}
		if (paramAnno.minLength() > 0 && value.length() < paramAnno.minLength()) {// 校验最小长度
			return fieldName + description;
		}
		if (paramAnno.maxLength() > 0 && value.length() > paramAnno.maxLength()) {// 校验最大长度
			return fieldName + description;
		}
		if (value != null && paramAnno.regexType() != RegexType.NONE) {// 校验正则表达式描述的字段
			switch (paramAnno.regexType()) {
			case NONE:
				break;
			case NO_SPECIAL_CHAR:
				if (value.matches(RegexType.NO_SPECIAL_CHAR.regex)) {// 不能含有特殊字符
					return fieldName + description;
				}
				break;
			case EMAIL:
				if (!value.matches(RegexType.EMAIL.regex)) {// 邮件地址不匹配
					return fieldName + description + "不合法";
				}
				break;
			case NUMBER:
				if (value.matches(RegexType.NUMBER.regex)) {// 不是数字
					return fieldName + description;
				}
				break;
			default:
				break;
			}
		}
		if (!paramAnno.regex().equals("")) {// 校验自定义正则表达式
			if (!value.matches(paramAnno.regex())) {
				return fieldName + description;
			}
		}
		return null;
	}
}
