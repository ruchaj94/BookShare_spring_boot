package com.example.model;

public class Message {

	private String emailId;
	private String subject;
	private String message;
	private String attachmentURL;
	public Message() {
		System.out.println("Inside Constructor of "+getClass().getName());
	}
	public Message(String emailId, String subject, String message) {
		super();
		this.emailId = emailId;
		this.subject = subject;
		this.message = message;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getAttachmentURL() {
		return attachmentURL;
	}
	public void setAttachmentURL(String attachmentURL) {
		this.attachmentURL = attachmentURL;
	}
	@Override
	public String toString() {
		return "Message [emailId=" + emailId + ", subject=" + subject + ", message=" + message + "]";
	}
	
}
