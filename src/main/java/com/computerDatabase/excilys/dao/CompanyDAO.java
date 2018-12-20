package com.computerDatabase.excilys.dao;

import java.sql.*;

import java.util.*;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;

import com.computerDatabase.excilys.mapper.CompanyMapper;
import com.computerDatabase.excilys.model.Company;

@Component
public class CompanyDAO {
	private static final String DELETE_COMPANY = "DELETE FROM `company` WHERE id = ?";
	private static final String DELETE_COMPUTERS_FROM_COMPANY = "DELETE FROM `computer` WHERE company_id = ?";
	private static final String LIST_COMPANIES = "SELECT id, name FROM `company` ORDER BY name ASC";
	private static final String LIST_COMPANY = "SELECT id, name FROM `company` WHERE id = ?";

	private CompanyMapper companyMapper = new CompanyMapper();
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(DbConnection.getDataSource());

	private CompanyDAO() {}
	
	private RowMapper<Company> rowMapper = new RowMapper<Company>() {
		public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
			return companyMapper.mapCompany(rs);
		}
	};

	public List<Company> list() {
		return jdbcTemplate.query(LIST_COMPANIES, rowMapper);
	}

	public Optional<Company> getCompanyById(long idCompany) {
		return Optional.ofNullable(jdbcTemplate.query(LIST_COMPANY, rowMapper).get(0));
	}

	public long delete(long id) {
		jdbcTemplate.update(DELETE_COMPANY, id);
		jdbcTemplate.update(DELETE_COMPUTERS_FROM_COMPANY, id);

		return id;
	}
}
