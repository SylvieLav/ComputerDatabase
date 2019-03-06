package com.computerDatabase.excilys.controller;

import java.time.LocalDateTime;
import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.computerDatabase.excilys.dao.ComputerDAO;
import com.computerDatabase.excilys.dto.ComputerDTO;
import com.computerDatabase.excilys.mapper.ComputerDTOMapper;
import com.computerDatabase.excilys.model.*;
import com.computerDatabase.excilys.service.*;
import com.computerDatabase.excilys.validator.*;

@Controller
@RequestMapping()
public class ComputerController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyValidator companyValidator;
	@Autowired
	private ComputerDTOMapper computerDTOMapper;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerValidator computerValidator;
	@Autowired
	private ComputerDAO computerDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);
	
	@GetMapping(value = "/dashboard")
	public String listComputers(@RequestParam(required = false) long number, @RequestParam(required = false) long pageNumber,
			@RequestParam(required = false) String search, @RequestParam(required = false) String sortBy,
			@RequestParam(required = false) String orderBy, @RequestParam(required = false) String lang, Model model) {
		LOGGER.info("******************************listComputers******************************");
		
		List<Computer> computers = computerService.list(-1, -1, "name", "ASC");
		long computersSize = computers.size();
		orderBy = "ASC";
		
		if (sortBy != null) {
			if (orderBy.isEmpty() || orderBy.contains("DESC")) {
				orderBy = "ASC";
			} else if (orderBy.contains("ASC")) {
				orderBy = "DESC";
			}
		}

		Page page = new Page();
		long lastPage = page.getLastPage(computersSize, pageNumber);
		
		if (lang == null) {
			lang = "EN";
		}
		
		if (sortBy != null) {
			if (number != computersSize) {
				computers = computerService.list(number, pageNumber, sortBy, orderBy);
			} else {
				computers = computerService.list(-1, -1, sortBy, orderBy);
			}
		} else if (search != null) {
			computers = computerService.listBySearch(-1, -1, "name", "ASC", search);
			computersSize = computers.size();
		} else if (number != computersSize) {
			computers = computerService.list(number, pageNumber, "name", "ASC");
		} else {
			computers = computerService.list(-1, -1, "name", "ASC");
		}

		List<ComputerDTO> computersDTO = new ArrayList<>();
		computers.stream().map(computer-> computersDTO.add(computerDTOMapper.map(computer)));
		
		model.addAttribute("computers", computersDTO);
		model.addAttribute("computersSize", computersSize);
		model.addAttribute("lang", lang);
		model.addAttribute("nextPage", page.getNextPage(pageNumber, lastPage));
		model.addAttribute("number", number);
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("page", pageNumber);
		model.addAttribute("pageArray", page.getPageArray(pageNumber, lastPage));
		model.addAttribute("previousPage", page.getPreviousPage(pageNumber));
		
		return "/dashboard";
	}
	
	@GetMapping(value = "/addComputer")
	public String createComputer(@RequestParam(required = true) String computerName, @RequestParam(required = false) LocalDateTime introduced,
			@RequestParam(required = false) LocalDateTime discontinued, @RequestParam(required = false) String lang,
			@RequestParam(required = false) long companyId, Model model) {
		LOGGER.info("******************************createComputer******************************");
		List<Company> companies = companyService.list();
		Company company = null;
		if (companyValidator.validateAll(String.valueOf(companyId))) {
			company = new Company.CompanyBuilder(companyId).build();
		}
		if (computerValidator.validateAll(introduced.toString().replaceAll("T", "-"), discontinued.toString().replaceAll("T", "-"),
				String.valueOf(companyId)) == true) {
			Computer computer = new Computer.ComputerBuilder(computerName).introduced(introduced).discontinued(discontinued).company(company).build();
			computerDAO.create(computer);
		}
		model.addAttribute("companies", companies);
		
		return "addComputer";
	}
	
	@PostMapping(value = "editComputer/{computerId}")
	public String updateComputer(@RequestParam(required = false) long computerId, @RequestParam(required = false) String lang, Model model) {
		LOGGER.info("******************************updateComputer******************************");
		try {
			Computer computer = computerService.listDetails(computerId).get();
			ComputerDTO computerDTO = computerDTOMapper.map(computer);
			
			model.addAttribute("computer", computerDTO);
			model.addAttribute("lang", lang);
		} catch (NumberFormatException e) {
			LOGGER.error("The computer ID " + computerId + " is not a valid number !");
		}
		
		List<Company> companies = companyService.list();
		model.addAttribute("companies", companies);
		
		return "editComputer";
	}
	
	@PostMapping
	public String deleteComputer(@RequestParam(required = false) String[] cb) {
		LOGGER.info("******************************deleteComputer******************************");
		for (String cbItem : cb) {
			computerService.delete(Long.parseLong(cbItem));
		}
		
		return "editComputer";
	}
}
