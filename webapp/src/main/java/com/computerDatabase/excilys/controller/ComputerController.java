package com.computerDatabase.excilys.controller;

import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.computerDatabase.excilys.dto.ComputerDTO;
import com.computerDatabase.excilys.mapper.ComputerDTOMapper;
import com.computerDatabase.excilys.model.*;
import com.computerDatabase.excilys.service.*;

@Controller
@RequestMapping()
public class ComputerController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerDTOMapper computerDTOMapper;
	@Autowired
	private ComputerService computerService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);
	
	@GetMapping(value = "/dashboard")
	public String listComputers(@RequestParam(required = false) Long number, @RequestParam(required = false) Long page,
			@RequestParam(required = false) String search, @RequestParam(required = false) String sortBy, @RequestParam(required = false) String lang,
			@RequestParam(required = false) String orderBy, Model model) {
		LOGGER.info("******************************ComputerController******************************");
		List<Computer> computers = computerService.list(-1, -1, "name", "ASC");
		long totalNumber = computers.size();
		number = totalNumber;
		page = 1L;
		
		orderBy = "ASC";
		
		model.addAttribute("computersSize", totalNumber);
		if (sortBy != null) {
			if (orderBy.isEmpty() || orderBy.contains("DESC")) {
				orderBy = "ASC";
			} else if (orderBy.contains("ASC")) {
				orderBy = "DESC";
			}
		}
		model.addAttribute("orderBy", orderBy);

		long lastPage = (totalNumber + number - 1) / number;
		List<Long> pageList = new ArrayList<>();
		if (page >= lastPage - 2) {
			for (int i = 0; i < 5; i++) {
				if (lastPage - 4 + i > 0)
					pageList.add(lastPage - 4 + i);
			}
		} else if (page <= 3) {
			for (int i = 0; i < 5; i++) {
				long l = i + 1;
				pageList.add(l);
			}
		} else {
			for (int i = 0; i < 5; i++)
				pageList.add(page - 2 + i);
		}

		long previousPage = 1, nextPage = lastPage;
		if (page > 1) {
			previousPage = page - 1;
		}
		if (page < lastPage) {
			nextPage = page + 1;
		}
		
		if (lang == null) {
			lang = "EN";
		}
		
		Long[] pageArray = pageList.stream().map(Long::new).toArray(Long[]::new);
		model.addAttribute("number", number);
		model.addAttribute("pageNumber", page);
		model.addAttribute("previousPage", previousPage);
		model.addAttribute("nextPage", nextPage);
		model.addAttribute("pageArray", pageArray);
		model.addAttribute("lang", lang);
		
		if (sortBy != null) {
			if (number != totalNumber) {
				computers = computerService.list(number, page, sortBy, orderBy);
			} else {
				computers = computerService.list(-1, -1, sortBy, orderBy);
			}
		} else if (search != null) {
			computers = computerService.listBySearch(-1, -1, "name", "ASC", search);
			totalNumber = computers.size();
		} else if (number != totalNumber) {
			computers = computerService.list(number, page, "name", "ASC");
		} else {
			computers = computerService.list(-1, -1, "name", "ASC");
		}

		List<ComputerDTO> computersDTO = new ArrayList<>();
		computers.stream().map(computer-> computersDTO.add(computerDTOMapper.map(computer)));
		model.addAttribute("computers", computersDTO);
		
		return "dashboard";
	}
	
	@GetMapping(value = "/addComputer")
	public String createComputer(@RequestParam(required = false) String lang, Model model) {
		List<Company> companies = companyService.list();
		
		model.addAttribute("lang", lang);
		model.addAttribute("companies", companies);
		
		return "addComputer";
	}
	
	@PostMapping(value = "/editComputer/{computerId}")
	public String updateComputer(@RequestParam(required = false) long computerId, @RequestParam(required = false) String lang, Model model) {
		try {
			LOGGER.info("computerId = " + computerId);
			Computer computer = null;
			computer = computerService.listDetails(computerId).get();
			LOGGER.info("computer.get().getName() = " + computer.getName());
			ComputerDTO computerDTO = null;
			computerDTO = computerDTOMapper.map(computer);
			
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
		for (String cbItem : cb) {
			computerService.delete(Long.parseLong(cbItem));
		}
		
		return "editComputer";
	}
}