package md.spring.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import md.spring.interfaces.StudentDao;
import md.spring.model.Discipline;
import md.spring.model.DisciplineAverage;
import md.spring.model.Group;
import md.spring.model.Person;
import md.spring.model.PhoneType;
import md.spring.model.SearchData;
import md.spring.model.Student;
@Repository("studentDaoImplements")
public class StudentDaoImplements implements StudentDao{

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
	public List<Student> getAllStudents() {
		Query query = currentSession().createQuery("Select distinct p From Student as p ");
//		  Inner Join FETCH p.listPhones as pl Inner Join FETCH pl.phoneType Left Join FETCH p.abonament as a
		return query.list();
	}
	public List<PhoneType> getAllPhoneTypes() {
		Criteria cr = currentSession().createCriteria(PhoneType.class);
//		Query query = currentSession().createQuery("Select p From PhoneType as p");
		return cr.list();
	}
	public List<Group> getAllGroups() {
		 Query query = currentSession().createQuery("Select g From Group as g");
		return query.list();
	}
	
//	get students by criteria
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
		return criteria.list();
	}
}

