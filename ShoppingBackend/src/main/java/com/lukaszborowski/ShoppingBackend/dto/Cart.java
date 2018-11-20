package com.lukaszborowski.ShoppingBackend.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Class that represents cart table from database 
 * @author Lukasz Borowski
 *
 */
@Entity
public class Cart implements Serializable{

	//---------------------------------------------------------------------------------------
	//private fields 
	//---------------------------------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private User user;
	@Column(name= "grand_total")
	private double grandTotal;
	@Column(name= "cart_lines")
	private int cartLines;

	//----------------------------------------------------------------------------------------
	// getters and setters
	//----------------------------------------------------------------------------------------
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	public int getCartLines() {
		return cartLines;
	}
	public void setCartLines(int cartLines) {
		this.cartLines = cartLines;
	}
	@Override
	public String toString() {
		return "Cart [id= " + id + ", user= " + user + ", grandTotal= " + grandTotal + ", cartLines= " + 
				cartLines + "]";
	}
	
	
}
