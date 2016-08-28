package md.spring.service;

import java.util.List;

import md.spring.model.Person;

public interface ServicePerson {
	void updatePerson(Person person);
	void deletePerson(int id);
	Person getPersonById(int id);
	void savePerson(Person person);
	List<Person> getAllPersons();
	
}
