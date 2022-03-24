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

import com.simplylern.model.Subject;
import com.simplylern.service.SubjectServiceImpl;
import com.simplylern.service.UserServiceImpl;

/**
 * Servlet implementation class HomeServlet
 */
public class SubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SubjectServiceImpl subjectServiceImpl = new SubjectServiceImpl();
	RequestDispatcher dispatcher = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("ServletPath:" + action);
		if(action != null && action.equals("add")) {
		        request.getRequestDispatcher("add-subject.jsp").forward(request, response);
		}else {
			this. listSubject( request,  response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		//System.out.println("ServletPath:" + action);
		if(action.equals("add") && request.getParameter("name") != null) {
			if(subjectServiceImpl.add(new Subject(request.getParameter("name")))) {
				this. listSubject( request,  response);
			}
		}else {
			 request.getRequestDispatcher("add-subject.jsp").forward(request, response);
		}
		
	}
	
	private void listSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("message", "Welcome Learners Academy");
		request.setAttribute("data", subjectServiceImpl.getAll());
		request.setAttribute("title", "Class Room");
		request.getRequestDispatcher("subject.jsp").forward(request, response);
	}

}
