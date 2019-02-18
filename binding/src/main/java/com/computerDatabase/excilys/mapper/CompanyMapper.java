package com.computerDatabase.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.excilys.model.Company;

@Component
public class CompanyMapper {

	@Autowired
	public CompanyMapper() {
	}


	public List<Company> mapCompanies(ResultSet rs) throws SQLException {
		List<Company> companies = new ArrayList<Company>();
		while (rs.next()) {
			Company company = new Company.CompanyBuilder(rs.getLong("id")).name(rs.getString("name")).build();
			companies.add(company);
		}

		return companies;
	}

	public Company mapCompany(ResultSet rs) throws SQLException {
		return new Company.CompanyBuilder(rs.getLong("id")).name(rs.getString("name")).build();
	}
}
