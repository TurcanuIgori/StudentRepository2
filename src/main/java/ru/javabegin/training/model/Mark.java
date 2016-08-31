package ru.javabegin.training.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class Mark {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne()
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private Student student;
	
	@Valid
	@ManyToOne()
	@JoinColumn(name = "discipline_id", referencedColumnName = "id")
	private Discipline discipline;
	
	
	@ManyToOne()
	@JoinColumn(name = "proffessor_id", referencedColumnName = "id")
	private Proffessor proffessor;
	
	@DecimalMax(value="10.00", message="Mark is greater than 10")
	@DecimalMin(value = "1.00", message = "Mark is less than 1")
	@Column
	private BigDecimal mark;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Discipline getDiscipline() {
		return discipline;
	}
	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	public Proffessor getProffessor() {
		return proffessor;
	}
	public void setProffessor(Proffessor proffessor) {
		this.proffessor = proffessor;
	}	
	public BigDecimal getMark() {
		return mark;
	}
	public void setMark(BigDecimal mark) {
		this.mark = mark;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
