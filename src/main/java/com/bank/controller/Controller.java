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
		case "addStudent":
		    // Handle form data and save
		    Student student = new Student();
		    System.out.println("Student being created...");
		    student.setStudentName(request.getParameter("name"));
		    student.setStudentEmail(request.getParameter("email"));
		    student.setStudentPhone(request.getParameter("phone"));
		    student.setStudentAddress(request.getParameter("address"));
		    if(bankDAO.createStudent(student)) {
		    	response.sendRedirect("index.jsp?action=listStudents");
		    }
		    else {
		    	request.setAttribute("error", "Could not add student.");
		    	request.getRequestDispatcher("Controller?action=listStudents").forward(request, response);
		    }
		    break;
		case "addAccount":
			//TODO: Implement Account Creation Handler
			break;
		case "transfer":
			//TODO: Implement Transfer Handler
			break;
		case "withdraw":
			//TODO: implement withdraw handler
			break;
		default:
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown POST action: " + action);
		}

	}

	private void processGETRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		List<Student> students = bankDAO.getAllStudents();
			List<Account> accounts = bankDAO.getAllAccounts();
		//Handle Actions here:
		switch(action) {
		/*
		 * Handle GET Requests Here
		 */
	
// --------------- Home Page ---------------------
		case "home":
            request.getRequestDispatcher("jsp/home/home.jsp").forward(request, response);
			break;

		case "login":
		    request.setAttribute("students", students);
		    request.getRequestDispatcher("jsp/home/login.jsp").forward(request, response);			
		    break;

// --------------- Students ---------------------
		case "listStudents":
			//do the listing of the students
            request.setAttribute("Students", students);
            request.getRequestDispatcher("jsp/students/ListStudents.jsp").forward(request, response);
            break;

		case "addStudent":
			request.getRequestDispatcher("jsp/students/AddStudent.jsp").forward(request, response);
			break;
// --------------- Accounts ---------------------
		case "listAccounts": // Read
			request.setAttribute("Accounts", accounts);
			request.getRequestDispatcher("jsp/accounts/ListAccounts.jsp").forward(request, response);
			break;

		case "addAccount": //Create
			request.getRequestDispatcher("jsp/accounts/AddAccount.jsp").forward(request, response);
			break;

		case "deleteAccount": // Delete
			break;

// --------------- Business Logic ---------------------

		case "transfer":
			request.getRequestDispatcher("jsp/transactions/Transfer.jsp").forward(request, response);
			break;

		case "depositOrWithdraw":
			request.getRequestDispatcher("jsp/transactions/DepositOrWithdraw.jsp").forward(request, response);
			break;
		//etc...
		}
	}
}
