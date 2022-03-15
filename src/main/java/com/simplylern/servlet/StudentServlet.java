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

import com.simplylern.model.Student;
import com.simplylern.service.StudentServiceImpl;
import com.simplylern.service.UserServiceImpl;

/**
 * Servlet implementation class HomeServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentServiceImpl studentServiceImpl = new StudentServiceImpl();
	RequestDispatcher dispatcher = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
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
		        request.getRequestDispatcher("add-student.jsp").forward(request, response);
		}else {
			this. listStudent( request,  response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		//System.out.println("ServletPath:" + action);
		if(action.equals("add") && request.getParameter("name") != null) {
			if(studentServiceImpl.add(new Student(request.getParameter("studentId"),request.getParameter("name")))) {
				this. listStudent( request,  response);
			}
		}else {
			 request.getRequestDispatcher("add-student.jsp").forward(request, response);
		}
		
	}
	
	private void listStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("message", "Welcome Learners Academy");
		request.setAttribute("data", studentServiceImpl.getAll());
		request.setAttribute("title", "Students");
		request.getRequestDispatcher("student.jsp").forward(request, response);
	}

}
