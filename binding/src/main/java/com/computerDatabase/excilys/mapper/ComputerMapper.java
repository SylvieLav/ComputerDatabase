package com.computerDatabase.excilys.mapper;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import com.computerDatabase.excilys.dto.ComputerDTO;
import com.computerDatabase.excilys.model.*;

public class ComputerMapper {

	@Autowired
	public ComputerMapper() {}

	public Computer mapComputer(ResultSet rs) throws SQLException {
		LocalDateTime timeIntroduced = null;
		LocalDateTime timeDiscontinued = null;

		String introduced = rs.getString("introduced");
		if (introduced != null) {
			introduced = introduced.replace(' ', 'T');
			timeIntroduced = LocalDateTime.parse(introduced, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		}

		String discontinued = rs.getString("discontinued");
		if (discontinued != null) {
			discontinued = discontinued.replace(' ', 'T');
			timeDiscontinued = LocalDateTime.parse(discontinued, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		}

		Company company = new Company(rs.getLong("company_id"), rs.getString("companyName"));
		
		return new Computer(rs.getInt("compuId"),rs.getString("compuName"),timeIntroduced,timeDiscontinued,company);
	}
	
	public ComputerDTO createDTO(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO(computer);

		computerDTO.setId(computer.getId());
		computerDTO.setName(computer.getName());
		computerDTO.setCompanyName(computer.getCompany().getName());
		computerDTO.setIntroduced(computer.getIntroduced());
		computerDTO.setDiscontinued(computer.getDiscontinued());

		return computerDTO;
	}

//	public Computer createBean(ComputerDTO computerDTO) {
//		return new Computer.ComputerBuilder(computerDTO.getName()).id(computerDTO.getId()).build();
//	}
}