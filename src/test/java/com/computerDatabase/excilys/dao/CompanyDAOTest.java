package com.computerDatabase.excilys.dao;

import static org.junit.Assert.assertEquals;

import java.sql.*;
import java.util.*;

import org.junit.*;
import org.mockito.*;

import com.computerDatabase.excilys.mapper.CompanyMapper;
import com.computerDatabase.excilys.model.Company;

public class CompanyDAOTest {
	@Mock
	private DbConnection dbConnection;
	@Mock
	private Connection connection;
	@Mock
	private PreparedStatement statement;
	@Mock
	private ResultSet rs;
	@Mock
	private CompanyMapper companyMapper;
	@InjectMocks
	private CompanyDAO companyDAO;
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testListCompanies() {
		List<Company> actual = companyDAO.list();
		List<Company> expected = new ArrayList<>();
		for (int i = 0; i<10; i++) {
			Company company = new Company.CompanyBuilder(i).name("company n° " + i).build();
			actual.add(company);
		}
		assertEquals("Test failed in companyDAO.list() !", expected, actual);
	}
	
}
