package md.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
@Table(name="address")
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Pattern(regexp="^(([A-Za-z]+)(\\s[A-Za-z]+)*)$", message="Invalid street.")
	@Size(min=3, max=20, message="Your street must be between 3 and 20 characters long.")
	@Column
	private String street;
	
	@Pattern(regexp="^(([A-Za-z]+)(\\s[A-Za-z]+)*)$", message="Invalid city.")
	@Size(min=3, max=20, message="Your city must be between 3 and 20 characters long.")
	@Column
	private String city;
	
	@Pattern(regexp="^(([A-Za-z]+)(\\s[A-Za-z]+)*)$", message="Invalid country.")
	@Size(min=3, max=20, message="Your country must be between 3 and 20 characters long.")
	@Column
	private String country;
	
	@OneToMany(mappedBy="address", orphanRemoval=true)
	private List<Person> personList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<Person> getPersonList() {
		return personList;
	}
	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
	
	

}
