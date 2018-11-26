package com.computerDatabase.excilys.ui;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.slf4j.*;

import com.computerDatabase.excilys.cli.CompanyCli;
import com.computerDatabase.excilys.cli.ComputerCli;
import com.computerDatabase.excilys.validator.ComputerValidator;

import junit.framework.Test;

public class MainClass {
	private long id;
	
	private static ComputerCli computerCli = ComputerCli.getInstance();
	private static CompanyCli companyCli = CompanyCli.getInstance();
	private static ComputerValidator computerValidator = ComputerValidator.getInstance();
	
	public MainClass(long id) {
		this.id = id;
	}
	
	public static final void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(MainClass.class);
		Scanner input = new Scanner(System.in);
		logger.info("Here you can read and modify the list of computers or their manufacturer companies.");
		String line;
		do {
			line = input.nextLine();
			if (line.equalsIgnoreCase("Create a computer")) {
				String computerName;
				do {
					logger.info("Give the name of the computer:");
					computerName = input.nextLine();
				} while (computerName.equals(""));
				logger.info("Give the date when the computer was introduced:");
				String introduced = input.nextLine();
				logger.info("Give the date when the computer was discontinued:");
				String discontinued = input.nextLine();
				logger.info("Give the company ID:");
				String companyID = input.nextLine();;
				boolean validator = computerValidator.validateAll(introduced, discontinued, companyID);
				if (validator == true) {
					computerCli.createCli(computerName, introduced, discontinued, companyID);
				}
			} else if (line.equalsIgnoreCase("List computers")) {
				String number;
				do {
					logger.info("Give the number of computers per page:");
					number = input.nextLine();
				} while (number.equals(""));
				computerCli.listCli(number);
			} else if (line.contains("List a computer")) {
				computerCli.listDetailsCli(line.split(" ")[3]);
			} else if (line.contains("Update")) {
				String oldId;
				do {
					logger.info("Give the id of the computer to update:");
					oldId = input.nextLine();
				} while (oldId.equals(""));
				logger.info("Give the new name of the computer:");
				String computerName = input.nextLine();
				logger.info("Give the new date when the computer was introduced:");
				String introduced = input.nextLine();
				logger.info("Give the new date when the computer was discontinued:");
				String discontinued = input.nextLine();
				logger.info("Give the new company ID:");
				String companyID = input.nextLine();
				computerCli.updateCli(oldId, computerName, introduced, discontinued, companyID);
			} else if (line.contains("Delete")) {
				computerCli.deleteCli(line.split(" ")[1]);
			} else if (line.equalsIgnoreCase("List companies")) {
				companyCli.listCli();
			} else {
				logger.info("Your command cannot be executed. Please try again.");
			}
		} while (line.equalsIgnoreCase("Stop") == false);
		input.close();
		logger.info("The programm has been stopped.");
	}

}
