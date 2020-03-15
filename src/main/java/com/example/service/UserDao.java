package com.example.service;

import java.util.List;

import com.example.model.User;


public interface UserDao {
	public String registerUser(User user);
	public User validateUser(String email,String password);
	public User forgotPassword(String emailId);
	public String updateUser(User user);
	public User getUserById(int userId);
	
}
