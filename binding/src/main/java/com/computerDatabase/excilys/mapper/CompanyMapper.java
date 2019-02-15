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

	public List<Company> mapCompanies(ResultSet listCompaniesDb) throws SQLException {
		List<Company> companies = new ArrayList<>();

		while (listCompaniesDb.next()) {
			Company company = mapCompany(listCompaniesDb);
			companies.add(company);
		}

		return companies;
	}
	
	public Company mapCompany(ResultSet companyDb) throws SQLException {
		return new Company(companyDb.getLong("id"),companyDb.getString("name"));
	}
}
