package com.computerDatabase.excilys.validator;

import org.springframework.stereotype.Component;

import com.computerDatabase.excilys.exception.CompanyIdException;

@Component
public class CompanyValidator {
	
	private CompanyValidator() {}
	
	private CompanyIdException validateCompanyId(String sCompanyId) {
		if (!"".equals(sCompanyId)) {
			try {
				Long.parseLong(sCompanyId);
			} catch (NumberFormatException e) {
				return new CompanyIdException(sCompanyId);
			}
		}
		
		return null;
	}
	
	public boolean validateAll(String sCompanyId) {
		return validateCompanyId(sCompanyId) == null;
	}
}
