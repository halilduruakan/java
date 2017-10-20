package com.springdemo.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springdemo.dao.UserDAO;
import com.springdemo.entity.User;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User user = userDAO.getUserByName(userName);
		
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUserName(),
																									   user.getPassword(),
																									   Arrays.asList(authority));
		
		return userDetails;
	}

}
