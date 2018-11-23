package com.computerDatabase.excilys.service;

import java.util.List;

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
		List<Company> companies = companyDAO.listCompanies();
		
		return companies;
	}
}
