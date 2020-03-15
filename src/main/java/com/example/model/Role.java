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
@Table(name="Role")
public class Role {

	//properties
	private Integer roleId;
	private String roleName;
	
	//Role--->*Users mapping parent side
	@JsonIgnore
	private List<User> users = new ArrayList<>();

	@OneToMany(mappedBy="selectedRole",cascade=CascadeType.ALL)
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	//default ctor
	public Role() {
		System.out.println("Inside Constructor of "+getClass().getName());
	}

	//parameterised ctor
	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}

	//getter and setters
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="roleId",length=10)
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name="role_name",length=15,unique=true)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	//toString
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	
	/*
	 * //convenience methods public void addUser(User user) { users.add(user);
	 * user.setSelectedRole(this); }
	 * 
	 * public void removeUser(User user) { users.remove(user);user .
	 * user.setSelectedRole(null); }
	 */
}
