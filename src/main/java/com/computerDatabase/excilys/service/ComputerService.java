package com.computerDatabase.excilys.service;

import java.util.*;

import com.computerDatabase.excilys.dao.ComputerDAO;
import com.computerDatabase.excilys.model.Computer;

public class ComputerService {
	private ComputerDAO computerDAO = ComputerDAO.getInstance();
	
	private ComputerService() {}
	
	private static final ComputerService INSTANCE = new ComputerService();
	
	public static ComputerService getInstance() {
		return INSTANCE;
	}

	public Computer createService(Computer computer) {
		computerDAO.create(computer);
		
		return computer;
	}
	
	public List<Computer> listBySearchService(long number, long pageNumber, String filter) {
		List<Computer> computers = computerDAO.listBySearch(number, pageNumber, filter);
		
		return computers;
	}
	
	public List<Computer> listService(long number, long page) {
		List<Computer> computers = computerDAO.list(number, page);
		
		return computers;
	}
	
	public Optional<Computer> listDetailsService(long id) {
		Optional<Computer> computer = computerDAO.listDetails(id);
		
		return computer;
	}
	
	public Computer updateService(Computer computer) {
		computer = computerDAO.update(computer);
		
		return computer;
	}
	
	public long deleteService(long id) {
		computerDAO.delete(id);
		
		return id;
	}
}
