package ru.javabegin.training.implement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ru.javabegin.training.model.Mark;

@Repository
public class MarkDao {
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
	public void saveMark(Mark mark) {
		 currentSession().save(mark);
		
	}
	
}
