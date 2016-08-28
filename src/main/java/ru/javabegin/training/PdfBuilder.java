package ru.javabegin.training;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import md.spring.model.DisciplineAverage;
import md.spring.model.Phone;
import md.spring.model.STATUS;
import md.spring.model.Student;

public class PdfBuilder extends AbstractTextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		List<Student> listStudents = (List<Student>) model.get("students");
		
		Font fontTitle = FontFactory.getFont(FontFactory.COURIER_OBLIQUE);
        fontTitle.setColor(BaseColor.BLACK);
        fontTitle.setSize(20);
        fontTitle.setStyle(Font.BOLD);

        document.add(new Paragraph("Student list \n \n", fontTitle));
		
        PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] {2.0f, 1.5f, 1.9f, 1.4f, 2.0f,  2.0f, 2.4f, 2.5f});
		table.setSpacingBefore(4);
         
		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.BLACK);
	    font.setSize(12); 
	    
	    Font fontCell = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontCell.setSize(10);
		
		// define table header cell
		PdfPCell cell = new PdfPCell();		
		cell.setPadding(5);
		
		// alignment
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
         
		// write table header
		cell.setPhrase(new Phrase("Foto", font));
		table.addCell(cell);
 
		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);
         
		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Date of Birth", font));
		table.addCell(cell);
 
		cell.setPhrase(new Phrase("Abonament", font));
		table.addCell(cell);
         
		cell.setPhrase(new Phrase("Address", font));
		table.addCell(cell);
         
		cell.setPhrase(new Phrase("Phone(s)", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Mark(s)", font));
		table.addCell(cell);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		for(Student student : listStudents){
			table.addCell(Image.getInstance(student.getImage()));
			table.addCell(new Phrase(student.getFirstName()+" "+student.getLastName(), fontCell));
			table.addCell(new Phrase(student.getGender().toString(), fontCell));
			table.addCell(new Phrase((dateFormat).format(student.getDob()), fontCell));
			String abonament = STATUS.NONE.toString();
			if(student.getAbonament() != null) {
	               abonament = student.getAbonament().getStatus().toString();
	               if(student.getAbonament().getStatus().equals(STATUS.ACTIVE)){
	                   abonament = abonament + "\n" + "From: " + dateFormat.format(student.getAbonament().getStartDate()) + "\nTo: " + dateFormat.format(student.getAbonament().getEndDate());
	               }
	               
			}	
			table.addCell(new Phrase(abonament,fontCell));
			table.addCell(new Phrase(student.getAddress().getCountry() + " " + "or. " + student.getAddress().getCity() + " " + "str. " + student.getAddress().getStreet(), fontCell));
			String phones = null;
	        for(Phone phone: student.getListPhones()){
	        	if(phones == null){
	        		phones = phone.getPhoneType().getType() + ": " + phone.getPhone() + "\n";
	        	}else{
	        		phones += phone.getPhoneType().getType() + ": " + phone.getPhone() + "\n";
	        	}	        	
	        }
	        table.addCell(new Phrase(phones, fontCell));            
            String marks = "This student does not have marks";
            if(student.getAverageList() != null) {
                for (DisciplineAverage average : student.getAverageList()) {
                	if(marks.equals("This student does not have marks")){
                		marks = average.getDiscipline().getTitle() + ": " ;
                        marks += average.getAverageMark() + "\n";
                	}else{
                		marks += average.getDiscipline().getTitle() + ": " ;
                        marks += average.getAverageMark() + "\n";
                	}
                }
            }
            table.addCell(new Phrase(marks, fontCell));
		}
			
		document.add(table);
	}
		

}
