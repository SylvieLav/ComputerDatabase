package com.computerDatabase.excilys.dao;

import java.sql.*;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;

import com.computerDatabase.excilys.mapper.ComputerMapper;
import com.computerDatabase.excilys.model.*;

@Component
public class ComputerDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);
	private static final String DELETE_COMPUTER = "DELETE FROM `computer` WHERE id = ?";
	private static final String INSERT_COMPUTER = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	private static final String LIST_COMPUTERS = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY ";
	private static final String LIST_COMPUTER_DETAILS = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.id, company.name FROM `computer` LEFT JOIN company ON computer.company_id = company.id WHERE computer.id = ";
	private static final String SEARCH_COMPUTERS = "SELECT company_id, introduced, discontinued, id, name FROM computer WHERE name LIKE ? ORDER BY ";
	private static final String UPDATE_COMPUTER = "UPDATE computer SET name = ? , introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	
	private ComputerMapper computerMapper = new ComputerMapper();
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(DbConnection.getDataSource());

	private ComputerDAO() {}
	
	private RowMapper<Computer> rowMapper = new RowMapper<Computer>() {
		public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return computerMapper.mapComputer(rs);
		}
	};

	public Computer create(Computer computer) {
		jdbcTemplate.update(INSERT_COMPUTER, new Object[]{computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompany().getId()});
		
		return computer;
	}

	public List<Computer> list(long number, long page, String sortElement, String order) {
		LOGGER.info("list called !");
		String request = LIST_COMPUTERS;
		if (sortElement.contains("companyName")) {
			sortElement = "company.name";
		} else if (sortElement.contains("name")) {
			sortElement = "computer.name";
		}

		request = request + sortElement + " " + order;
		if (number != -1 && page != -1) {
			request = request + " LIMIT " + (page - 1) * number + ", " + number;
		}
		
		return jdbcTemplate.query(request, rowMapper);
	}
	
	public List<Computer> listBySearch(long number, long page, String sortElement, String order, String filter) {
		LOGGER.info("listBySearch called !");
		String request = SEARCH_COMPUTERS;
		if (order.contains("name")) {
			order = "computer.name";
		} else if (order.contains("company")) {
			order = "company.name";
		}
		
		request = request + sortElement + " " + order;
		if (number != -1 && page != -1) {
			request = request + " LIMIT " + (page - 1) * number + ", " + number;
		}
		
		return jdbcTemplate.query(request, rowMapper);
	}

	public Optional<Computer> listDetails(long id) {
		LOGGER.info("listDetails called !");
		String request = LIST_COMPUTER_DETAILS + id;
		LOGGER.info("request = " + request);
		
		jdbcTemplate.query(request, rowMapper);

		return Optional.ofNullable(jdbcTemplate.query(request, rowMapper).get(0));
	}

	public Computer update(Computer computer) {
		jdbcTemplate.update(UPDATE_COMPUTER, new Object[]{computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompany().getId(), computer.getId()});

		return computer;
	}

	public long delete(long id) {
		jdbcTemplate.update(DELETE_COMPUTER, id);

		return id;
	}
}
