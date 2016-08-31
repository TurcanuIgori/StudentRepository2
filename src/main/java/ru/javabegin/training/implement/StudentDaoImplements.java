package ru.javabegin.training.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ru.javabegin.training.model.Group;
import ru.javabegin.training.model.PhoneType;
import ru.javabegin.training.model.SearchData;
import ru.javabegin.training.model.Student;
@Repository("studentDaoImplements")
public class StudentDaoImplements{

	private static final Logger logger = LoggerFactory.getLogger(StudentDaoImplements.class);
	private SessionFactory sessionFactory;	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	// delete student by id
	public void deleteStudent(int id) {		
		currentSession().delete(getStudentById(id));
		logger.info("Person had been removed.");
		
	}
	
	// get student by discipline.id
	public Student getStudentById(int id) {
		Query query = currentSession().createQuery("Select distinct p From Student as p Inner Join Fetch p.disciplineList where p.id = :id");
		query.setParameter("id", id);
		return (Student) query.uniqueResult();
	}
	
	// get student by id
	public Student getStudentDetailsById(int id) {
		Query query = currentSession().createQuery("Select distinct p From Student as p where p.id = :id");
		query.setParameter("id", id);
		return (Student) query.uniqueResult();
	}

	//	if student.id > 0 will execute update student else save student
	public void saveStudent(Student student) {
		if(student.getId() != 0){
			currentSession().update(student);
		}else{
			currentSession().save(student);
		}
		
	}
	
	// get a list with all students from database
	@SuppressWarnings("unchecked")
	public List<Student> getAllStudents() {
		Query query = currentSession().createQuery("Select distinct p From Student as p Left Join Fetch p.averageList");
//		  Inner Join FETCH p.listPhones as pl Inner Join FETCH pl.phoneType Left Join FETCH p.abonament as a
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<PhoneType> getAllPhoneTypes() {
		Criteria cr = currentSession().createCriteria(PhoneType.class);
//		Query query = currentSession().createQuery("Select p From PhoneType as p");
		return cr.list();
	}
	@SuppressWarnings("unchecked")
	public List<Group> getAllGroups() {
		 Query query = currentSession().createQuery("Select g From Group as g");
		return query.list();
	}
	
//	get students by criteria
	@SuppressWarnings("unchecked")
	public List<Student> getAllStudentsByCriterias(SearchData searchData) {
		Criteria criteria = currentSession().createCriteria(Student.class, "s");
		if(searchData.getName() != null){
			criteria.add(Restrictions.or(Restrictions.like("s.lastName", "%" + searchData.getName() + "%"), Restrictions.like("s.firstName",  "%"+ searchData.getName() + "%")));
		}
		if(searchData.getGroup() != 0){
			criteria.add(Restrictions.eq("s.grup.id", searchData.getGroup()));
		}
		if(searchData.getGender() != null){
			criteria.add(Restrictions.like("s.gender", searchData.getGender()));
		}
		if(searchData.getStartDob() != null){
			criteria.add(Restrictions.gt("s.dob", searchData.getStartDob()));
		}
		if(searchData.getEndDob() != null){
			criteria.add(Restrictions.le("s.dob", searchData.getEndDob()));
		}
		if(searchData.getAddress() != null){
			criteria.createAlias("s.address", "a");
			criteria.add(Restrictions.or(
				Restrictions.like("a.street", "%" + searchData.getAddress() + "%"),
				Restrictions.like("a.city", "%" + searchData.getAddress() + "%"),
				Restrictions.like("a.country", "%" + searchData.getAddress() + "%")
			));			
		}
		if(searchData.getDiscipline() != 0 && searchData.getDisciplineAvg() != 0){
			criteria.createAlias("s.averageList", "avg");
			criteria.add(Restrictions.gt("avg.averageMark", searchData.getDisciplineAvg()));
			criteria.createAlias("avg.discipline", "ad");
			criteria.add(Restrictions.eq("ad.id", searchData.getDiscipline()));
		}else if(searchData.getDiscipline() != 0){
			criteria.createAlias("s.disciplineList", "d");
			criteria.add(Restrictions.eq("d.id", searchData.getDiscipline()));
		}else if(searchData.getDisciplineAvg() != 0){
			criteria.createAlias("s.averageList", "avg");
			criteria.add(Restrictions.gt("avg.averageMark", searchData.getDisciplineAvg()));
		}
		List<Student> students = criteria.list();
		return students;
	}
}

