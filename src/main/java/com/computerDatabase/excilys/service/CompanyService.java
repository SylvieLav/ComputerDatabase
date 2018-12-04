package com.computerDatabase.excilys.service;

import java.util.List;
import java.util.Optional;

import com.computerDatabase.excilys.dao.CompanyDAO;
import com.computerDatabase.excilys.model.Company;

public class CompanyService {
	
	private CompanyDAO companyDAO = CompanyDAO.getInstance();
	
	private CompanyService() {}
	
	private static final CompanyService INSTANCE = new CompanyService();
	
	public static CompanyService getInstance() {
		return INSTANCE;
	}
	
	public List<Company> listService() {
		List<Company> companies = companyDAO.list();
		
		return companies;
	}
	
	public Optional<Company> getCompanyById(long id) {
		Optional<Company> company= companyDAO.getCompanyById(id);
		
		return company;
	}
}
