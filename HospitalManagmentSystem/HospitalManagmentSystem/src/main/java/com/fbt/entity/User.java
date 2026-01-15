package com.fbt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userID;
	@Column(name = "EmailId", unique = true, nullable = false)
	private String email;
	private long mobNo;
	@Column(unique = true, nullable = false) // 👈 Added unique constraint
    private String userName;
	
	private String password;
	@ManyToOne(fetch = FetchType.EAGER) // Eager fetch to get role with user
	@JoinColumn(name = "roleId", referencedColumnName = "id")
	private Role role;
	private boolean active;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobNo() {
		return mobNo;
	}
	public void setMobNo(long mobNo) {
		this.mobNo = mobNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "User [userID=" + userID + ", email=" + email + ", mobNo=" + mobNo + ", userName=" + userName
				+ ", password=" + password + ", role=" + role + ", active=" + active + "]";
	}
	public User(int userID, String email, long mobNo, String userName, String password, Role role, boolean active) {
		super();
		this.userID = userID;
		this.email = email;
		this.mobNo = mobNo;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.active = active;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
