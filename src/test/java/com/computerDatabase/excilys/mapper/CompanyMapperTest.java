package com.computerDatabase.excilys.mapper;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.computerDatabase.excilys.model.Company;

public class CompanyMapperTest {
	@InjectMocks
	private CompanyMapper companyMapper = new CompanyMapper();
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSetCompanies(ResultSet rs) {
		List<Company> actual = companyMapper.setCompanies(rs);
		List<Company> expected = new ArrayList<>();
		
		assertEquals("Test failed in setCompanies() !", expected, actual);
	}

}
