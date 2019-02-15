package com.computerDatabase.excilys.mapper;

import org.springframework.stereotype.Component;

import com.computerDatabase.excilys.dto.ComputerDTO;
import com.computerDatabase.excilys.model.Computer;

@Component
public class ComputerDTOMapper {
	
	private ComputerDTOMapper() {}
	
	public ComputerDTO map(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO(computer);
		computerDTO.setId(computer.getId());
		computerDTO.setIntroduced(computer.getIntroduced());
		computerDTO.setDiscontinued(computer.getDiscontinued());
		computerDTO.setName(computer.getName());
		computerDTO.setCompanyName(computer.getCompany().getName());

		return computerDTO;
	}
}