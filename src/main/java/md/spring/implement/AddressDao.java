package md.spring.implement;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import md.spring.model.Address;

@Repository
public class AddressDao {
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
//	public void update(Address address) {
//		Query query = currentSession().createQuery("update address set street = :street, city = :city, country = :country where id = :id");
//		query.setParameter("street", address.getStreet());
//		query.setParameter("city", address.getCity());
//		query.setParameter("country", address.getCountry());
//		query.setParameter("id", address.getId());
//		query.executeUpdate();
//	}
//	public int save(Address address) {
//		Query query = currentSession().createQuery("insert into address (street, city, country) select :street, :city, country from address");
//		query.setParameter("street", address.getStreet());
//		query.setParameter("city", address.getCity());
//		query.setParameter("country", address.getCountry());
//		query.executeUpdate();
//		return (Integer) currentSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult();
//	}
}
