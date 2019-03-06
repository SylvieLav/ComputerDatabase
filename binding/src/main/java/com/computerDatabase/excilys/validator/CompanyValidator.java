package com.computerDatabase.excilys.validator;

import org.slf4j.*;

public class CompanyValidator {
	
	private CompanyValidator() {}
	
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
	
	public boolean validateAll(String sCompanyId) {
		return validateCompanyId(sCompanyId);
	}
}
