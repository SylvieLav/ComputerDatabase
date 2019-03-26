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
		computerDTO.setName(computer.getName());
		if (computer.getIntroduced() != null)
			computerDTO.setIntroduced(computer.getIntroduced().toString());
		if (computer.getIntroduced() != null)
			computerDTO.setDiscontinued(computer.getDiscontinued().toString());
		if (computer.getCompany() != null)
			computerDTO.setCompanyId(String.valueOf(computer.getCompany().getId()));

		return computerDTO;
	}
}