package com.bank.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.bank.dao.BankDAO;
import com.bank.model.*;
import java.util.List;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
    private BankDAO bankDAO;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processGETRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processPOSTRequest(request, response);
	}

	private void processPOSTRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action)	{
		/*
		 * Handle POST Requests here:
		 */
		case "transfer":
			//implement transfer handler
			//TODO: Get current account from context, get target account from
			// POST form, set attributes then redirect back to page.
			break;
		case "withdraw":
			break;
			//implement withdraw handler
		}

	}

	private void processGETRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		//Handle Actions here:
		switch(action) {
		/*
		 * Handle GET Requests Here
		 */
		case "home":
            request.getRequestDispatcher("jsp/home.jsp").forward(request, response);
			break;
		case "listStudents":
			//do the listing of the students
			List<Student> students = bankDAO.getAllStudents();
            request.setAttribute("Students", students);
            request.getRequestDispatcher("jsp/ListStudents.jsp").forward(request, response);
        
            break;
		case "listAccounts":
			List<Account> accounts = bankDAO.getAllAccounts();
			request.setAttribute("Accounts", accounts);
			request.getRequestDispatcher("jsp/ListAccounts.jsp").forward(request, response);
			break;
		case "transfer":
			request.getRequestDispatcher("jsp/transfer/Transfer.jsp").forward(request, response);
			break;
		case "withdraw":
			request.getRequestDispatcher("jsp/withdraw/Withdraw.jsp").forward(request, response);
			break;
		//etc...
		}
	}
}
