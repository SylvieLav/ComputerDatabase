package com.computerDatabase.excilys.service;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.*;
import org.mockito.*;

import com.computerDatabase.excilys.model.Computer;
import com.computerDatabase.excilys.model.Computer.ComputerBuilder;

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
		Computer expected = new ComputerBuilder(computer.getName()).id(computer.getId()).build();
		
		assertEquals("Test failed in companyService.createService() !", expected, actual);
	}

	@Test
	public void testListService(long number, long page) {
		List<Computer> actual = computerService.listService(number, page);
		List<Computer> expected = new ArrayList<Computer>();
		
		assertEquals("Test failed in companyService.listService() !", expected, actual);
	}

	@Test
	public void testListDetailsService(long id) {
		Optional<Computer> actual = computerService.listDetailsService(id);
		Computer expected = null;
		
		assertEquals("Test failed in companyService.listDetailsService() !", expected, actual);
	}

	@Test
	public void testUpdateService(Computer computer) {
		Computer actual = computerService.updateService(computer);
		Computer expected = new ComputerBuilder(computer.getName()).id(computer.getId()).build();
		
		assertEquals("Test failed in companyService.updateService() !", expected, actual);
	}

	@Test
	public void testDeleteService(long id) {
		long actual = computerService.deleteService(id);
		long expected = id;
		
		assertEquals("Test failed in companyService.deleteService() !", expected, actual);
	}
}
