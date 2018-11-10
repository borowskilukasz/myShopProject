package com.lukaszborowski.ShoppingBackend.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private fields
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	//many addresses to one user
	
	//@ManyToOne
	//private User user;

	@NotBlank(message = "Please enter street")
	private String street;
	@NotBlank(message = "Please enter city")
	private String city;
	@NotBlank(message = "Please enter state")
	private String state;
	@NotBlank(message = "Please enter country")
	private String country;
	
	@Column(name= "postal_code")
	@NotBlank(message = "Please enter postal code")
	private String postalCode;
	private boolean shipping;
	private boolean billing;
	
	@Column(name= "user_id")
	private int userId;

	//getters and setters
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public boolean isShipping() {
		return shipping;
	}
	public void setShipping(boolean shipping) {
		this.shipping = shipping;
	}
	public boolean isBilling() {
		return billing;
	}
	public void setBilling(boolean billing) {
		this.billing = billing;
	}
	@Override
	public String toString() {
		return "Address [id= " + id + ", userId= " + userId + ", street= " + street + ", city= " + city 
				+ ", state= " + state + ", country= " + country + ", postalCode= " + postalCode +
				", shipping= " + shipping + ", billing= " + billing + "]";
	}
	
	
	
}
