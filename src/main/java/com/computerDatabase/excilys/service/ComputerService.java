package com.computerDatabase.excilys.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computerDatabase.excilys.dao.ComputerDAO;
import com.computerDatabase.excilys.model.Computer;

@Service
public class ComputerService {
	@Autowired
	private ComputerDAO computerDAO;

	private ComputerService() {
	}

	public Computer createService(Computer computer) {
		computerDAO.create(computer);

		return computer;
	}

	public List<Computer> listBySearchService(long number, long pageNumber, String sortElement, String order, String filter) {
		List<Computer> computers = computerDAO.listBySearch(number, pageNumber, sortElement, order, filter);

		return computers;
	}

	public List<Computer> listService(long number, long page, String sortElement, String order) {
		List<Computer> computers = computerDAO.list(number, page, sortElement, order);

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
