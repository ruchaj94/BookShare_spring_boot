package com.example.Controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.*;
import com.example.model.*;

@CrossOrigin(origins="*",allowedHeaders = "*")
//@CrossOrigin(origins = "http://localhost:4200", methods="GET,POST,PUT,DELETE,ORIGIN")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserDao dao;
	
	@Autowired
	JavaMailSender mailSender;
	
	public UserController() {
		System.out.println("Inside Constructor of "+getClass().getName());
	}
	
	
	@GetMapping("/{sid}")
	public ResponseEntity<?> getUserDetails(@PathVariable int sid) {
		System.out.println("rest srvr : get user dtls");
		User u = dao.getUserById(sid);
	
		
		if (u != null) 
		{
			// valid student id
			//System.out.println(u);
			//return new ResponseEntity<>(u, HttpStatus.OK);
			
			Map<String, Object> responseBody = new HashMap<String,Object>();
			responseBody.put("status", "SUCCESS");
			responseBody.put("message", "User with id :"+sid);
			responseBody.put("data", u);
			return new ResponseEntity<Map<String,Object>>(responseBody, HttpStatus.CREATED);

		}
		// invalid id 
		return new ResponseEntity<>("Invalid User ID ", HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping(value="reg",consumes = "application/json")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
			System.out.println("Inside Controller Register User");
			
		/*
		 * System.out.println("Question: "+user.getSelectedQuestion().getQuestionId());
		 * System.out.println(user.getCity());
		 */
			try
			{
				String stat = dao.registerUser(user);
				Map<String, String> responseBody = new HashMap<String,String>();
				responseBody.put("status", "SUCCESS");
				responseBody.put("message", "Registration done successfully");
				responseBody.put("data", stat);
				return new ResponseEntity<Map<String,String>>(responseBody, HttpStatus.CREATED);

				
				//return new ResponseEntity<String>(dao.registerUser(user), HttpStatus.CREATED);
				//return new ResponseEntity<String>("data send", HttpStatus.CREATED);
			}
			catch (RuntimeException e) {
				System.out.println("Error in Registration "+e);
				return new ResponseEntity<String>("User Registration Fail", HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@PutMapping(value="update",consumes="application/json")
	public ResponseEntity<?> updateUser(@RequestBody User user)
	{
		System.out.println("Inside User Controller update USer");
		try
		{
			String stat = dao.updateUser(user);
			
			Map<String, String> responseBody = new HashMap<String, String>();
			responseBody.put("status", "SUCCESS");
			responseBody.put("message", "User updated successfully");
			responseBody.put("data", stat);
			return new ResponseEntity<Map<String,String>>(responseBody, HttpStatus.CREATED);

			//return new ResponseEntity<String>(dao.updateUser(user), HttpStatus.OK);
		}
		catch (RuntimeException e) {
			// TODO: handle exception
			System.out.println("Error in update User");
			return new ResponseEntity<String>("User updation Failed", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	
	@PostMapping(value="login",consumes = "application/json")
	public ResponseEntity<?> validateUser(@RequestBody Map<String, String> payload)
	{
		String email = payload.get("email");
		String password = payload.get("password");
		System.out.println("Inside Controller Validate User: email"+email+" password:"+password);
		
		try
		{
			User u = dao.validateUser(email, password);
			System.out.println(u);
		
			Map<String, Object> responseBody = new HashMap<String, Object>();
			responseBody.put("status", "SUCCESS");
			responseBody.put("message", "User has been logged in successfully");
			
			u.setPassword("asdfghjklikmmnbvcxzgfds");
			responseBody.put("data", u);

			return new ResponseEntity<Map<String,Object>>(responseBody, HttpStatus.CREATED);
		}
		catch (NoResultException ex) {
			System.out.println("Error in ValidateUser "+ex);
			return new ResponseEntity<String>("failed", HttpStatus.OK);
		}
	}
	
	
	/* ================================================================= */
	@PostMapping(value="forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestBody String email)
	{
		System.out.println(email);
		try
		{
			return new ResponseEntity<User>(dao.forgotPassword(email), HttpStatus.CREATED);
		}
		catch (RuntimeException e) {
			System.out.println("Error in Forgot Password "+e);
			return new ResponseEntity<String>("failed", HttpStatus.OK);
		}
	}
	
	
	@PostMapping(value="sendMsg",consumes = "application/json")
	public ResponseEntity<String> sendMessage(@RequestBody Message msg)
	{
		System.out.println("Inside Controller Send Message");
		System.out.println("Message: "+msg.toString());
		try
		{
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(msg.getEmailId());
			mailMessage.setSubject(msg.getSubject());
			mailMessage.setText(msg.getMessage());
			try
			{
			
				mailSender.send(mailMessage);
				return new ResponseEntity<String>("Message Send", HttpStatus.CREATED);
			}
			catch (MailException e) {
				System.out.println("inside mail exception");
				e.printStackTrace();
			}
			return new ResponseEntity<String>("Message Send Fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (RuntimeException e) {
			System.out.println("Error in Send Message "+e);
			return new ResponseEntity<String>("Message Send Fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
}
