package com.computerDatabase.excilys.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.computerDatabase.excilys.model.Company;

public class CompanyServiceTest {
	@InjectMocks
	private CompanyService companyService = CompanyService.getInstance();
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testListService() {
		List<Company> actual = companyService.listService();
		
		assertEquals("Test failed in companyService.listService() !", expected, actual);
	}

}
