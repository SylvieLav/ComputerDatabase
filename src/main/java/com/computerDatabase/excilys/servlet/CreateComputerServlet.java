package com.computerDatabase.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.computerDatabase.excilys.model.Computer;
import com.computerDatabase.excilys.service.ComputerService;

/**
* Servlet implementation class Servlet
*/
@WebServlet("/ListComputerServlet")
public class ListComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ComputerService computerService = ComputerService.getInstance();
      
   public ListComputerServlet() {
       super();
   }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Je suis dans ListComputerServlet");
		this.getServletContext().getRequestDispatcher("/WEB-INF/listComputer.jsp").forward(request, response);
		List<Computer> computers = computerService.listService(1, 10);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
