package com.computerDatabase.excilys.cli;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerDatabase.excilys.model.Company;
import com.computerDatabase.excilys.service.CompanyService;

public class CompanyCli {
	private CompanyService companyService;
	private static final CompanyCli INSTANCE = new CompanyCli();
	
	public static CompanyCli getInstance() {
		return INSTANCE;
	}
	
	public void listCli() {
		Logger logger = LoggerFactory.getLogger(CompanyCli.class);
		List<Company> companies = companyService.listService();
		for (Company company : companies) {
			logger.info(company.getName());
		}
	}
}
