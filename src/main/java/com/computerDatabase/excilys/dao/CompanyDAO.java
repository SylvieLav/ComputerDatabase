package com.computerDatabase.excilys.dao;

import java.sql.*;

import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.excilys.mapper.CompanyMapper;
import com.computerDatabase.excilys.model.Company;

@Component
public class CompanyDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);
	private static final String DELETE_COMPANY = "DELETE FROM `company` WHERE id = ?";
	private static final String DELETE_COMPUTERS_FROM_COMPANY = "DELETE FROM `computer` WHERE company_id = ?";
	private static final String LIST_COMPANIES = "SELECT id, name FROM `company` ORDER BY name ASC";
	private static final String LIST_COMPANY = "SELECT id, name FROM `company` WHERE id = ?";

	private CompanyMapper companyMapper = new CompanyMapper();
	
	@Autowired
	private DbConnection dbConnection;

	private CompanyDAO() {
	}

	public List<Company> list() {
		Integer t = 0;
		t.toString();
		List<Company> companies = new ArrayList<Company>();

		try (PreparedStatement statement = dbConnection.connect().prepareStatement(LIST_COMPANIES);
				ResultSet rs = statement.executeQuery()) {
			companies = companyMapper.mapCompanies(rs);
		} catch (SQLException e) {
			LOGGER.error("Could not execute the query in CompanyDAO.list() !");
		} finally {
			dbConnection.disconnect();
		}

		return companies;
	}

	public Optional<Company> getCompanyById(long idCompany) {
		Company company = null;
		ResultSet rs = null;
		try (PreparedStatement statement = dbConnection.connect().prepareStatement(LIST_COMPANY)) {
			statement.setLong(1, idCompany);
			rs = statement.executeQuery();
			rs.next();
			company = companyMapper.mapCompany(rs);
		} catch (SQLException e) {
			LOGGER.error("Could not execute the query in CompanyDAO.getCompanyById() !");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				LOGGER.error("ResultStatement did not close successfully !");
			}
			dbConnection.disconnect();
		}
		return Optional.ofNullable(company);
	}

	public long delete(long id) {
		try (PreparedStatement statement = dbConnection.connect().prepareStatement(DELETE_COMPANY);
				PreparedStatement statement2 = dbConnection.connect()
						.prepareStatement(DELETE_COMPUTERS_FROM_COMPANY);) {
			statement.setLong(1, id);
			statement.executeUpdate();
			statement2.setLong(1, id);
			statement2.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Could not execute the queries in CompanyDAO.delete() !");
		} finally {
			dbConnection.disconnect();
		}

		return id;
	}
}
