package ru.javabegin.training;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import md.spring.model.Discipline;
import md.spring.model.LibraryAbonament;
import md.spring.model.Mark;
import md.spring.model.Proffessor;
import md.spring.model.SearchData;
import md.spring.model.Student;
import md.spring.service.Service;

@Controller
public class HomeController {
	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private Service service;	
	@Autowired
	public void setService(Service service) {		
		this.service = service;
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("searchData", new SearchData());
		model.addAttribute("groups", service.getAllGroups());
		model.addAttribute("disciplineList", service.getAllDiscipline());
		model.addAttribute("students", service.getAllStudents());		
		return "home";
	}
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String search(@ModelAttribute("searchData")SearchData searchData, Locale locale, Model model) {
		System.out.println(searchData.getName() + searchData.getDiscipline() + searchData.getGender());
		model.addAttribute("searchData", searchData);
		model.addAttribute("groups", service.getAllGroups());
		model.addAttribute("disciplineList", service.getAllDiscipline());		
		model.addAttribute("students", service.getAllStudentsByCriterias(searchData));		
		return "home";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String homePost(@RequestParam(value="id", required=false) int id, Model model) {
		if(id != 0){			
			model.addAttribute("student", service.getStudentDetailsById(id));
		}else{			
			model.addAttribute("student", new Student());
		}
		model.addAttribute("grups", service.getAllGroups());
		model.addAttribute("phonetype", service.getAllPhoneTypes());		
		return "edit";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String addUpdate(@Valid Student student, BindingResult bindingResult, Model model, @RequestParam(value="img", required=true) MultipartFile file) throws IOException{
		if(bindingResult.hasErrors()){
			model.addAttribute("grups", service.getAllGroups());
			model.addAttribute("phonetype", service.getAllPhoneTypes());
			model.addAttribute("student", student);
			return "edit";
		}
		if(student.getId() != 0){
			if(file.getBytes().length != 0){									
				student.setImage(file.getBytes());
			}else{				
				student.setImage(service.getStudentDetailsById(student.getId()).getImage());
			}
		}else{			
			if(file.getBytes().length != 0){									
				student.setImage(file.getBytes());
			}else{				
				ClassLoader classLoader = getClass().getClassLoader();

				InputStream is = new FileInputStream(new File("C:\\Users\\admin\\Desktop\\secondProject\\src\\main\\webapp\\resources\\img\\noImg.png"));
				student.setImage(IOUtils.toByteArray(is));		        	
			}
		}			
		service.saveStudent(student);
		return "redirect:/";
	}
	@RequestMapping(value = "/abonament", method = RequestMethod.GET)
	public String abonamentGet(@RequestParam(value="abonamentId", required=false) int abonamentId, Model model) {
		LibraryAbonament abonament = service.getAbonamentByStudentId(abonamentId);
		if(abonament != null){
			model.addAttribute("abonament", abonament);
		}else{
			LibraryAbonament ab= new LibraryAbonament();			
			ab.setPerson(service.getStudentDetailsById(abonamentId));
			model.addAttribute("abonament", ab);
		}						
		return "abonament";
	}
	@RequestMapping(value = "/abonament", method = RequestMethod.POST)
	public String addUpdate(@Valid LibraryAbonament abonament, BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors()){
			return "abonament";
		}
		service.saveAbonament(abonament);
		return "redirect:/";
	}
	@RequestMapping(value = "/mark", method = RequestMethod.GET)
	public String markGet(@RequestParam(value="stId", required=false) int stId, Model model) {
		Mark mark= new Mark();		
		mark.setStudent(service.getStudentById(stId));
		model.addAttribute("mark", mark);							
		return "mark";
	}
	@RequestMapping(value="/getProffessors", method=RequestMethod.GET)
	@ResponseBody
	public Discipline getProffesssors(@RequestParam("disciplineId") int id){
		Discipline discipline = service.getDisciplineById(id);
		for(Proffessor p : discipline.getProffessorList()){
			p.setAbonament(null);
			p.setAddress(null);
			p.setImage(null);
			p.setListPhones(null);
		}
		return discipline;		
	}
	@RequestMapping(value = "/mark", method = RequestMethod.POST)
	public String addMark(@Valid Mark mark, BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors()){
			return "mark";
		}
		service.saveMark(mark);
		return "redirect:/";
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value="id", required=false) int id, Model model) {
		service.deleteStudent(id);		
		return "redirect:/";
	}	
	@RequestMapping(method=RequestMethod.GET, value="image")
	public void showImage(@RequestParam(value="id", required=false) int id, HttpServletResponse response,HttpServletRequest request){
		response.setContentType("image/jpg");
		try {
			if(id == 0){
				InputStream is = new FileInputStream(new File("C:\\Users\\admin\\Desktop\\secondProject\\src\\main\\webapp\\resources\\img\\noImg.png"));				
				response.getOutputStream().write(IOUtils.toByteArray(is));
			}else{
				response.getOutputStream().write(service.getStudentDetailsById(id).getImage());
			}			
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@RequestMapping(method=RequestMethod.GET, value="downloadPdf")
	public ModelAndView download(HttpServletRequest request, HttpServletResponse response){
//		response.setContentType("application/pdf");
//		response.setHeader("Content-Disposition", "attachment; filename=studentList.pdf");
		return new ModelAndView("pdfView", "students", service.getAllStudents());
	}
	
}
