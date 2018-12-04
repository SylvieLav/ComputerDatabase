package com.computerDatabase.excilys.dao;

import java.sql.*;

import java.util.*;

import org.slf4j.*;

import com.computerDatabase.excilys.mapper.CompanyMapper;
import com.computerDatabase.excilys.model.Company;

public class CompanyDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);
	private static final String LIST_COMPANIES = "SELECT id, name FROM `company` ORDER BY name ASC";
	private static final String LIST_COMPANY = "SELECT id, name FROM `company` WHERE id = ?";
	private CompanyMapper companyMapper = new CompanyMapper();
	private DbConnection dbConnection = DbConnection.getInstance();
	
	private CompanyDAO() {}
	
	private static final CompanyDAO INSTANCE = new CompanyDAO();
	
	public static CompanyDAO getInstance() {
		return INSTANCE;
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

}
