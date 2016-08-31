package ru.javabegin.training.service;

import java.util.List;

import ru.javabegin.training.model.Person;

public interface ServicePerson {
	void updatePerson(Person person);
	void deletePerson(int id);
	Person getPersonById(int id);
	void savePerson(Person person);
	List<Person> getAllPersons();
	
}
