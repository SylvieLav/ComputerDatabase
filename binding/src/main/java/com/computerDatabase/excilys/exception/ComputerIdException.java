package com.computerDatabase.excilys.exception;

import org.slf4j.*;

public class ComputerIdException extends Exception {
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerIdException.class);
	
	private static final long serialVersionUID = 1L;

	public ComputerIdException(String sComputerId) {
		LOGGER.error("Cannot parse the computer ID " + sComputerId);
	}

}
