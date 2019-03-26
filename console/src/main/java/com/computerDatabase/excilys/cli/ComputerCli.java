package com.computerDatabase.excilys.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.excilys.model.*;
import com.computerDatabase.excilys.service.ComputerService;
import com.computerDatabase.excilys.service.JaxRsService;
import com.computerDatabase.excilys.validator.*;

@Component
public class ComputerCli {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerCli.class);

	@Autowired
	private static CompanyValidator companyValidator;
	@Autowired
	private static ComputerService computerService;
	@Autowired
	private static ComputerValidator computerValidator;
	@Autowired
	private static JaxRsService entryPoint = new JaxRsService();

	public static void listCli() {
		LOGGER.info("********************ComputerCli.listCli()********************");
		LOGGER.info(entryPoint.listJSONComputers().toString());
	}
	
	public static void listDetailsCli(String id) {
		LOGGER.info("********************ComputerCli.listDetailsCli()********************");
		LOGGER.info(entryPoint.listJSONDetailsComputer().toString());
	}

	public static void deleteCli(String id) {
		LOGGER.info("********************ComputerCli.deleteCli()********************");
		LOGGER.info(entryPoint.deleteJSONComputer(Long.parseLong(id)).toString());
	}

	public static void createCli(String sName, String sIntroduced, String sDiscontinued, String sCompanyId) {
		LOGGER.info("********************ComputerCli.createCli()********************");
		LocalDateTime introduced = null, discontinued = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		Computer computer = null;
		Company company = null;
		
		if (computerValidator.validateAll(sIntroduced, sDiscontinued)) {
			if (!"".equals(sIntroduced))
				introduced = LocalDateTime.parse(sIntroduced, formatter);
			if (!"".equals(sDiscontinued))
				discontinued = LocalDateTime.parse(sDiscontinued, formatter);
			
			if (!"".equals(sCompanyId) && companyValidator.validateAll(sCompanyId))
				company = new Company.CompanyBuilder(Long.parseLong(sCompanyId)).build();


			computer = new Computer.ComputerBuilder(sName).introduced(introduced).discontinued(discontinued).company(company).build();
			LOGGER.info(entryPoint.createJSONComputer(computer).toString());
		}
	}

	public static void updateCli(String id, String newName, String sIntroduced, String sDiscontinued, String sCompanyId) {
		LOGGER.info("********************ComputerCli.updateCli()********************");
		LocalDateTime introduced = null, discontinued = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		Computer computerToUpdate = computerService.listDetails(Long.parseLong(id)).get();
		Computer computer = null;
		Company company = null;
		
				if (!(sIntroduced.contains("NULL") || sDiscontinued.contains("NULL")) && computerValidator.validateAll(sIntroduced, sDiscontinued)) {
			if ("".equals(sIntroduced))
				introduced = computerToUpdate.getIntroduced();
			else
				introduced = LocalDateTime.parse(sIntroduced, formatter);

			if ("".equals(sDiscontinued))
				discontinued = computerToUpdate.getDiscontinued();
			else
				discontinued = LocalDateTime.parse(sDiscontinued, formatter);

			if (!sCompanyId.contains("NULL") && companyValidator.validateAll(sCompanyId)) {
				if ("".equals(sCompanyId))
					company = computerToUpdate.getCompany();
				else
					company = new Company.CompanyBuilder(Long.parseLong(sCompanyId)).build();
			}
			
			computer = new Computer.ComputerBuilder(newName).introduced(introduced).discontinued(discontinued).company(company).build();
			LOGGER.info(entryPoint.updateJSONComputer(computer).toString());
		}
	}
}
