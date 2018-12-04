package com.computerDatabase.excilys.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.*;

import com.computerDatabase.excilys.model.*;
//import com.computerDatabase.excilys.model.Page;
import com.computerDatabase.excilys.service.ComputerService;

public class ComputerCli {
	//private Page page;
	private static ComputerService computerService = ComputerService.getInstance();
	private static final ComputerCli INSTANCE = new ComputerCli();
	
	public static ComputerCli getInstance() {
		return INSTANCE;
	}
	
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
		if (sCompanyId.equals("") == false) {
			company = new Company.CompanyBuilder(Long.parseLong(sCompanyId)).build();
		}
		
		Computer computer = new Computer.ComputerBuilder(sName).introduced(introduced).discontinued(discontinued).company(company).build();
		computerService.createService(computer);
	}
	
	public void listCli(String sNumber, String sPage) {
		Logger logger = LoggerFactory.getLogger(ComputerCli.class);
		long number = Long.parseLong(sNumber);
		long pageNumber = Long.parseLong(sPage);
		List<Computer> computers = computerService.listService(number, pageNumber);
		for (Computer computer : computers) {
			logger.info(computer.getName());
		}
	}
	
	public void listDetailsCli(String id) {
		Logger logger = LoggerFactory.getLogger(ComputerCli.class);
		try {
		Computer computer = computerService.listDetailsService(Long.parseLong(id)).get();
		logger.info(id + "\n" + computer.getName() + "\n" + computer.getIntroduced() + "\n" + computer.getDiscontinued() + "\n" + computer.getCompany().getName());
	
		} catch (NumberFormatException e) {
			logger.error("The id you gave is not a valid number");
		}
	}
	
	public void updateCli(String id, String newName, String sIntroduced, String sDiscontinued, String sCompanyId) {
		Logger logger = LoggerFactory.getLogger(ComputerCli.class);
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
		
		if (introduced != null && discontinued != null && introduced.compareTo(discontinued)>=0) {
			logger.error("Error : discontinued<introduced");
		} else {
			Computer computer = new Computer.ComputerBuilder(newName).introduced(introduced).discontinued(discontinued).company(company).build();
			computerService.updateService(computer);
		}
	}
	
	public void deleteCli(String id) {
		computerService.deleteService(Long.parseLong(id));
	}

}