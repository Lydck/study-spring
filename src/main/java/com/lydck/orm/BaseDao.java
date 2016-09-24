package com.lydck.orm;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao<T> {
	@Autowired
	private SqlSessionTemplate batisTemplate;

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class<T>) params[0];
	}

	public T get(String sqlId, Object parameter) {
		return batisTemplate.selectOne(sqlId, parameter);
	}

}
