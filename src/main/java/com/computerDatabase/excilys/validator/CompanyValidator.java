package com.computerDatabase.excilys.validator;

import org.slf4j.*;

import com.computerDatabase.excilys.model.Company;

public class CompanyValidator {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyValidator.class);
	
	private CompanyValidator() {};
	
	private boolean validateName(String name) {
		if ("".equals(name)) {
			LOGGER.error("The name of the company cannot be empty !");
			return false;
		}

		return true;
	}
	
	public boolean validateAll(Company company) {
		return validateName(company.getName());
	}
}
