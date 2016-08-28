package md.spring.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@Table
public class Student extends Person{

	private static final long serialVersionUID = 1L;
		
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name="student_grup", joinColumns={@JoinColumn(name="student_id")}, inverseJoinColumns={@JoinColumn(name="group_id")})
	@PrimaryKeyJoinColumn
	private Group grup;

	@ManyToMany(cascade=CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="student_discipline", joinColumns={@JoinColumn(name="student_id")}, inverseJoinColumns={@JoinColumn(name="discipline_id")})
	private List<Discipline> disciplineList;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Set<Mark> markList;
	
	@OneToMany(targetEntity=DisciplineAverage.class, mappedBy = "student", cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)	
	private Set<DisciplineAverage> averageList;
	
	public Set<Mark> getMarkList() {
		return markList;
	}

	public void setMarkList(Set<Mark> markList) {
		this.markList = markList;
	}

	public Group getGrup() {
		return grup;
	}

	public void setGrup(Group grup) {
		this.grup = grup;
	}

	public List<Discipline> getDisciplineList() {
		return disciplineList;
	}

	public void setDisciplineList(List<Discipline> disciplineList) {
		this.disciplineList = disciplineList;
	}

	public Set<DisciplineAverage> getAverageList() {
		return averageList;
	}

	public void setAverageList(Set<DisciplineAverage> averageList) {
		this.averageList = averageList;
	}

}
