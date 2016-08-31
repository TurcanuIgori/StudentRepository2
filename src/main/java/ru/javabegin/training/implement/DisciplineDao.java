package ru.javabegin.training.implement;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ru.javabegin.training.model.Discipline;
import ru.javabegin.training.model.Student;

@Repository
public class DisciplineDao {

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
	public Discipline getDisciplineById(int id){
		Query query = currentSession().createQuery("Select distinct d From Discipline as d where d.id = :id");
		query.setParameter("id", id);
		return (Discipline) query.uniqueResult();
	}
	public List<Discipline> getAllDiscipline() {
		Query query = currentSession().createQuery("Select distinct d From Discipline as d");
		return query.list();		
	}
}
