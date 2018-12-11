package com.computerDatabase.excilys.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.computerDatabase.excilys.dto.ComputerDTO;
import com.computerDatabase.excilys.mapper.ComputerDTOMapper;
import com.computerDatabase.excilys.model.*;
import com.computerDatabase.excilys.service.ComputerService;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = -9075919656212484604L;
	
	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerDTOMapper computerDTOMapper;

	public MainServlet() {
		super();
	}
	
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long totalNumber = computerService.listService(-1, -1, null, null).size();
		long number = totalNumber;
		long page = 1;

		String sNumber = request.getParameter("number");
		String sPage = request.getParameter("page");
		String search = request.getParameter("search");
		String sortBy = request.getParameter("sortBy");
		String orderBy = "ASC";
		
		request.setAttribute("computersSize", totalNumber);
		if (sortBy != null) {
			if (request.getParameter("orderBy").isEmpty() || request.getParameter("orderBy").contains("DESC")) {
				orderBy = "ASC";
			} else if (request.getParameter("orderBy").contains("ASC")) {
				orderBy = "DESC";
			}
		}
		request.setAttribute("orderBy", orderBy);

		if (sNumber != null) {
			number = Long.parseLong(sNumber);
		}

		if (sPage != null) {
			page = Long.parseLong(sPage);
		}
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
		Long[] pageArray = pageList.stream().map(Long::new).toArray(Long[]::new);
		request.setAttribute("number", number);
		request.setAttribute("pageNumber", page);
		request.setAttribute("previousPage", previousPage);
		request.setAttribute("nextPage", nextPage);
		request.setAttribute("pageArray", pageArray);

		List<Computer> computers;
		if (sortBy != null) {
			System.out.println("Cas sortBy != null !");
			computers = computerService.listService(number, page, sortBy, request.getParameter("orderBy"));
		} else if (search != null) {
			System.out.println("Cas search != null !");
			computers = computerService.listBySearchService(-1, -1, null, null, search);
			totalNumber = computers.size();
		} else {
			System.out.println("Cas else !");
			computers = computerService.listService(number, page, null, null);
		}

		List<ComputerDTO> computersDTO = new ArrayList<>();
		request.setAttribute("computers", computersDTO);
		computers.stream().forEach(computer -> {
			Company company = computer.getCompany();
			if (company.getId() != 0) {
				// Initialise le nom de la company du computer avec le nom de la company
				// récupérée par l'id
				// company.setName(companyService.getCompanyById(company.getId()).get().getName());
			}
			computersDTO.add(computerDTOMapper.map(computer));
		});

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] cbArray = request.getParameterValues("cb");
		for (String cb : cbArray) {
			System.out.println("cb = " + cb);
			computerService.deleteService(Long.parseLong(cb));
		}
	}
}
