package com.rs.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rs.model.User;
import com.rs.service.IUserService;

public class UserServiceImpl implements IUserService {
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class); 

	public List<User> displayAllUsers() {
		User user1 = new User("Ramesh", "Hyderabad");
		try {
			logger.info(Thread.currentThread().getName() + " entered into sleep..");
			Thread.sleep(10000);
			logger.info(Thread.currentThread().getName() + " entered into sleep done..");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Arrays.asList(user1);
	}

}
