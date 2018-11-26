package com.computerDatabase.excilys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerDatabase.excilys.mapper.ComputerMapper;
import com.computerDatabase.excilys.model.Computer;

public class ComputerDAO {
	private static final String DELETE_COMPUTER			= "DELETE FROM `computer` WHERE id = ?";
	private static final String INSERT_COMPUTER			= "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)"; 
	private static final String LIST_COMPUTERS			= "SELECT * FROM `computer` ORDER BY name ASC";
	private static final String LIST_COMPUTER_DETAILS	= "SELECT * FROM `computer` where id = ?";
	private static final String UPDATE_COMPUTER			= "UPDATE computer SET name = ? , introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private ComputerMapper computerMapper				= new ComputerMapper();
	private DbConnection dbConnection					= DbConnection.getInstance();
	
	private ComputerDAO() {}
	
	private static final ComputerDAO INSTANCE = new ComputerDAO();
	
	public static ComputerDAO getInstance() {
		return INSTANCE;
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
			e.printStackTrace();
		} finally {
			dbConnection.disconnect();
		}
		
		return computer;
	}
	
	public List<Computer> list() {
		List<Computer> computers = new ArrayList<Computer>();
		
		try (PreparedStatement statement = dbConnection.connect().prepareStatement(LIST_COMPUTERS);
				ResultSet rs = statement.executeQuery();) {
		    computers = computerMapper.setComputers(rs);
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
			dbConnection.disconnect();
		}
		
		return computers;
	}
	
	public Computer listDetails(long id) {
		Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
		Computer computer = null;
		
		try (PreparedStatement statement = dbConnection.connect().prepareStatement(LIST_COMPUTER_DETAILS)) {
		    statement.setLong(1, id);
		    ResultSet rs = statement.executeQuery();
		    
		    computer = computerMapper.setComputers(rs).get(0);
		} catch (IndexOutOfBoundsException e) {
			logger.error("No computer was found for the id " + id);
		} catch (SQLException e) {
		    e.printStackTrace();
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
			e.printStackTrace();
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
		    e.printStackTrace();
		} finally {
			dbConnection.disconnect();
		}
		
		return id;
	}

}
