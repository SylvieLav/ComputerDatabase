package com.computerDatabase.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.computerDatabase.excilys.model.Computer;
import com.computerDatabase.excilys.service.ComputerService;
import com.computerDatabase.excilys.spring.Configuration;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/UpdateComputerServlet")
public class UpdateComputerServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateComputerServlet.class);
	private static final long serialVersionUID = -9075919656212484604L;
	
	private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Configuration.class);
	private static ComputerService computerService = applicationContext.getBean("computerService", ComputerService.class);

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
			long id = Long.parseLong(request.getQueryString().split("=")[1]);
			Computer computer = computerService.listDetailsService(id).get();

			request.setAttribute("computer", computer);
		} catch (NumberFormatException e) {
			LOGGER.error("The computer ID is not a valid number !");
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
