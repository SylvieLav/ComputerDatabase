package com.computerDatabase.excilys.dao;

import java.sql.*;
import java.util.*;

import org.slf4j.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;

import com.computerDatabase.excilys.dto.ComputerDTO;
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

	private ComputerDAO() {
	}
	
	private RowMapper<Computer> rowMapper = new RowMapper<Computer>() {
		public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return computerMapper.mapComputer(rs);
		}
	};

	public ComputerDTO createDTO(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO(computer);

		computerDTO.setId(computer.getId());
		computerDTO.setName(computer.getName());
		computerDTO.setCompanyName(computer.getCompany().getName());
		computerDTO.setIntroduced(computer.getIntroduced());
		computerDTO.setDiscontinued(computer.getDiscontinued());

		return computerDTO;
	}

	public Computer createBean(ComputerDTO computerDTO) {
		Computer computer = new Computer.ComputerBuilder(computerDTO.getName()).id(computerDTO.getId()).build();

		return computer;
	}

	public Computer create(Computer computer) {
			/*statement.setString(1, computer.getName());
			String introduced = null;
			if (computer.getIntroduced() != null) {
				introduced = computer.getIntroduced().toString();
			}
			statement.setString(2, introduced);
			String discontinued = null;
			if (computer.getDiscontinued() != null) {
				discontinued = computer.getDiscontinued().toString();
			}
			statement.setString(3, discontinued);
			
			statement.setLong(4, computer.getCompany().getId());*/
			jdbcTemplate.update(INSERT_COMPUTER, new Object[]{computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompany().getId()});
		
		return computer;
	}

	public List<Computer> list(long number, long page, String sortElement, String order) {
		LOGGER.info("list called !");
		List<Computer> computers = new ArrayList<Computer>();
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
		
		computers = jdbcTemplate.query(request, rowMapper);
		
		return computers;
	}
	
	public List<Computer> listBySearch(long number, long page, String sortElement, String order, String filter) {
		LOGGER.info("listBySearch called !");
		List<Computer> computers = new ArrayList<Computer>();
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

		computers = jdbcTemplate.query(request, rowMapper);
		
		return computers;
	}

	public Optional<Computer> listDetails(long id) {
		LOGGER.info("listDetails called !");
		Optional<Computer> computer = null;
		String request = LIST_COMPUTER_DETAILS + id;
		LOGGER.info("request = " + request);
		
		jdbcTemplate.query(request, rowMapper);
		computer = Optional.ofNullable(jdbcTemplate.query(request, rowMapper).get(0));

		return computer;
	}

	public Computer update(Computer computer) {
		/*statement.setString(1, computer.getName());
		statement.setString(2, computer.getIntroduced().toString());
		statement.setString(3, computer.getDiscontinued().toString());
		statement.setLong(4, computer.getCompany().getId());
		statement.setLong(5, computer.getId());
		statement.executeUpdate();*/
		jdbcTemplate.update(UPDATE_COMPUTER, new Object[]{computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompany().getId(), computer.getId()});

		return computer;
	}

	public long delete(long id) {
		jdbcTemplate.update(DELETE_COMPUTER, id);

		return id;
	}
}
