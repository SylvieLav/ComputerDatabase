package com.computerDatabase.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.computerDatabase.excilys.model.Company;

public class CompanyMapper {
	
	public CompanyMapper() {
	}
	
	public List<Company> setCompanies(ResultSet rs) {
		List<Company> companies = new ArrayList<Company>();
		try {
			while (rs.next()) {
				Company company = new Company.CompanyBuilder(rs.getLong("id")).name(rs.getString("name")).build();
				companies.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	return companies;
	}
}
