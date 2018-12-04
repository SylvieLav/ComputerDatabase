package com.computerDatabase.excilys.service;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.*;
import org.mockito.*;

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
		List<Company> expected = new ArrayList<>();
		
		assertEquals("Test failed in companyService.listService() !", expected, actual);
	}
}
