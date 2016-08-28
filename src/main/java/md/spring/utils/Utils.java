package md.spring.utils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;

public class Utils {
	
	 public DriverManagerDataSource getConnection(){
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
	     dataSource.setDriverClassName("org.postgresql.Driver");	      
	     dataSource.setUsername("postgres");
	     dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
	     dataSource.setPassword("postgre");
	     return dataSource;
	 }

}
