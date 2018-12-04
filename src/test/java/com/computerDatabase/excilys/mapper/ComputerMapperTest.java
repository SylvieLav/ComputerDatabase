package com.computerDatabase.excilys.mapper;

import static org.junit.Assert.assertEquals;

import java.sql.*;
import java.util.*;

import org.junit.*;
import org.mockito.*;
import org.slf4j.*;

import com.computerDatabase.excilys.model.Computer;

public class ComputerMapperTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerMapperTest.class);
	
	@InjectMocks
	private ComputerMapper computerMapper = new ComputerMapper();
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSetComputers(ResultSet rs) {
		List<Computer> actual = new ArrayList<>();
		List<Computer> expected = new ArrayList<>();
		try {
			actual = computerMapper.mapComputers(rs);
		} catch (SQLException e) {
			LOGGER.error("Could not map computers for the test !");
		}
		
		assertEquals("Test failed in setComputers() !", expected, actual);
	}

}
