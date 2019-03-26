package com.computerDatabase.excilys.model;

import java.util.*;

public class Page {
	
	public long getLastPage(long computersSize, long number) {
		return (computersSize + number - 1) / number;
	}
	
	public Long[] getPageArray(long pageNumber, long lastPage) {
		List<Long> pageList = new ArrayList<>();
		if (pageNumber >= lastPage - 2) {
			for (int i = 0; i < 5; i++) {
				if (lastPage - 4 + i > 0)
					pageList.add(lastPage - 4 + i);
			}
		} else if (pageNumber <= 3) {
			for (int i = 0; i < 5; i++) {
				long l = i + 1;
				pageList.add(l);
			}
		} else {
			for (int i = 0; i < 5; i++)
				pageList.add(pageNumber - 2 + i);
		}
		
		return pageList.stream().map(Long::new).toArray(Long[]::new);
	}
}
