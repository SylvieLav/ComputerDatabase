package com.computerDatabase.excilys.validator;

import java.time.LocalDateTime;
import java.time.format.*;

import org.slf4j.*;

public class ComputerValidator {
	private static final ComputerValidator INSTANCE = new ComputerValidator();
	
	private ComputerValidator() {}
	
	public static ComputerValidator getInstance() {
		return INSTANCE;
	}
	
	private boolean validateComputerId(String computerId) {
		Logger logger = LoggerFactory.getLogger(ComputerValidator.class);
		try {
			Long.parseLong(computerId);
		} catch (NumberFormatException e) {
			logger.error("Cannot parse the computer ID " + computerId);
			return false;
		}
		
		return true;
	}
	
	private boolean validateCompanyId(String sCompanyId) {
		Logger logger = LoggerFactory.getLogger(ComputerValidator.class);
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
	
	@SuppressWarnings("null")
	private boolean validateComputerDate(String sIntroduced, String sDiscontinued) {
		Logger logger = LoggerFactory.getLogger(ComputerValidator.class);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		LocalDateTime introduced = null, discontinued = null;
		if ((sIntroduced == null || sIntroduced.equals("")) && (sDiscontinued == null || sDiscontinued.equals(""))) {
			return true;
		}
		if (sIntroduced != null || sIntroduced.equals("")) {
			try {
				introduced = LocalDateTime.parse(sIntroduced, formatter);
			} catch (DateTimeParseException e) {
				System.out.println("Cannot parse the introduced date " + sIntroduced);
				return false;
			}
		}
		if (sDiscontinued != null || sDiscontinued.equals("")) {
			try {
				discontinued = LocalDateTime.parse(sDiscontinued, formatter);
			} catch (DateTimeParseException e) {
				logger.error("Cannot parse the discontinued date " + sDiscontinued);
				return false;
			}
		}
		if (!(sIntroduced == null || sIntroduced.equals("")) && !(sDiscontinued == null || sDiscontinued.equals("")) && introduced.compareTo(discontinued)>=0) {
			logger.error("The discontinued date must be after the introduced date !");
			return false;
		} else {
			return true;
		}
	}
	
	public boolean validateAll(String introduced, String discontinued, String companyId) {
		if (validateComputerId(companyId) == true && validateCompanyId(companyId) == true && validateComputerDate(introduced, discontinued) == true) {
			return true;
		}
		
		return false;
	}
}