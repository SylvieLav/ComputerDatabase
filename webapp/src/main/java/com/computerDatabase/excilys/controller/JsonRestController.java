package com.computerDatabase.excilys.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.computerDatabase.excilys.exception.ComputerIdException;
import com.computerDatabase.excilys.model.Computer;
import com.computerDatabase.excilys.service.ComputerService;
import com.computerDatabase.excilys.validator.ComputerValidator;

@RestController
public class JsonRestController {

	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerValidator computerValidator;
	
	@GetMapping("/dashboard-rest")
	@ResponseStatus(HttpStatus.OK)
	public List<Computer> listComputers() {
		return computerService.list(-1, -1, "name", "ASC");
	}
	
	@GetMapping("/getComputer-rest/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Computer getComputerDetails(@PathVariable("id") Long id) throws ComputerIdException {
		Optional<Computer> computer = computerService.listDetails(id);
		if (computer.isPresent()) {
			return computer.get();
		}
		throw new ComputerIdException(id.toString());
	}
	
	@PostMapping("/addComputer-rest")
	@ResponseStatus(HttpStatus.CREATED)
	public Computer addComputer(@RequestBody Computer computer) {
		if (computerValidator.validateAll(computer.getIntroduced().toString(), computer.getDiscontinued().toString()))
			return computerService.create(computer);
		
		return null;
	}
	
	@PutMapping("/editComputer-rest/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Computer updateComputer(@PathVariable("computerId") Long computerId, @RequestBody Computer computer) {
		if (computerValidator.validateAll(computer.getIntroduced().toString(), computer.getDiscontinued().toString()))
			return computerService.update(computer);
		
		return null;
	}
	
	@DeleteMapping("/deleteComputer-rest/{id}")
	@ResponseStatus(HttpStatus.OK)
	public long deleteComputer(@PathVariable Long id) {
		return computerService.delete(id);
	}
	
}
