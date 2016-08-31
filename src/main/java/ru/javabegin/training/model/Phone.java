package ru.javabegin.training.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
@Table
public class Phone implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
		
	@Pattern(regexp="^[0-9]{3,15}$", message="Invalid phone.")
	@Size(min=9, max=14, message="Your phone must be between 9 and 14 characters long.")
	@Column
	private String phone;
	
	@Valid
	@ManyToOne
	@JoinColumn(name="type_id", referencedColumnName="id")
	private PhoneType phoneType;
			
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public PhoneType getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}	
}
