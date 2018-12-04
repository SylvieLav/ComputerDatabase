package com.computerDatabase.excilys.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.*;
import org.mockito.*;

import com.computerDatabase.excilys.model.Computer;
import com.computerDatabase.excilys.model.Computer.ComputerBuilder;

public class ComputerDAOTest {
	@InjectMocks
	private ComputerDAO computerDAO = ComputerDAO.getInstance();
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreate(Computer computer) {
		Computer actual = computerDAO.create(computer);
		Computer expected = new ComputerBuilder(computer.getName()).id(computer.getId()).build();
		assertEquals("Test failed in computerDAO.create() !", expected, actual);
	}

	@Test
	public void testList(long number, long page) {
		List<Computer> actual = computerDAO.list();
		List<Computer> expected = new ArrayList<>();
		for (int i = 0; i<10; i++) {
			Computer computer = new Computer.ComputerBuilder("computer n° " + i).build();
			actual.add(computer);
		}
		assertEquals("Test failed in computerDAO.list() !", expected, actual);
	}

	@Test
	public void testListDetails(long id) {
		Optional<Computer> actual =  computerDAO.listDetails(id);
		Optional<Computer> expected = null;
		assertEquals("Test failed in computerDAO.listDetails() !", expected, actual);
	}

	@Test
	public void testUpdate(Computer computer) {
		Computer actual = computerDAO.update(computer);
		Computer expected = new ComputerBuilder(computer.getName()).id(computer.getId()).build();
		assertEquals("Test failed in computerDAO.testUpdate() !", expected, actual);
	}

	@Test
	public void testDelete(long id) {
		long actual = computerDAO.delete(id);
		long expected = id;
		assertEquals("Test failed in computerDAO.testUpdate() !", expected, actual);
	}

}
