package com.example.service;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.model.User;

@Service
public class UserDaoImpl implements UserDao{

	@Autowired
	SessionFactory factory;
	
	
	public UserDaoImpl() {
		System.out.println("Inside Constructor of "+getClass().getName());
	}
	

	@Override
	public User getUserById(int userId) {
		
    return factory.getCurrentSession().get(User.class, userId);
	}
	
	
	
	@Override
	public String registerUser(User user) {
		System.out.println("Inside UserDao registerUser");
		
		factory.getCurrentSession().save(user);
		return "User Registered Successfully";
	}
		
	@Override
	public User validateUser(String email, String password) {
		System.out.println("Inside UserDao validateUser");
		String jpql = "select u from User u where u.emailId=:emailId and u.password=:password";
		User user = factory.getCurrentSession().createQuery(jpql, User.class).setParameter("emailId", email).setParameter("password", password).getSingleResult();
		System.out.println("+++++++++++++"+user);
		if(user != null)
		{
			return user;
		}
		return null;
	}
	
	@Override
	public User forgotPassword(String emailId) {
		System.out.println("emailId : "+emailId);
		System.out.println("Inside Dao forgot Password");
		String jpql = "select u from User u where u.emailId=:email";
		//System.out.println(factory.getCurrentSession().createQuery(jpql, User.class).setParameter("email",emailId).getSingleResult());
		return factory.getCurrentSession().createQuery(jpql, User.class).setParameter("email",emailId).getSingleResult();
	}


	@Override
	public String updateUser(User user) {
		System.out.println("in update User dao");
		factory.getCurrentSession().saveOrUpdate(user);
		return "user details updated successfully";
	}
	
	
}
	