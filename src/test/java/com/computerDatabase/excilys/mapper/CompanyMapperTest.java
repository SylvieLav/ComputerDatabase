package com.computerDatabase.excilys.mapper;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.mockito.*;
import org.slf4j.*;

import com.computerDatabase.excilys.model.Company;

public class CompanyMapperTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyMapperTest.class);
	
	@InjectMocks
	private CompanyMapper companyMapper = new CompanyMapper();
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSetCompanies(ResultSet rs) {
		List<Company> actual = new ArrayList<>();
		List<Company> expected = new ArrayList<>();
		try {
			actual = companyMapper.mapCompanies(rs);
		} catch (SQLException e) {
			LOGGER.error("Could not map companies for the test !");
		}
		
		assertEquals("Test failed in setCompanies() !", expected, actual);
	}

}
