package com.computerDatabase.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.computerDatabase.excilys.service.ComputerService;

/**
* Servlet implementation class Servlet
*/
@WebServlet("/CreateComputerServlet")
public class CreateComputerServlet extends HttpServlet {
	private static final long serialVersionUID = -9075919656212484604L;
	//private static ComputerService computerService = ComputerService.getInstance();
      
   public CreateComputerServlet() {
       super();
   }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
