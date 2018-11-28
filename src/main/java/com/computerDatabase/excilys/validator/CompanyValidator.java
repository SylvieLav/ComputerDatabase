package com.computerDatabase.excilys.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyValidator {
	private static final CompanyValidator INSTANCE = new CompanyValidator();
	
	private CompanyValidator() {}
	
	public static CompanyValidator getInstance() {
		return INSTANCE;
	}
	
	private boolean validateCompanyId(String sCompanyId) {
		Logger logger = LoggerFactory.getLogger(CompanyValidator.class);
		if (!(sCompanyId.equals(null) || sCompanyId.equals(""))) {
			try {
				Long.parseLong(sCompanyId);
			} catch (NumberFormatException e) {
				logger.error("Cannot parse the company ID " + sCompanyId);
				return false;
			}
		}
		
		return true;
	}
	
	public void validateAll(String sCompanyId) {
		validateCompanyId(sCompanyId);
	}
}
