package com.springdemo.service;

import com.springdemo.entity.User;

public interface UserService {
	
	public void registerUser(User user);
	public boolean userNameExist(String userName);
	public boolean emailExist(String email);
	

}
