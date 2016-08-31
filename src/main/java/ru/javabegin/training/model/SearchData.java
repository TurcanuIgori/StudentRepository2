package ru.javabegin.training.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SearchData {
	private String name;
	private String address;
	private int group;
	private GENDER gender;	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDob;	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDob;
	private int discipline;
	private double disciplineAvg;
	private double totalAvg;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}	
	public GENDER getGender() {
		return gender;
	}
	public void setGender(GENDER gender) {
		this.gender = gender;
	}
	public Date getStartDob() {
		return startDob;
	}
	public void setStartDob(Date startDob) {
		this.startDob = startDob;
	}
	public Date getEndDob() {
		return endDob;
	}
	public void setEndDob(Date endDob) {
		this.endDob = endDob;
	}
	public int getDiscipline() {
		return discipline;
	}
	public void setDiscipline(int discipline) {
		this.discipline = discipline;
	}
	public double getDisciplineAvg() {
		return disciplineAvg;
	}
	public void setDisciplineAvg(double disciplineAvg) {
		this.disciplineAvg = disciplineAvg;
	}
	public double getTotalAvg() {
		return totalAvg;
	}
	public void setTotalAvg(double totalAvg) {
		this.totalAvg = totalAvg;
	}	
}
