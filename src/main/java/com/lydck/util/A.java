package com.lydck.util;

public class A {
	@ParamValiAnno(regexType = RegexType.EMAIL, description = "邮箱地址")
	public String propertyA;
	@ParamValiAnno(minLength = 12, description = "最小长度为12")
	private String propertyB;
	public static String propertyC;
	public Weekday workDay;
	@ParamValiAnno(nullable = false, description="内容对象不能为空")
	private Object context;

	public Object getContext() {
		return context;
	}

	public void setContext(Object context) {
		this.context = context;
	}

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

	public static void main(String[] args) {

		A param = new A();
		param.setPropertyA("123@q.com");
		param.setPropertyB("123456789123");
		param.setContext(new Object());
		String result = ValidateUtil.validate(param);

		System.out.println(result);
	}
}
