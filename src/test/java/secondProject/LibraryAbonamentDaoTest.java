package secondProject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.controller.NullDataException;
import ru.javabegin.training.model.LibraryAbonament;
import ru.javabegin.training.service.Service;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations={"servlet-context.xml"})
public class LibraryAbonamentDaoTest {

	@Autowired
	private Service service;
	
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}

	@Test(expected=NullDataException.class)
	@Rollback(true)
	public void saveAbonamentTest(){
		LibraryAbonament abonament = null;
		service.saveAbonament(abonament);
	}
}