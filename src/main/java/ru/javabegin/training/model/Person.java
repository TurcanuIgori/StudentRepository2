package ru.javabegin.training.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
//@MappedSuperclass
public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Pattern(regexp="^(([A-Za-z]+)(\\s[A-Za-z]+)*)$", message="Invalid first name.")
	@Size(min=3, max=20, message="Your first name must be between 3 and 20 characters long.")
	@Column(name="firstname")
	private String firstName;
	
	@Pattern(regexp="^(([A-Za-z]+)(\\s[A-Za-z]+)*)$", message="Invalid last name.")
	@Size(min=3, max=20, message="Your last name must be between 3 and 20 characters long.")
	@Column(name="lastname")
	private String lastName;
	
	@Valid
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)	
	@JoinColumn(name="address_id")	
	private Address address;
	
	@Valid
	@OneToMany(fetch=FetchType.EAGER, orphanRemoval = true, cascade=CascadeType.ALL)
	@JoinTable(name="person_phone", joinColumns={@JoinColumn(name="person_id")}, inverseJoinColumns={@JoinColumn(name="phone_id")})	
	private List<Phone> listPhones;
	
	@OneToOne(mappedBy = "person", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private LibraryAbonament abonament;
	
	@NotNull(message="Please provide a date.")	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="dob")
	private Date dob;
	
	@NotNull(message="Please select gender.")
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "char(1)")
	private GENDER gender;
	
	@NotNull(message="Please provide an email.")
	@Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",	message="Invalid email address.")
	@Column
	private String email;
	
	@Column
	private byte[] image;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Phone> getListPhones() {
		return listPhones;
	}
	public void setListPhones(List<Phone> listPhones) {
		this.listPhones = listPhones;
	}
	public LibraryAbonament getAbonament() {
		return abonament;
	}
	public void setAbonament(LibraryAbonament abonament) {
		this.abonament = abonament;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public GENDER getGender() {
		return gender;
	}
	public void setGender(GENDER gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
}
