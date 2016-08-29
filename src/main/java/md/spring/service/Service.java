package md.spring.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import md.spring.implement.AddressDao;
import md.spring.implement.DisciplineDao;
import md.spring.implement.LibraryAbonamentDao;
import md.spring.implement.MarkDao;
import md.spring.implement.PhoneDao;
import md.spring.implement.StudentDaoImplements;
import md.spring.model.Discipline;
import md.spring.model.Group;
import md.spring.model.LibraryAbonament;
import md.spring.model.Mark;
import md.spring.model.PhoneType;
import md.spring.model.SearchData;
import md.spring.model.Student;

@Component
public class Service {	
	private StudentDaoImplements studentDaoImplements;	
	private PhoneDao phoneDao;
	private AddressDao addressDao;
	private LibraryAbonamentDao abonamentDao;
	private DisciplineDao disciplineDao;
	private MarkDao markDao;
	public PhoneDao getPhoneDao() {
		return phoneDao;
	}
	@Autowired
	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}	
	public void setAbonamentDao(LibraryAbonamentDao abonamentDao) {
		this.abonamentDao = abonamentDao;
	}
	@Autowired
	public void setPhoneDao(PhoneDao phoneDao) {
		this.phoneDao = phoneDao;
	}
	
	public void setDisciplineDao(DisciplineDao disciplineDao) {
		this.disciplineDao = disciplineDao;
	}
	
	public void setMarkDao(MarkDao markDao) {
		this.markDao = markDao;
	}
	@Autowired
	public void setStudentDaoImplements(StudentDaoImplements studentDaoImplements) {		
		
		this.studentDaoImplements = studentDaoImplements;
	}	
	

	@Transactional
	public void saveStudent(Student student){
		studentDaoImplements.saveStudent(student);
	}
	@Transactional
	public Student getStudentById(int id){
		return studentDaoImplements.getStudentById(id);
	}

	@Transactional
	public void deleteStudent(int id) {
		studentDaoImplements.deleteStudent(id);		
	}
	@Transactional
	public List<Student> getAllStudents() {
		return new ArrayList(new HashSet(studentDaoImplements.getAllStudents()));
	}
	@Transactional
	public List<PhoneType> getAllPhoneTypes() {
		return studentDaoImplements.getAllPhoneTypes();
	}
	@Transactional
	public List<Group> getAllGroups() {
		return studentDaoImplements.getAllGroups();
	}
	@Transactional
	public LibraryAbonament getAbonamentByStudentId(int id) {		 
		return abonamentDao.getAbonamentByStudentId(id);
	}
	@Transactional
	public void saveAbonament(LibraryAbonament abonament) {
		abonamentDao.saveAbonament(abonament);
				
	}
	@Transactional
	public Student getStudentDetailsById(int id) {
		return studentDaoImplements.getStudentDetailsById(id);
	}
	@Transactional
	public Discipline getDisciplineById(int id) {
		return disciplineDao.getDisciplineById(id);
	}
	
	//add mark
	@Transactional
	public void saveMark(Mark mark) {
		 markDao.saveMark(mark);
		
	}
	
	//get all discipline from database
	@Transactional
	public List<Discipline> getAllDiscipline() {		
		return disciplineDao.getAllDiscipline();
	}
	
	//get all students by criteria
	@Transactional
	public List<Student> getAllStudentsByCriterias(SearchData searchData) {
		 return new ArrayList<Student>(new HashSet<Student>(studentDaoImplements.getAllStudentsByCriterias(searchData)));
	}
}
