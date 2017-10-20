package com.springdemo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.entity.Customer;
import com.springdemo.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void registerUser(User theUser) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		System.out.println(currentSession.save(theUser));

	}
	
	@Override
	public boolean userNameExist(String userName) {
		
		boolean result;
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("select count(*) from User where username=:userName");
		query.setParameter("userName", userName);
		
		result = ((Number) query.uniqueResult()).intValue() > 0;

		return result;
	}

	@Override
	@Transactional
	public User getUserByName(String userName) {
		Session currentSession = sessionFactory.getCurrentSession();
		User theUser = currentSession.get(User.class, userName);
		
		return theUser;
	}

	@Override
	public boolean emailExist(String email) {
		
		boolean result;
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("select count(*) from User where email=:email");
		query.setParameter("email", email);
		
		result = ((Number) query.uniqueResult()).intValue() > 0;
		
		return result;
	}


}
