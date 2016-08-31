package md.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
@Entity
public class PhoneType implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Min(value = 1, message = "Select Phone type.")	
	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String type;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
