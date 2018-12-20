package com.computerDatabase.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.slf4j.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.computerDatabase.excilys.dto.ComputerDTO;
import com.computerDatabase.excilys.mapper.ComputerDTOMapper;
import com.computerDatabase.excilys.model.*;
import com.computerDatabase.excilys.service.*;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/UpdateComputerServlet")
public class UpdateComputerServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateComputerServlet.class);
	private static final long serialVersionUID = -9075919656212484604L;
	
	private CompanyService companyService;
	private ComputerService computerService;
	private ComputerDTOMapper computerDTOMapper;

	public UpdateComputerServlet() {
		super();
	}
	
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			long id = Long.parseLong(request.getParameter("computerId"));
			LOGGER.info("id = " + id);
			Computer computer = null;
			computer = computerService.listDetails(id).get();
			LOGGER.info("computer.get().getName() = " + computer.getName());
			ComputerDTO computerDTO = null;
			computerDTO = computerDTOMapper.map(computer);
			
			request.setAttribute("computer", computerDTO);
			request.setAttribute("lang", request.getParameter("lang"));
		} catch (NumberFormatException e) {
			LOGGER.error("The computer ID " + request.getParameter("computerId") + " is not a valid number !");
		}
		
		List<Company> companies = companyService.list();
		request.setAttribute("companies", companies);

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
