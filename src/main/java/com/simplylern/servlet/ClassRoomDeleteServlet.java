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

import com.simplylern.model.ClassRoom;
import com.simplylern.service.ClassRoomServiceImpl;
import com.simplylern.service.UserServiceImpl;

/**
 * Servlet implementation class HomeServlet
 */
public class ClassRoomDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClassRoomServiceImpl classRoomServiceImpl = new ClassRoomServiceImpl();
	RequestDispatcher dispatcher = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassRoomDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String action1 = request.getServletPath();
		System.out.println("ACTION:" + action);
		System.out.println("ACTION1:" + action1);
		System.out.println("ServletPath:" + action);
		if(action != null && action.equals("add")) {
		        request.getRequestDispatcher("add-class-room.jsp").forward(request, response);
		}else if(action != null && action.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("We are going to delete class with ID:" + id);
			classRoomServiceImpl.delete(id);
			request.setAttribute("message", "Deleted Successfully");
			this.listClassRoom( request,  response);
		}else {
			this. listClassRoom( request,  response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		System.out.println("ServletPath:" + action);
//		if(action.equals("add") && request.getParameter("name") != null) {
//			if(classRoomServiceImpl.add(new ClassRoom(request.getParameter("name")))) {
//				this. listClassRoom( request,  response);
//			}
//		}else if(action.equals("delete") && request.getParameter("id") != null) {
//			int id = Integer.parseInt(request.getParameter("id"));
//			System.out.println("We are going to delete class with ID:" + id);
//			classRoomServiceImpl.delete(id);
//			request.setAttribute("message", "Deleted Successfully");
//		}else {
//			 request.getRequestDispatcher("add-class-room.jsp").forward(request, response);
//		}
		
	}
	
	private void listClassRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("message", "Welcome Learners Academy");
		request.setAttribute("data", classRoomServiceImpl.getAll());
		request.setAttribute("title", "Class Room");
		request.getRequestDispatcher("class-room.jsp").forward(request, response);
	}

}
