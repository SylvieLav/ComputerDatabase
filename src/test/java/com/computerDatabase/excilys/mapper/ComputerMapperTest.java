package com.computerDatabase.excilys.mapper;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.computerDatabase.excilys.model.Computer;

public class ComputerMapperTest {
	@InjectMocks
	private ComputerMapper computerMapper = new ComputerMapper();
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSetComputers(ResultSet rs) {
		List<Computer> actual = computerMapper.setComputers(rs);
		List<Computer> expected = new ArrayList<>();
		
		assertEquals("Test failed in setComputers() !", expected, actual);
	}

}
