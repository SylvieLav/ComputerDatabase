package com.computerDatabase.excilys.cli;

import java.util.Scanner;

import org.slf4j.*;

public class MainCli {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainCli.class);

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		LOGGER.info("Welcome to Computer Database - CLI ! Here you can read and modify the list of computers or their manufacturer companies.");
		String line;
		do {
			line = input.nextLine();
			if (line.toLowerCase().contains("create a computer")) {
				String name;
				do {
					LOGGER.info("Give the name of the computer:");
					name = input.nextLine();
				} while (name.equals(""));
				LOGGER.info("Give the date when the computer was introduced:");
				String introduced = input.nextLine();
				LOGGER.info("Give the date when the computer was discontinued:");
				String discontinued = input.nextLine();
				LOGGER.info("Give the company ID:");
				String companyID = input.nextLine();
				ComputerCli.createCli(name, introduced, discontinued, companyID);
			} else if (line.toLowerCase().contains("list computers")) {
				ComputerCli.listCli();
			} else if (line.toLowerCase().contains("list a computer")) {
				ComputerCli.listDetailsCli(line.split(" ")[3]);
			} else if (line.toLowerCase().contains("update a computer")) {
				String id;
				do {
					LOGGER.info("Give the id of the computer to update:");
					id = input.nextLine();
				} while (id.equals(""));
				LOGGER.info("Give the new name of the computer:");
				String computerName = input.nextLine();
				LOGGER.info("Give the new date when the computer was introduced:");
				String introduced = input.nextLine();
				LOGGER.info("Give the new date when the computer was discontinued:");
				String discontinued = input.nextLine();
				LOGGER.info("Give the new company ID:");
				String companyID = input.nextLine();
				ComputerCli.updateCli(id, computerName, introduced, discontinued, companyID);
			} else if (line.toLowerCase().contains("delete")) {
				ComputerCli.deleteCli(line.split(" ")[1]);
			} else {
				LOGGER.info("Your command cannot be executed. Please try again.");
			}
		} while (!line.equalsIgnoreCase("Stop"));
		input.close();
		LOGGER.info("The programm has been stopped.");
	}
}
