package md.spring.implement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import md.spring.interfaces.StudentDao;
import md.spring.model.Discipline;
import md.spring.model.DisciplineAverage;
import md.spring.model.Group;
import md.spring.model.Person;
import md.spring.model.PhoneType;
import md.spring.model.SearchData;
import md.spring.model.Student;
@Repository("studentDaoImplements")
public class StudentDaoImplements implements StudentDao{

//	private NamedParameterJdbcTemplate jdbcTemplate;
//	@Autowired
//	public PersonDaoImplements(DataSource dataSource) {
//		super();
//		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//	}
	private static final Logger logger = LoggerFactory.getLogger(StudentDaoImplements.class);
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
	
	public void deleteStudent(int id) {		
		currentSession().delete(getStudentById(id));
		logger.info("Person had been removed.");
		
	}
	public Student getStudentById(int id) {
		Query query = currentSession().createQuery("Select distinct p From Student as p Inner Join Fetch p.disciplineList where p.id = :id");
		query.setParameter("id", id);
		return (Student) query.uniqueResult();
	}
	public Student getStudentDetailsById(int id) {
		Query query = currentSession().createQuery("Select distinct p From Student as p where p.id = :id");
		query.setParameter("id", id);
		return (Student) query.uniqueResult();
	}

	public void saveStudent(Student student) {
		if(student.getId() != 0){
			currentSession().update(student);
		}else{
			currentSession().save(student);
		}
		
	}	
	public List<Student> getAllStudents() {
		Query query = currentSession().createQuery("Select distinct p From Student as p ");
//		  Inner Join FETCH p.listPhones as pl Inner Join FETCH pl.phoneType Left Join FETCH p.abonament as a
		return query.list();
	}
	public List<PhoneType> getAllPhoneTypes() {
		Criteria cr = currentSession().createCriteria(PhoneType.class);
//		Query query = currentSession().createQuery("Select p From PhoneType as p");
		return cr.list();
	}
	public List<Group> getAllGroups() {
		 Query query = currentSession().createQuery("Select g From Group as g");
		return query.list();
	}
	public List<Student> getAllStudentsByCriterias(SearchData searchData) {
		Criteria criteria = currentSession().createCriteria(Student.class, "s");
		if(searchData.getName() != null){
			criteria.add(Restrictions.or(Restrictions.like("s.lastName", "%" + searchData.getName() + "%"), Restrictions.like("s.firstName",  "%"+ searchData.getName() + "%")));
		}
		if(searchData.getGroup() != 0){
			criteria.add(Restrictions.eq("s.grup.id", searchData.getGroup()));
		}
		if(searchData.getGender() != null){
			criteria.add(Restrictions.like("s.gender", searchData.getGender()));
		}
		if(searchData.getStartDob() != null){
			criteria.add(Restrictions.gt("s.dob", searchData.getStartDob()));
		}
		if(searchData.getEndDob() != null){
			criteria.add(Restrictions.le("s.dob", searchData.getEndDob()));
		}
		if(searchData.getAddress() != null){
			criteria.createAlias("s.address", "a");
			criteria.add(Restrictions.or(
				Restrictions.like("a.street", "%" + searchData.getAddress() + "%"),
				Restrictions.like("a.city", "%" + searchData.getAddress() + "%"),
				Restrictions.like("a.country", "%" + searchData.getAddress() + "%")
			));			
		}
//		if(searchData.getDisciplineAvg() != 0){
//			DetachedCriteria subCriteria1 = DetachedCriteria.forClass(DisciplineAverage.class, "dAvg");
//			subCriteria1.setProjection(Projections.rowCount());
//			subCriteria1.add(Restrictions.eq("dAvg.student", "s.id"));
//			subCriteria1.add(Restrictions.gt("dAvg.averageMark", searchData.getDisciplineAvg()));
//			criteria.add(Subqueries.exists(subCriteria1));
			
//			criteria.createAlias("s.averageList", "avg");
//			criteria.add(Restrictions.gt("avg.averageMark", searchData.getDisciplineAvg()));
//		}
//		if(searchData.getDiscipline() != 0){
//			DetachedCriteria subCriteria = DetachedCriteria.forClass(Discipline.class, "d");
//			subCriteria.setProjection(Projections.id());
//			System.out.println(searchData.getDiscipline());
//			subCriteria.add(Restrictions.eq("d.id", searchData.getDiscipline()));
//			criteria.add(Subqueries.in("s.disciplineList", subCriteria));
//			if(searchData.getDisciplineAvg() != 0){
//				DetachedCriteria subCriteria1 = DetachedCriteria.forClass(DisciplineAverage.class, "dAvg");
//				subCriteria1.setProjection(Projections.rowCount());
//				subCriteria1.add(Restrictions.eq("dAvg.averageMark.id", searchData.getDisciplineAvg()));
//				criteria.add(Subqueries.lt(1, subCriteria1));
//			}
//		}		
		return criteria.list();
	}


	
//	public int insert(Person person) {		
//		String sql = "insert into persons (lastName, firstName, city, Address) values (:lastName, :firstName, :city, :address);";	
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		param.addValue("lastName", person.getLastName());
//		param.addValue("firstName", person.getFirstName());
//		param.addValue("city", person.getCity());
//		param.addValue("address", person.getAddress());
//		jdbcTemplate.update(sql, param, keyHolder);
//		Map<String, Object> keys = keyHolder.getKeys();
//		
//		return (Integer) keys.get("id");
//	}

//	public void delete(Person person) {
//		StringBuilder sql = new StringBuilder("delete from persons where id = :id");
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		param.addValue("id", person.getId());
//		jdbcTemplate.update(sql.toString(), param);
//		
//	}
//
//	public Person getPersonByID(int id) {		
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		param.addValue("id", id);
//		Person newPerson =  (Person) jdbcTemplate.queryForObject("select * from persons where id=:id", param, new PersonRowMapper());
//		return newPerson;
//	}
//	
//	public void updatePerson(Person person) {
//		StringBuilder sql = new StringBuilder("update persons set firstName = :firstName where id = :id");
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		param.addValue("firstName", person.getFirstName());
//		param.addValue("id", person.getId());
//		jdbcTemplate.update(sql.toString(), param);
//		
//	}
//	
//
//	public List<Person> getPersonListByName(String name) {
//		String sql = "select * from persons where firstName like :name";
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		param.addValue("name", name);
//		return jdbcTemplate.query(sql, param, new PersonRowMapper());
//	}
//
//	public List<Person> getPersonListByAddress(String address) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public Map<String, Integer> getStatisticByCity() {
//		String sql ="select city, count(firstName)  from persons group by city";
//		return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>(){
//			public Map<String, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
//				Map<String, Integer> map = new HashMap();
//				while(rs.next()){
//					String city = rs.getString(1);
//					int count = rs.getInt(2);
//					map.put(city, count);
//				}
//				return map;
//			}
//		});
//		
//	}




}

