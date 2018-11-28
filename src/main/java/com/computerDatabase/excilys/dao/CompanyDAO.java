package com.computerDatabase.excilys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.computerDatabase.excilys.mapper.CompanyMapper;
import com.computerDatabase.excilys.model.Company;

public class CompanyDAO {
	private static final String LIST_COMPANIES = "SELECT * FROM `company` ORDER BY name ASC";
	private CompanyMapper companyMapper = new CompanyMapper();
	private DbConnection dbConnection = DbConnection.getInstance();
	
	private CompanyDAO() {}
	
	private static final CompanyDAO INSTANCE = new CompanyDAO();
	
	public static CompanyDAO getInstance() {
		return INSTANCE;
	}
	
	public List<Company> list() {
		List<Company> companies = new ArrayList<Company>();
		
		try (PreparedStatement statement = dbConnection.connect().prepareStatement(LIST_COMPANIES);
				ResultSet rs = statement.executeQuery()) {
		    companies = companyMapper.setCompanies(rs);
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
			dbConnection.disconnect();
		}
		
		return companies;
	}

}
