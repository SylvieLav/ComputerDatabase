package com.computerDatabase.excilys.dto;

import java.time.LocalDateTime;

import com.computerDatabase.excilys.model.Computer;

public class ComputerDTO {
	private LocalDateTime introduced;
	private LocalDateTime discontinued;
	private long		  id;
	private String		  companyName;
	private String		  name;
	
	public ComputerDTO(Computer computer) {
		this.id = computer.getId();
		this.name = computer.getName();
		this.introduced = computer.getIntroduced();
		this.discontinued = computer.getDiscontinued();
		this.companyName = computer.getCompany().getName();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public LocalDateTime getDiscontinued() {
		return discontinued;
	}
	
	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}
	
	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}
	
	public LocalDateTime getIntroduced() {
		return introduced;
	}
}
