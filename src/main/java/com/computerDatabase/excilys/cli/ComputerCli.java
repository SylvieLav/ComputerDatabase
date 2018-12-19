package com.computerDatabase.excilys.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.excilys.model.*;
import com.computerDatabase.excilys.service.ComputerService;

@Component
public class ComputerCli {
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerCli.class);

	@Autowired
	private ComputerService computerService;

	public void createCli(String sName, String sIntroduced, String sDiscontinued, String sCompanyId) {
		LocalDateTime introduced = null, discontinued = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		if (sIntroduced.equals("") == false) {
			introduced = LocalDateTime.parse(sIntroduced, formatter);
		}
		if (sDiscontinued.equals("") == false) {
			discontinued = LocalDateTime.parse(sDiscontinued, formatter);
		}
		Company company = new Company.CompanyBuilder(Long.parseLong(sCompanyId)).build();

		Computer computer = new Computer.ComputerBuilder(sName).introduced(introduced).discontinued(discontinued)
				.company(company).build();
		computerService.createService(computer);
	}

	public void listCli(String sNumber, String sPage, String sortElement, String order) {
		long number = Long.parseLong(sNumber);
		long page = Long.parseLong(sPage);
		List<Computer> computers = computerService.listService(number, page, sortElement, order);
		for (Computer computer : computers) {
			LOGGER.info(computer.getName());
		}
	}

	public void listDetailsCli(String id) {
		try {
			// Computer computer =
			// computerService.listDetailsService(Long.parseLong(id)).get();

		} catch (NumberFormatException e) {
			LOGGER.error("The id you gave is not a valid number");
		}
	}

	public void updateCli(String id, String newName, String sIntroduced, String sDiscontinued, String sCompanyId) {
		LocalDateTime introduced = null, discontinued = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		Computer computerToUpdate = computerService.listDetailsService(Long.parseLong(id)).get();
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

		if (introduced != null && discontinued != null && introduced.compareTo(discontinued) >= 0) {
			LOGGER.error("Error : discontinued<introduced");
		} else {
			Computer computer = new Computer.ComputerBuilder(newName).introduced(introduced).discontinued(discontinued)
					.company(company).build();
			computerService.updateService(computer);
		}
	}

	public void deleteCli(String id) {
		computerService.deleteService(Long.parseLong(id));
	}

}