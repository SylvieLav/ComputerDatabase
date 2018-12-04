package com.computerDatabase.excilys.dto;

import java.time.LocalDateTime;

import com.computerDatabase.excilys.model.Company;

public class ComputerDTO {
	private LocalDateTime	introduced;
	private LocalDateTime	discontinued;
	private long			id;
	private String			companyName;
	private String			name;
	
	public ComputerDTO(long id, String name) {
		this.id = id;
		this.name = name;
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
