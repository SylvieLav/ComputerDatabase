package com.computerDatabase.excilys.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.computerDatabase.excilys.dto.ComputerDTO;
import com.computerDatabase.excilys.mapper.ComputerDTOMapper;
import com.computerDatabase.excilys.model.Company;
import com.computerDatabase.excilys.model.Computer;
import com.computerDatabase.excilys.service.CompanyService;
import com.computerDatabase.excilys.service.ComputerService;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = -9075919656212484604L;
	private static ComputerService computerService = ComputerService.getInstance();
	private ComputerDTOMapper computerDTOMapper = ComputerDTOMapper.getInstance();
       
    public MainServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		long totalNumber = computerService.listService(-1, -1).size();
		request.setAttribute("computersSize", totalNumber);
		long number = totalNumber;
		long pageNumber = 1;
		
		String sNumber = request.getParameter("number");
		String sPageNumber = request.getParameter("pageNumber");
		if (sNumber != null) {
			number = Long.parseLong(sNumber);
		}
		
		if (sPageNumber != null) {
			pageNumber = Long.parseLong(sPageNumber);
		}
		
		long[] pageArray = new long[5];
		for (int i=0; i<5; i++) {
			pageArray[i] = pageNumber + i;
		}
		request.setAttribute("number", number);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("pageArray", pageArray);
		
		List<Computer> computers = computerService.listService(number, pageNumber);
		
		String search = request.getParameter("search");
		System.out.println("search = " + search);
		if (search != null) {
			computers = computerService.listBySearchService(-1, -1, search);
		}
		
		List<ComputerDTO> computersDTO = new ArrayList<>();
		computers.stream().forEach(computer -> {
			Company company = computer.getCompany();
			if (company.getId() != 0) {
				//Initialise le nom de la company du computer à partir du nom de la company récupérée par l'id
				//computer.getCompany().name(companyService.getCompanyById(company.getId()).get().getName());
			}
			computersDTO.add(computerDTOMapper.map(computer));
		});
		

		
		request.setAttribute("computers", computersDTO);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] cbArray = request.getParameterValues("cb");
		for (String cb : cbArray) {
			System.out.println("cb = " + cb);
			computerService.deleteService(Long.parseLong(cb));
		}
	}
}
