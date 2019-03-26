package com.computerDatabase.excilys.dto;

import com.computerDatabase.excilys.model.Computer;

public class ComputerDTO {
	private long id;
	private String companyId;
	private String discontinued;
	private String introduced;
	private String name;
	
	public ComputerDTO() {}
	
	public ComputerDTO(String name, String introduced, String discontinued, String companyId) {
		this.companyId = companyId;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.name = name;
	}

	public ComputerDTO(Computer computer) {
		this.id = computer.getId();
		this.name = computer.getName();
		if (computer.getIntroduced() != null)
			this.introduced = computer.getIntroduced().toString();
		if (computer.getDiscontinued() != null)
			this.discontinued = computer.getDiscontinued().toString();
		if (computer.getCompany() != null)
			this.companyId = String.valueOf(computer.getCompany().getId());
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
}
