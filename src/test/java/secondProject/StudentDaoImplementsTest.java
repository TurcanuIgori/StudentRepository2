package secondProject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ru.javabegin.training.controller.NotFoundDataException;
import ru.javabegin.training.controller.NullDataException;
import ru.javabegin.training.model.Address;
import ru.javabegin.training.model.GENDER;
import ru.javabegin.training.model.Group;
import ru.javabegin.training.model.Phone;
import ru.javabegin.training.model.PhoneType;
import ru.javabegin.training.model.Student;
import ru.javabegin.training.service.Service;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations={"servlet-context.xml"})
public class StudentDaoImplementsTest {

	@Autowired
	private Service service;
	
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}	
	
	//check if method service.save(Student student) will return student with auto-generate id
	@Test
	@Rollback(true)
	public void saveStudentTest(){
		Student student = new Student();
		student.setFirstName("firstNameTest");
		student.setLastName("lastNameTest");
		Address address = new Address();
		address.setStreet("PaciiTest");
		address.setCity("BaltiTest");
		address.setCountry("MoldovaTest");
		student.setAddress(address);
		student.setDob(new Date(2016-01-01));
		student.setGender(GENDER.M);
		student.setEmail("test@gmail.com");
		Group group = new Group();
		group.setId(1);
		student.setGrup(group);
		List<Phone> phoneList = new ArrayList();
		Phone phone = new Phone();
		PhoneType phoneType = new PhoneType();
		phoneType.setId(1);
		phone.setPhoneType(phoneType);
		phone.setPhone("025657280");
		student.setListPhones(phoneList);
		student = service.saveStudent(student);	
		assertNotNull(student.getId());
	}
	
	//if we send null object method must return exception
	@Test(expected=NullDataException.class)
	public void saveStudentTest1(){
		Student student = null;
		service.saveStudent(student);
	}
	
	//if not registration with this id in database method must return NotFoundDataException exception
	@Test(expected=NotFoundDataException.class)
	@Rollback(true)
	public void getStudentDetailsByIdTest(){
		assertEquals(1, service.getStudentDetailsById(1).getId());
	}
	
	//if not student with current id in database method must return Not FoundData Exception exception 
	@Test(expected=NotFoundDataException.class)
	@Rollback(true)
	public void deleteStudentTest(){
		service.deleteStudent(13);
		assertEquals(13, service.getStudentDetailsById(13).getId());
	}
	
}
