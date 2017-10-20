package com.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.dao.UserDAO;
import com.springdemo.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public void registerUser(User theUser) {
		userDAO.registerUser(theUser);
	}
	
	@Override
	@Transactional
	public boolean userNameExist(String theUserName) {
		return userDAO.userNameExist(theUserName);
	}

	@Override
	@Transactional
	public boolean emailExist(String email) {
		return userDAO.emailExist(email);
	}

}
