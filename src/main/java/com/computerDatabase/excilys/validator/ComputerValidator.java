package com.computerDatabase.excilys.validator;

import java.time.LocalDateTime;

import org.slf4j.*;

import com.computerDatabase.excilys.model.Computer;

public class ComputerValidator {
	private static final ComputerValidator INSTANCE = new ComputerValidator();
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerValidator.class);
	
	private ComputerValidator() {}
	
	public static ComputerValidator getInstance() {
		return INSTANCE;
	}
	
	private boolean validateName(String name) {
		if ("".equals(name)) {
			LOGGER.error("The name of the computer cannot be empty !");
			return false;
		}
		
		return true;
	}
	
	private boolean validateComputerDate(LocalDateTime introduced, LocalDateTime discontinued) {
		
		if (introduced != null && discontinued != null && introduced.compareTo(discontinued)>=0) {
			LOGGER.error("The discontinued date must be after the introduced date !");
			return false;
		}
		
		return true;
	}
	
	public boolean validateAll(Computer computer) {
		
		if (validateName(computer.getName()) == false || validateComputerDate(computer.getIntroduced(), computer.getDiscontinued()) == false) {
			return false;
		}
		
		return true;
	}
}
