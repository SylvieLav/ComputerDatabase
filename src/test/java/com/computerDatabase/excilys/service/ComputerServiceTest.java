package com.computerDatabase.excilys.service;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.mockito.*;

import com.computerDatabase.excilys.model.Computer;

public class ComputerServiceTest {
	@InjectMocks
	private ComputerService computerService = ComputerService.getInstance();
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateService(Computer computer) {
		Computer actual = computerService.createService(computer);
		Computer expected = new Computer();
		
		assertEquals("Test failed in companyService.createService() !", expected, actual);
	}

	@Test
	public void testListService() {
		List<Computer> actual = computerService.listService();
		List<Computer> expected = new ArrayList<Computer>();
		
		assertEquals("Test failed in companyService.listService() !", expected, actual);
	}

	@Test
	public void testListDetailsService(long id) {
		Computer actual = computerService.listDetailsService(id);
		Computer expected = new Computer();
		
		assertEquals("Test failed in companyService.listDetailsService() !", expected, actual);
	}

	@Test
	public void testUpdateService(Computer computer) {
		Computer actual = computerService.updateService(computer);
		Computer expected = new Computer();
		
		assertEquals("Test failed in companyService.updaterService() !", expected, actual);
	}

	@Test
	public void testDeleteService(long id) {
		long actual = computerService.deleteService(id);
		long expected;
		
		assertEquals("Test failed in companyService.updaterService() !", expected, actual);
	}

}
