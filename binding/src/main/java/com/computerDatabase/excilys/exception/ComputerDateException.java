package com.computerDatabase.excilys.exception;

import org.slf4j.*;

public class ComputerDateException extends Exception {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDateException.class);
	private static final long serialVersionUID = 1L;

	public ComputerDateException(String sDate) {
		LOGGER.error("Cannot parse the date " + sDate);
	}
	
	public ComputerDateException(String sIntroduced, String sDiscontinued) {
		LOGGER.error("The discontinued date " + sDiscontinued + " must be after the introduced date " + sIntroduced);
	}

}
