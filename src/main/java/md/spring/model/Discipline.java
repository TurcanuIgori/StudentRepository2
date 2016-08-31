package md.spring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@Table
public class Discipline {
	
	@Min(value = 1, message = "Select discipline.")	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String title;
	
	@Column
	private double scholarshipThreshold;
	
	@OneToMany(fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "professor_discipline", joinColumns = { @JoinColumn(name = "discipline_id") }, inverseJoinColumns = { @JoinColumn(name = "profesor_id") })
	private List<Proffessor> proffessorList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getScholarshipThreshold() {
		return scholarshipThreshold;
	}
	public void setScholarshipThreshold(double scholarshipThreshold) {
		this.scholarshipThreshold = scholarshipThreshold;
	}
	public List<Proffessor> getProffessorList() {
		return proffessorList;
	}
	public void setProffessorList(List<Proffessor> proffessorList) {
		this.proffessorList = proffessorList;
	}
}
