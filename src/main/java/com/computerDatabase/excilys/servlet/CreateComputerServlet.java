package com.computerDatabase.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/CreateComputerServlet")
public class CreateComputerServlet extends HttpServlet {
	private static final long serialVersionUID = -9075919656212484604L;

	public CreateComputerServlet() {
		super();
	}
	
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
