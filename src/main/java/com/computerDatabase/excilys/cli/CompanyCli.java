package com.computerDatabase.excilys.cli;

import java.util.List;

import org.slf4j.*;
import org.springframework.stereotype.Component;

import com.computerDatabase.excilys.model.Company;
import com.computerDatabase.excilys.service.CompanyService;

@Component
public class CompanyCli {
	
	private CompanyService companyService;
	
	public void listCli() {
		Logger logger = LoggerFactory.getLogger(CompanyCli.class);
		List<Company> companies = companyService.list();
		for (Company company : companies) {
			logger.info(company.getName());
		}
	}
	
	public void deleteCli(String id) {
		companyService.delete(Long.parseLong(id));
	}
}