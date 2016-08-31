package ru.javabegin.training.implement;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ru.javabegin.training.model.LibraryAbonament;
import ru.javabegin.training.model.STATUS;
import ru.javabegin.training.model.Student;
import sun.util.logging.resources.logging;

@Repository
public class LibraryAbonamentDao {
	
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
	
	public LibraryAbonament getAbonamentByStudentId(int id){
		Query query = currentSession().createQuery("Select distinct a From LibraryAbonament as a Inner Join Fetch a.person where a.person.id = :id");
		query.setParameter("id", id);
		return (LibraryAbonament) query.uniqueResult();
	}
	
	public void saveAbonament(LibraryAbonament abonament){		
		if(abonament.getStatus()==STATUS.SUSPENDED || abonament.getStatus()==STATUS.NONE){
            abonament.setStartDate(null);
            abonament.setEndDate(null);
        }
        if(abonament.getId() == 0){
           currentSession().save(abonament);
        }else {
        	currentSession().update(abonament);
        }
	}
}
