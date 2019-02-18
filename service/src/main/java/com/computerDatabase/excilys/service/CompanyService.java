package com.computerDatabase.excilys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computerDatabase.excilys.dao.CompanyDAO;
import com.computerDatabase.excilys.model.Company;

@Service
public class CompanyService {
	@Autowired
	private CompanyDAO companyDAO;
	
	private CompanyService() {}

	public List<Company> list() {
		return companyDAO.list();
	}

	public Optional<Company> getCompanyById(long id) {
		return companyDAO.getCompanyById(id);
	}

	public long delete(long id) {
		companyDAO.delete(id);

		return id;
	}
}