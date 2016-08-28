package md.spring.implement;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import md.spring.model.Phone;
@Repository
public class PhoneDao{
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
	public void update(Phone phone) {
		Query query = currentSession().createQuery("update phone set phone = :phone, type_id = :type_id where id = :id");
		query.setParameter("phone", phone.getPhone());
		query.setParameter("type_id", phone.getPhoneType().getId());
		query.setParameter("id", phone.getId());
		query.executeUpdate();
	}
	public int save(Phone phone) {
		Query query = currentSession().createQuery("insert into phone (phone, type_id) select :phone, :type_id from phone");
		query.setParameter("phone", phone.getPhone());
		query.setParameter("type_id", phone.getPhoneType().getId());
		query.executeUpdate();
		return (Integer) currentSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult();
	}

}
