package com.springdemo.dao;


import com.springdemo.entity.User;

public interface UserDAO {
	
	public void registerUser(User theUser);
	public boolean userNameExist(String userName);
	public User getUserByName(String userName);
	public boolean emailExist(String email);
	
}
