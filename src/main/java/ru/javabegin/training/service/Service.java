package ru.javabegin.training.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.javabegin.training.controller.NotFoundDataException;
import ru.javabegin.training.controller.NullDataException;
import ru.javabegin.training.implement.DisciplineDao;
import ru.javabegin.training.implement.LibraryAbonamentDao;
import ru.javabegin.training.implement.MarkDao;
import ru.javabegin.training.implement.StudentDaoImplements;
import ru.javabegin.training.model.Discipline;
import ru.javabegin.training.model.Group;
import ru.javabegin.training.model.LibraryAbonament;
import ru.javabegin.training.model.Mark;
import ru.javabegin.training.model.PhoneType;
import ru.javabegin.training.model.SearchData;
import ru.javabegin.training.model.Student;

@Component
public class Service {	
	private StudentDaoImplements studentDaoImplements;	
	private LibraryAbonamentDao abonamentDao;
	private DisciplineDao disciplineDao;
	private MarkDao markDao;
	public void setAbonamentDao(LibraryAbonamentDao abonamentDao) {
		this.abonamentDao = abonamentDao;
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
	@Transactional(propagation=Propagation.REQUIRED)
	public Student saveStudent(Student student) throws NullDataException{
		return studentDaoImplements.saveStudent(student);
	}
	@Transactional(propagation=Propagation.SUPPORTS)
	public Student getStudentById(int id){
		return studentDaoImplements.getStudentById(id);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteStudent(int id) {
		studentDaoImplements.deleteStudent(id);		
	}	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Student> getAllStudents() {
		return new ArrayList(new HashSet(studentDaoImplements.getAllStudents()));
	}
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<PhoneType> getAllPhoneTypes() {
		return studentDaoImplements.getAllPhoneTypes();
	}
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Group> getAllGroups() {
		return studentDaoImplements.getAllGroups();
	}
	@Transactional(propagation=Propagation.SUPPORTS)
	public LibraryAbonament getAbonamentByStudentId(int id) throws NotFoundDataException {		 
		return abonamentDao.getAbonamentByStudentId(id);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveAbonament(LibraryAbonament abonament) throws NullDataException{
		abonamentDao.saveAbonament(abonament);
	}
	@Transactional(propagation=Propagation.SUPPORTS)
	public Student getStudentDetailsById(int id) throws DataAccessException, NotFoundDataException{
		return studentDaoImplements.getStudentDetailsById(id);
	}	
	@Transactional(propagation=Propagation.SUPPORTS)
	public Discipline getDisciplineById(int id) {
		return disciplineDao.getDisciplineById(id);
	}
	
	//add mark
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveMark(Mark mark) throws NullDataException{
		 markDao.saveMark(mark);
	}
	
	//get all discipline from database
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Discipline> getAllDiscipline() {		
		return disciplineDao.getAllDiscipline();
	}
	
	//get all students by criteria
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Student> getAllStudentsByCriterias(SearchData searchData) {
		 return new ArrayList<Student>(new HashSet<Student>(studentDaoImplements.getAllStudentsByCriterias(searchData)));
	}
}
