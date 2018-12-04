package com.computerDatabase.excilys.mapper;

import com.computerDatabase.excilys.dto.ComputerDTO;
import com.computerDatabase.excilys.model.Computer;

public class ComputerDTOMapper {

	private ComputerDTOMapper() {
	}

	private static final ComputerDTOMapper INSTANCE = new ComputerDTOMapper();

	public static ComputerDTOMapper getInstance() {
		return INSTANCE;
	}
	
	public ComputerDTO map(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO(computer.getId(), computer.getName());
		computerDTO.setId(computer.getId());
		computerDTO.setIntroduced(computer.getIntroduced());
		computerDTO.setDiscontinued(computer.getDiscontinued());
		computerDTO.setName(computer.getName());
		if (computer.getCompany() != null) {
			computerDTO.setCompanyName(computer.getCompany().getName());
		} 
		return computerDTO;
	}
}