package com.computerDatabase.excilys.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.computerDatabase.excilys.dto.CompanyDTO;
import com.computerDatabase.excilys.dto.ComputerDTO;
import com.computerDatabase.excilys.mapper.ComputerDTOMapper;
import com.computerDatabase.excilys.model.Company;
import com.computerDatabase.excilys.model.Company.CompanyBuilder;
import com.computerDatabase.excilys.model.Computer;
import com.computerDatabase.excilys.service.CompanyService;
import com.computerDatabase.excilys.service.ComputerService;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = -9075919656212484604L;
	private static CompanyService companyService = CompanyService.getInstance();
	private static ComputerService computerService = ComputerService.getInstance();
	private ComputerDTOMapper computerDTOMapper = ComputerDTOMapper.getInstance();
       
    public MainServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		long totalNumber = computerService.listService(-1, -1).size();
		long number = totalNumber;
		long page = 1;
		
		String sNumber = request.getParameter("number");
		String sPage = request.getParameter("page");
		if (sNumber != null) {
			number = Long.parseLong(sNumber);
		}
		
		if (sPage != null) {
			page = Long.parseLong(sPage);
		}
		long lastPage = (totalNumber + number - 1)/number;
		List<Long> pageList = new ArrayList<>();
		if (page>=lastPage-2) {
			for (int i=0; i<5; i++) {
				if (lastPage-4+i>0)
					pageList.add(lastPage-4+i);
			}
		} else if (page<=3) {
			for (int i=0; i<5; i++) {
				long l = i+1;
				pageList.add(l);
			}
		} else {
			for (int i=0; i<5; i++)
				pageList.add(page-2+i);
		}
		
		long previousPage = 1, nextPage = lastPage;
		if (page>1) {
			previousPage = page - 1;
		}
		if (page<lastPage) {
			nextPage = page + 1;
		}
		Long[] pageArray = pageList.stream().map(Long::new).toArray(Long[]::new);
		request.setAttribute("number", number);
		request.setAttribute("pageNumber", page);
		request.setAttribute("previousPage", previousPage);
		request.setAttribute("nextPage", nextPage);
		request.setAttribute("pageArray", pageArray);
		
		List<Computer> computers = computerService.listService(number, page);
		
		String search = request.getParameter("search");
		if (search != null) {
			computers = computerService.listBySearchService(-1, -1, search);
			totalNumber = computers.size();
		}
		request.setAttribute("computersSize", totalNumber);
		
		List<ComputerDTO> computersDTO = new ArrayList<>();
		//Récupérer le nom de la company a partir du companyId du computer
		computers.stream().forEach(computer -> {
			Company company = computer.getCompany();
			if (company.getId() != 0) {
				//Initialise le nom de la company du computer avec le nom de la company récupérée par l'id
				//company.setName(companyService.getCompanyById(company.getId()).get().getName());
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
