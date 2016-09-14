package com.lydck.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.lydck.domain.User;

@Service("userHiberDaoService")
public class UserHiberDaoService {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public void addUser(User user) {
		hibernateTemplate.persist(user);
	}
}
