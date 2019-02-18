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
		Company company = new Company.CompanyBuilder(rs.getLong("company_id")).name(rs.getString("company.name")).build();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime introduced = null, discontinued = null;
		if (rs.getString("introduced") != null) {
			introduced = LocalDateTime.parse(rs.getString("introduced"), fmt);
		}
		if (rs.getString("discontinued") != null) {
			discontinued = LocalDateTime.parse(rs.getString("discontinued"), fmt);
		}

		return new Computer.ComputerBuilder(rs.getString("computer.name")).id(rs.getLong("computer.id")).introduced(introduced).discontinued(discontinued)
				.company(company).build();
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

	public Computer createBean(ComputerDTO computerDTO) {
		return new Computer.ComputerBuilder(computerDTO.getName()).id(computerDTO.getId()).build();
	}
}