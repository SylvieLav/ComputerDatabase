package com.computerDatabase.excilys.exception;

import org.slf4j.*;

public class CompanyIdException extends Exception {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyIdException.class);
	
	private static final long serialVersionUID = 1L;

	public CompanyIdException(String sCompanyId) {
		LOGGER.error("Cannot parse the company ID " + sCompanyId);
	}

}
