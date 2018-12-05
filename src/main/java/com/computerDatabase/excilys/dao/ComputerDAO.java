package com.computerDatabase.excilys.dao;

import java.sql.*;
import java.util.*;

import org.slf4j.*;

import com.computerDatabase.excilys.dto.ComputerDTO;
import com.computerDatabase.excilys.mapper.ComputerMapper;
import com.computerDatabase.excilys.model.*;

public class ComputerDAO {
	private static final Logger LOGGER					= LoggerFactory.getLogger(ComputerDAO.class);
	private static final String DELETE_COMPUTER			= "DELETE FROM `computer` WHERE id = ?";
	private static final String INSERT_COMPUTER			= "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)"; 
	private static final String LIST_COMPUTERS			= "SELECT company_id, introduced, discontinued, id, name FROM computer ORDER BY name";
	private static final String LIST_COMPUTER_DETAILS	= "SELECT company_id, introduced, discontinued, id, name FROM `computer` where id = ?";
	private static final String SEARCH_COMPUTERS		= "SELECT company_id, introduced, discontinued, id, name FROM computer WHERE name LIKE ? ORDER BY name";
	private static final String UPDATE_COMPUTER			= "UPDATE computer SET name = ? , introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private ComputerMapper 		computerMapper			= new ComputerMapper();
	private DbConnection		dbConnection			= DbConnection.getInstance();
	
	private ComputerDAO() {}
	
	private static final ComputerDAO INSTANCE = new ComputerDAO();
	
	public static ComputerDAO getInstance() {
		return INSTANCE;
	}
	
	public ComputerDTO createDTO(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO(computer.getId(), computer.getName());
		
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
		try (PreparedStatement statement = dbConnection.connect().prepareStatement(INSERT_COMPUTER)) {
			statement.setString(1, computer.getName());
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
			String id = null;
			if (computer.getCompany() != null) {
				id = Long.toString(computer.getCompany().getId());
			}
			statement.setString(4, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Could not execute the query in ComputerDAO.create() !");
		} finally {
			dbConnection.disconnect();
		}
		
		return computer;
	}
	
	public List<Computer> listBySearch(long number, long pageNumber, String filter) {
		List<Computer> computers = new ArrayList<Computer>();
		final String request;
		if (number == -1 && pageNumber == -1) {
			request = SEARCH_COMPUTERS;
		} else {
			request = SEARCH_COMPUTERS + " ASC LIMIT ?, ?";
		}
		
		try (PreparedStatement statement = dbConnection.connect().prepareStatement(request)) {
			statement.setString(1, "%" + filter + "%");
			if (request.contains("ASC LIMIT")) {
				statement.setLong(2, (pageNumber-1)*number);
				statement.setLong(3, number);
			}
			System.out.println("listBySearch.statement = " + statement);
			ResultSet rs = statement.executeQuery();
			computers = computerMapper.mapComputers(rs);
		} catch (SQLException e) {
			LOGGER.error("Could not execute the query in ComputerDAO.listBySearch() !");
		} finally {
			dbConnection.disconnect();
		}
		return computers;
	}
	
	public List<Computer> list(long number, long page) {
		List<Computer> computers = new ArrayList<Computer>();
		String request;
		if (number == -1 && page == -1) {
			request = LIST_COMPUTERS;
		} else {
			request = LIST_COMPUTERS + " ASC LIMIT ?, ?";
		}
		try (PreparedStatement statement = dbConnection.connect().prepareStatement(request)) {
			if (request.contains("ASC LIMIT")) {
				statement.setLong(1, (page-1)*number);
				statement.setLong(2, number);
			}
			ResultSet rs = statement.executeQuery();
			computers = computerMapper.mapComputers(rs);
		} catch (SQLException e) {
			LOGGER.error("Could not execute the query in ComputerDAO.list() !");
		} finally {
			dbConnection.disconnect();
		}
		return computers;
	}
	
	public Optional<Computer> listDetails(long id) {
		Optional<Computer> computer = null;
		
		try (PreparedStatement statement = dbConnection.connect().prepareStatement(LIST_COMPUTER_DETAILS)) {
		    statement.setLong(1, id);
		    ResultSet rs = statement.executeQuery();
		    
		    computer = Optional.ofNullable(computerMapper.mapComputers(rs).get(0));
		} catch (IndexOutOfBoundsException e) {
			LOGGER.error("No computer was found for the id " + id);
		} catch (SQLException e) {
			LOGGER.error("Could not execute the query in ComputerDAO.listDetails() !");
		} finally {
			dbConnection.disconnect();
		}
		
		return computer;
	}
	
	public Computer update(Computer computer) {		
		try (PreparedStatement statement = dbConnection.connect().prepareStatement(UPDATE_COMPUTER)) {
			statement.setString(1, computer.getName());
			statement.setString(2, computer.getIntroduced().toString());
			statement.setString(3, computer.getDiscontinued().toString());
			statement.setLong(4, computer.getCompany().getId());
			statement.setLong(5, computer.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Could not execute the query in ComputerDAO.update() !");
		} finally {
			dbConnection.disconnect();
		}
		
		return computer;
	}
	
	public long delete(long id) {	
		try (PreparedStatement statement = dbConnection.connect().prepareStatement(DELETE_COMPUTER)) {
		    statement.setLong(1, id);
		    statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Could not execute the query in ComputerDAO.delete() !");
		} finally {
			dbConnection.disconnect();
		}
		
		return id;
	}

}
