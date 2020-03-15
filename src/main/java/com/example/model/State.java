package com.example.model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="states")
public class State {

	private Integer stateId;
	private String stateName;
	
	@JsonIgnore
	private List<User> users = new ArrayList<>();
	
	@JsonIgnore
	private List<City> cities = new ArrayList<>();
	
	public State() {
		System.out.println("inside ctor of"+getClass().getName());
	}

	public State(String stateName) {
		super();
		this.stateName = stateName;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="state_id",length=10)
	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	@Column(name="state_name",length=15,unique=true)
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	

	@OneToMany(mappedBy="state",cascade=CascadeType.ALL)
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@OneToMany(mappedBy="state1",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return "State [stateId=" + stateId + ", stateName=" + stateName + "]";
	}
	
	public void addCity(City city)
	{
		cities.add(city);
		city.setState1(this);
	}
	
	public void removeCity(City city)
	{
		cities.remove(city);
		city.setState1(null);
	}
	
	
}
