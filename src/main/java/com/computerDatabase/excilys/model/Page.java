package com.computerDatabase.excilys.model;

import java.util.List;

public class Page {

	public Page() {
	}

	public Computer[] createPage(List<Computer> computers, int number, int pageNumber) {
		Computer[] computerArray = new Computer[number];
		for (int i = number * (pageNumber - 1); i < number * pageNumber; i++) {
			computerArray[i] = computers.get(i);
		}

		return computerArray;
	}
}
