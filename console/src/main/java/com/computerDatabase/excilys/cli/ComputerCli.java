package com.computerDatabase.excilys.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.computerDatabase.excilys.model.*;
import com.computerDatabase.excilys.service.ComputerService;
import com.computerDatabase.excilys.validator.*;

public class ComputerCli {
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyValidator companyValidator;
	@Autowired
	private ComputerValidator computerValidator;

	public void createCli(String sName, String sIntroduced, String sDiscontinued, String sCompanyId) {
		LocalDateTime introduced = null, discontinued = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		if (sIntroduced.equals("") == false) {
			introduced = LocalDateTime.parse(sIntroduced, formatter);
		}
		if (sDiscontinued.equals("") == false) {
			discontinued = LocalDateTime.parse(sDiscontinued, formatter);
		}
		Company company = null;
		if (sCompanyId.equals("") == false && companyValidator.validateAll(sCompanyId) == true) {
			company = new Company.CompanyBuilder(Long.parseLong(sCompanyId)).build();
		}

		if (computerValidator.validateAll(sIntroduced, sDiscontinued, sCompanyId)) {
			Computer computer = new Computer.ComputerBuilder(sName).introduced(introduced).discontinued(discontinued).company(company).build();
			computerService.create(computer);
		}
	}

	public void listCli(String sNumber, String sPage) {
		Logger logger = LoggerFactory.getLogger(ComputerCli.class);
		long number = Long.parseLong(sNumber);
		long page = Long.parseLong(sPage);
		List<Computer> computers = computerService.list(number, page, "computerName", "ASC");
		for (Computer computer : computers) {
			logger.info(computer.getName());
		}
	}

	public void listDetailsCli(String id) {
		Logger logger = LoggerFactory.getLogger(ComputerCli.class);
		try {
			Computer computer = computerService.listDetails(Long.parseLong(id)).get();
			logger.info(id + "\n" + computer.getName() + "\n" + computer.getIntroduced() + "\n" + computer.getDiscontinued() + "\n" + computer.getCompany().getName());

		} catch (NumberFormatException e) {
			logger.error("The id you gave is not a valid number");
		}
	}

	public void updateCli(String id, String newName, String sIntroduced, String sDiscontinued, String sCompanyId) {
		Logger logger = LoggerFactory.getLogger(ComputerCli.class);
		LocalDateTime introduced = null, discontinued = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		Computer computerToUpdate = computerService.listDetails(Long.parseLong(id)).get();
		Company company = null;

		if (sIntroduced.equals("")) {
			introduced = computerToUpdate.getIntroduced();
		} else if (sIntroduced.equals("NULL") == false) {
			introduced = LocalDateTime.parse(sIntroduced, dtf);
		}
		if (sDiscontinued.equals("")) {
			discontinued = computerToUpdate.getDiscontinued();
		} else if (sDiscontinued.equals("NULL") == false) {
			discontinued = LocalDateTime.parse(sDiscontinued, dtf);
		}
		if (sCompanyId.equals("")) {
			company = computerToUpdate.getCompany();
		} else if (sCompanyId.equals("NULL") == false) {
			company = new Company.CompanyBuilder(Long.parseLong(sCompanyId)).build();
		}

		if (introduced != null && discontinued != null && introduced.compareTo(discontinued)>=0) {
			logger.error("Error : discontinued<introduced");
		} else {
			Computer computer = new Computer.ComputerBuilder(newName).introduced(introduced).discontinued(discontinued).company(company).build();
			computerService.update(computer);
		}
	}

	public void deleteCli(String id) {
		computerService.delete(Long.parseLong(id));
	}

}