package ru.javabegin.training.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import ru.javabegin.training.model.Student;

@ControllerAdvice
public class GlobalExceptions {
	
	private static final Logger logg = LoggerFactory.getLogger(GlobalExceptions.class);

	@ExceptionHandler(value={NotFoundDataException.class})
	public ModelAndView handleNotFoundData(NotFoundDataException ex){
		ModelAndView model = new ModelAndView();
		model.setViewName("error");
		model.addObject("error", ex.getMessage());	
		return model;
	}
	
	@ExceptionHandler(value={NullDataException.class})
	public ModelAndView handleNullData(NullDataException ex){
		ModelAndView model = new ModelAndView();
		model.setViewName("error");
		model.addObject("error", ex.getMessage());	
		return model;
	}
}
