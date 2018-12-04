package com.computerDatabase.excilys.mapper;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.computerDatabase.excilys.model.Company;
import com.computerDatabase.excilys.model.Computer;

public class ComputerMapper {
	
	public ComputerMapper() {
	}
	
	public List<Computer> mapComputers(ResultSet rs) throws SQLException {
		List<Computer> computers = new ArrayList<Computer>();
		while (rs.next()) {
			Company company = new Company.CompanyBuilder(rs.getLong("company_id")).build();
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			//DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			LocalDateTime introduced = null, discontinued = null;
			if (rs.getString("introduced") != null) {
				introduced = LocalDateTime.parse(rs.getString("introduced"), fmt);
			}
			if (rs.getString("discontinued") != null) {
				discontinued = LocalDateTime.parse(rs.getString("discontinued"), fmt);
			}
			Computer computer = new Computer.ComputerBuilder(rs.getString("name")).id(rs.getLong("id")).introduced(introduced).discontinued(discontinued).company(company).build();
				
			computers.add(computer);
		}
	
	return computers;
	}

}
