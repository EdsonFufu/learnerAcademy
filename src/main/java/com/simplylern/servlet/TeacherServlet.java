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

import com.simplylern.model.Teacher;
import com.simplylern.service.TeacherServiceImpl;
import com.simplylern.service.UserServiceImpl;

/**
 * Servlet implementation class HomeServlet
 */
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl();
	RequestDispatcher dispatcher = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherServlet() {
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
		        request.getRequestDispatcher("add-teacher.jsp").forward(request, response);
		}else {
			this. listTeacher( request,  response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		//System.out.println("ServletPath:" + action);
		if(action.equals("add") && request.getParameter("name") != null) {
			if(teacherServiceImpl.add(new Teacher(request.getParameter("name")))) {
				this. listTeacher( request,  response);
			}
		}else {
			 request.getRequestDispatcher("add-teacher.jsp").forward(request, response);
		}
		
	}
	
	private void listTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("message", "Welcome Learners Academy");
		request.setAttribute("data", teacherServiceImpl.getAll());
		request.setAttribute("title", "Teacher");
		request.getRequestDispatcher("teacher.jsp").forward(request, response);
	}

}
