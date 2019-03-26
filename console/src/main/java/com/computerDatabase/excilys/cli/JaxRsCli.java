package com.computerDatabase.excilys.cli;

import com.computerDatabase.excilys.service.JaxRsService;

public class JaxRsCli {

	public static void main(String []args) {
		JaxRsService service = new JaxRsService();
		System.out.println(service.listJSONComputers());
	}
}
