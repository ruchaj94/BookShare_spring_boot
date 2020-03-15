package com.example.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cities")
public class City {
	private Integer cityId;
	private String cityName;
	private State state1;
	
	@JsonIgnore
	private List<User> users = new ArrayList<>();
	
	public City() {
		System.out.println("inside ctor of"+getClass().getName());
	}

	public City(String cityName) {
		super();
		this.cityName = cityName;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="city_id",length=10)
	public Integer getCityId()
	{
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Column(name="city_name",length=15,unique=true)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@OneToMany(mappedBy="city",cascade=CascadeType.ALL)
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@ManyToOne
	@JoinColumn(name="state1_id")
	public State getState1() {
		return state1;
	}

	public void setState1(State state1) {
		this.state1 = state1;
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + "]";
	}
	

}
