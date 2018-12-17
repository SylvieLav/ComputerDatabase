package com.computerDatabase.excilys.mapper;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.computerDatabase.excilys.model.Company;
import com.computerDatabase.excilys.model.Computer;

public class ComputerMapper {

	public ComputerMapper() {
	}

	public Computer mapComputer(ResultSet rs) throws SQLException {
		Computer computer = null;
		Company company = new Company.CompanyBuilder(rs.getLong("company_id")).name(rs.getString("company.name")).build();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		// DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime introduced = null, discontinued = null;
		if (rs.getString("introduced") != null) {
			introduced = LocalDateTime.parse(rs.getString("introduced"), fmt);
		}
		if (rs.getString("discontinued") != null) {
			discontinued = LocalDateTime.parse(rs.getString("discontinued"), fmt);
		}
		computer = new Computer.ComputerBuilder(rs.getString("computer.name")).id(rs.getLong("id")).introduced(introduced).discontinued(discontinued)
				.company(company).build();

		return computer;
	}

}
