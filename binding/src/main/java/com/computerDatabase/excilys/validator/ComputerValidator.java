package com.computerDatabase.excilys.validator;

import java.time.LocalDateTime;
import java.time.format.*;

import org.springframework.stereotype.Component;

import com.computerDatabase.excilys.exception.*;

@Component
public class ComputerValidator {
	
	private ComputerValidator() {}
	
	private ComputerDateException validateComputerDate(String sIntroduced, String sDiscontinued) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime introduced = null, discontinued = null;
		if ("".equals(sIntroduced) && "".equals(sDiscontinued))
			return null;
		
		if (!"".equals(sIntroduced)) {
			try {
				introduced = LocalDateTime.parse(sIntroduced + " 00:00", formatter);
			} catch (DateTimeParseException e) {
				return new ComputerDateException(sIntroduced);
			}
		}
		
		if (!"".equals(sDiscontinued)) {
			try {
				discontinued = LocalDateTime.parse(sDiscontinued + " 00:00", formatter);
			} catch (DateTimeParseException e) {
				return new ComputerDateException(sDiscontinued);
			}
		}
		
		if (sIntroduced != null && sDiscontinued != null && introduced.compareTo(discontinued)>=0)
			return new ComputerDateException(sIntroduced, sDiscontinued);
		else
			return null;
	}
	
	public boolean validateAll(String introduced, String discontinued) {
		return validateComputerDate(introduced, discontinued) == null;
	}
}
