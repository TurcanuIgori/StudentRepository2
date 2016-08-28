package md.spring.interfaces;

import java.util.List;

import md.spring.model.Person;
import md.spring.model.Student;

public interface StudentDao {

	void deleteStudent(int id);
	Person getStudentById(int id);
	void saveStudent(Student student);
	List<Student> getAllStudents();
}
