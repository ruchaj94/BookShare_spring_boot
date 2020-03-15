package com.example.model;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="User")
public class User {

	//properties
	private Integer userId;
	private String profilePhoto;
	private String fName;
	private String lName;
	private String emailId;
	private String password;
	private String adharNo;
	private String mobileNo;
	private String answer;
	private String address;
	private String gender;
	
	//many side of asso
	//User------->*question
	private Questions selectedQuestion;
	
	//Role-------------->*Users
	private Role selectedRole;
	
	//*User------->city	
	private City city;
	//*User------->*state	
	private State state;
	
    @ManyToOne
	@JoinColumn(name="question_id")
	public Questions getSelectedQuestion() {
		return selectedQuestion;
	}

	public void setSelectedQuestion(Questions selectedQuestion) {
		this.selectedQuestion = selectedQuestion;
	}

	@ManyToOne
	@JoinColumn(name="role_id")
	public Role getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(Role selectedRole) {
		this.selectedRole = selectedRole;
	}

	@ManyToOne
	@JoinColumn(name="city_id")
	public City getCity() {
		return city;
	}

	public void setCity(City city) 
	{
		this.city = city;
	}

	@ManyToOne
	@JoinColumn(name="state_id")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	
	//User---->*Books parent side
	@JsonIgnore
	private List<Book> books = new ArrayList<>();
	
	@OneToMany(mappedBy="owner",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	//default ctor
	public User() {
		System.out.println("Inside Users default Ctor");
	}
	
	//parameterized ctor
	public User(Integer userId, String profilePhoto, String fName, String lName, String emailId, String password,
			String adharNo, String mobileNo, String answer, String address, String gender) {
		super();
		this.userId = userId;
		this.profilePhoto = profilePhoto;
		this.fName = fName;
		this.lName = lName;
		this.emailId = emailId;
		this.password = password;
		this.adharNo = adharNo;
		this.mobileNo = mobileNo;
		this.answer = answer;
		this.address = address;
		this.gender = gender;
	}


	//getter and setters
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userId",length=10)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name="profilePhoto",length=30)
	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	
	@Column(name="fName",length=15)
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	@Column(name="lName",length=15)
	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	@Column(name="emailId",length=30,unique=true)
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name="password",length=400)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="adharNo",length=15,unique=true)
	public String getAdharNo() {
		return adharNo;
	}

	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}
	
	@Column(name="mobileNo",length=15,unique=true)
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	@Column(name="answer",length=15)
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Column(length=100)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(length=10)
	public String getGender() {
		return gender;
	}

    public void setGender(String gender) {
		this.gender = gender;
	}
 
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", profilePhoto=" + profilePhoto + ", fName=" + fName + ", lName=" + lName
				+ ", emailId=" + emailId + ", password=" + password + ", adharNo=" + adharNo + ", mobileNo=" + mobileNo
				+ ", answer=" + answer + "]";
	}
	
}