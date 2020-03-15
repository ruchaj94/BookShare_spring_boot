package com.example.model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Questions")
public class Questions {

	//properties
	private Integer questionId;
	private String question;
	
	//many side of asso
	//User--------->*Question
	@JsonIgnore
	private List<User> users = new ArrayList<>();
	
	@OneToMany(mappedBy="selectedQuestion",cascade=CascadeType.ALL)
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	//default ctor
	public Questions() {
		System.out.println("Inside Constructor of "+getClass().getName());
	}

	//parameterised ctor
	public Questions(String question) {
		super();
		this.question = question;
	}

	//getters and setters
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="questionId",length=10)
	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Column(name="question",length=50,unique=true)
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	//toString
	@Override
	public String toString() {
		return "Questions [questionId=" + questionId + ", question=" + question + "]";
	}
	
	//convenience method
	/*
	 * public void addUser(User user) { users.add(user);
	 * user.setSelectedQuestion(this); }
	 * 
	 * public void removeUser(User user) { users.remove(user);
	 * user.setSelectedQuestion(null); }
	 */
	
}
