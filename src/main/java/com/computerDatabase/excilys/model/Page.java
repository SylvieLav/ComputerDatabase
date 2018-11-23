package com.computerDatabase.excilys.model;

import java.util.List;

public class Page {
	
	public Page() {}
	
	public Computer[] createPage (List<Computer> computers, int number) {
		Computer[] computerArray = new Computer[number];
		for (int i = 0; i<computerArray.length; i++) {
			computerArray[i] = computers.get(i);
		}
		
		return computerArray;
	}

}
