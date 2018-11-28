package com.computerDatabase.excilys.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.mockito.*;

import com.computerDatabase.excilys.model.Computer;

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
		Computer expected = new Computer();
		assertEquals("Test failed in computerDAO.create() !", expected, actual);
	}

	@Test
	public void testList() {
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
		Computer actual =  computerDAO.listDetails(id);
		Computer expected = new Computer();
		assertEquals("Test failed in computerDAO.listDetails() !", expected, actual);
	}

	@Test
	public void testUpdate(Computer computer) {
		Computer actual = computerDAO.update(computer);
		Computer expected = new Computer();
		assertEquals("Test failed in computerDAO.testUpdate() !", expected, actual);
	}

	@Test
	public void testDelete(long id) {
		Computer actual = new Computer();
		Computer expected = new Computer();
		assertEquals("Test failed in computerDAO.testUpdate() !", expected, actual);
	}

}
