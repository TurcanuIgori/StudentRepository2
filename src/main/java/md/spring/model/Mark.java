package md.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "discipline_id", referencedColumnName = "id")
	private Discipline discipline;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "proffessor_id", referencedColumnName = "id")
	private Proffessor proffessor;
	
	@Column
	private float mark;
	
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
	public float getMark() {
		return mark;
	}
	public void setMark(float mark) {
		this.mark = mark;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
