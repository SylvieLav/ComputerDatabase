package com.computerDatabase.excilys.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.validation.Valid;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);
	
	@GetMapping(value = "/login")
	public String authenticate() {
		
		return "/login";
	}

	@GetMapping(value = "/dashboard")
	public String listComputers(@RequestParam(required = false) Long number, @RequestParam(required = false) Long pageNumber,
			@RequestParam(required = false) String search, @RequestParam(required = false) String sortElement, @RequestParam(required = false) String orderBy,
			@RequestParam(required = false) String lang, Model model) {
		List<Computer> computers = computerService.list(-1, -1, "name", "ASC");
		long computersSize = computers.size();

		if (number == null || number == 0)
			number = computersSize;
		if (pageNumber == null || pageNumber == 0)
			pageNumber = 1L;
		if (sortElement == null)
			sortElement = "name";
		if (lang == null)
			lang = "EN";

		String orderBy2 = null;
		if (orderBy == null || orderBy.isEmpty()) {
			orderBy = "DESC";
			orderBy2 = "ASC";
		} else if (orderBy.contains("ASC")) {
			orderBy = "DESC";
			orderBy2 = "ASC";
		} else if (orderBy.contains("DESC")) {
			orderBy = "ASC";
			orderBy2 = "DESC";
		}

		Page page = new Page();
		long lastPage = page.getLastPage(computersSize, number);

		if (search != null) {
			computers = computerService.listBySearch(-1, -1, "name", "ASC", search);
			computersSize = computers.size();
		} else {
			computers = computerService.list(number, pageNumber, sortElement, orderBy2);
		}

		List<ComputerDTO> computersDTO = new ArrayList<>();
		computers.stream().forEach(currentComputer -> {computersDTO.add(new ComputerDTO(currentComputer));
		});

		model.addAttribute("computers", computers);
		model.addAttribute("computersSize", computersSize);
		model.addAttribute("lang", lang);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("number", number);
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("page", pageNumber);
		model.addAttribute("pageArray", page.getPageArray(pageNumber, lastPage));
		model.addAttribute("sortElement", sortElement);

		return "/dashboard";
	}

	@PostMapping(value = "/dashboard")
	public String deleteComputer(@RequestParam(required = true) String[] cb) {
		for (String cbItem : cb)
			computerService.delete(Long.parseLong(cbItem));

		return "redirect:/dashboard";
	}

	@GetMapping(value = "/addComputer")
	public String createComputer(@RequestParam(required = false) String lang, Model model) {

		if (lang == null) {
			lang = "EN";
		}

		List<Company> companies = companyService.list();

		model.addAttribute("companies", companies);
		model.addAttribute("computerDTO", new ComputerDTO("", LocalDateTime.now().minusDays(1).toString(), LocalDateTime.now().toString(), "0"));
		model.addAttribute("lang", lang);

		return "redirect:/dashboard";
	}

	@PostMapping(value = "/addComputer")
	@ResponseStatus(HttpStatus.CREATED)
	public final String createComputerPost(@Valid @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult result,
			@RequestParam(required = false) String lang, Model model) {
		Company company = null;
		if (companyValidator.validateAll(String.valueOf(computerDTO.getCompanyId()))) {
			company = new Company.CompanyBuilder(Long.parseLong(computerDTO.getCompanyId())).build();
		}

		if (computerValidator.validateAll(computerDTO.getIntroduced(), computerDTO.getDiscontinued())) {
			LocalDateTime introduced, discontinued;
			introduced = LocalDateTime.parse(computerDTO.getIntroduced() + "T00:00");
			discontinued = LocalDateTime.parse(computerDTO.getDiscontinued() + "T00:00");
			Computer computer = new Computer.ComputerBuilder(computerDTO.getName()).introduced(introduced).discontinued(discontinued).company(company).build();
			computerService.create(computer);
		}

		model.addAttribute("name", computerDTO.getName());
		model.addAttribute("introduced", computerDTO.getIntroduced());
		model.addAttribute("discontinued", computerDTO.getDiscontinued());
		model.addAttribute("companyId", computerDTO.getCompanyId());

		return "/redirect:/dashboard";
	}

	@GetMapping(value = "/editComputer/{computerId}")
	public String updateComputer(@PathVariable("computerId") Long computerId, @RequestParam(required = false) String lang, Model model) {
		if (lang == null)
			lang = "EN";

		ComputerDTO computerDTO = new ComputerDTO("", LocalDateTime.now().minusDays(1).toString(), LocalDateTime.now().toString(), "0");
		try {
			Computer computer = computerService.listDetails(computerId).get();
			computerDTO = computerDTOMapper.map(computer);
		} catch (NumberFormatException e) {
			LOGGER.error("The computer ID " + computerId + " is not a valid number !");
		}

		List<Company> companies = companyService.list();

		model.addAttribute("companies", companies);
		model.addAttribute("computerDTO", computerDTO);
		model.addAttribute("introduced", computerDTO.getIntroduced().replaceAll("T00:00", ""));
		model.addAttribute("discontinued", computerDTO.getDiscontinued().replaceAll("T00:00", ""));
		model.addAttribute("lang", lang);

		return "redirect:/dashboard";
	}

	@PostMapping(value = "/editComputer/{computerId}")
	@ResponseStatus(value = HttpStatus.OK)
	public final String updateComputerPost(@PathVariable("computerId") Long computerId, @Valid @ModelAttribute("computerDTO") ComputerDTO computerDTO,
			BindingResult result, @RequestParam(required = false) String lang) {
		Company company = null;
		if (companyValidator.validateAll(String.valueOf(computerDTO.getCompanyId())))
			company = new Company.CompanyBuilder(Long.parseLong(computerDTO.getCompanyId())).build();

		String sIntroduced = "", sDiscontinued = "";
		LocalDateTime introduced = null, discontinued = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		if (!"".equals(computerDTO.getIntroduced()))
			sIntroduced = computerDTO.getIntroduced().replace("T", " ");
			introduced = LocalDateTime.parse(sIntroduced + " 00:00", formatter);
		if (!"".equals(computerDTO.getDiscontinued()))
			sDiscontinued = computerDTO.getDiscontinued().replace("T", " ");
			discontinued = LocalDateTime.parse(sDiscontinued + " 00:00", formatter);

		if (computerValidator.validateAll(sIntroduced, sDiscontinued)) {
			Computer computer = new Computer.ComputerBuilder(computerDTO.getName()).id(computerId).introduced(introduced).discontinued(discontinued).company(company).build();
			computerService.update(computer);
		}

		return "/redirect:/dashboard";
	}
}
