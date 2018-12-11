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

	private CompanyService() {
	}

	public List<Company> listService() {
		List<Company> companies = companyDAO.list();

		return companies;
	}

	public Optional<Company> getCompanyById(long id) {
		Optional<Company> company = companyDAO.getCompanyById(id);

		return company;
	}

	public long deleteService(long id) {
		companyDAO.delete(id);

		return id;
	}
}
