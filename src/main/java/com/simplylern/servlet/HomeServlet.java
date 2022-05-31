package com.simplylern.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


import com.simplylern.service.UserServiceImpl;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session=request.getSession(false); 
		  if(session == null) {
	    		request.setAttribute("message", "You have not Logged in");
				request.getRequestDispatcher("index.jsp").forward(request, response);
		  }
		  String un = (String)session.getAttribute("un");
	      if("".equals(un) || un == null) {
	    		request.setAttribute("message", "You have not Logged in");
				request.getRequestDispatcher("index.jsp").forward(request, response);
	      }
	      System.out.println("Hello "+un);  
		request.setAttribute("message", "Welcome Learners Academy");
		request.setAttribute("title", "Welcome Learners Academy");
		request.getRequestDispatcher("/home").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		
	}

}
