package com.stafffinder.task.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class User {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(length=50)
	private String firstName;
	
	@Column(length=50)
	private String lastName;
	
	@Column(nullable = false, length=50)
	private String password;
	
	@Column(name = "land_number")
	private String landlinePhoneNumber;
	
	@Column(name = "mobile_number")
	private String mobilePhoneNumber;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLandlinePhoneNumber() {
		return landlinePhoneNumber;
	}

	public void setLandlinePhoneNumber(String landlinePhoneNumber) {
		this.landlinePhoneNumber = landlinePhoneNumber;
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	@Override
	public String toString() {
		return "Track [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", landlinePhoneNumber=" + landlinePhoneNumber + ", mobilePhoneNumber=" + mobilePhoneNumber
				+ "]";
	}

}