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
import com.simplylern.service.ClassRoomServiceImpl;
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

		try {
			if(request.getParameter("action") != null) {
				if (request.getParameter("action").equals("add")){ 
					RequestDispatcher dispatcher = request.getRequestDispatcher("add-student.jsp");
					dispatcher.forward(request, response);
				}else if ((request.getParameter("action").equals("edit") || request.getParameter("action").equals("assign-class-room")) && request.getParameter("id") != null){
					String id = request.getParameter("id");
					System.out.println("Display Form To Edit/Assign Item With ID:" + id);
					Student t = new StudentServiceImpl().getById(Integer.parseInt(id));
					System.out.println(t.toString());
					request.setAttribute("item",t);
					if(request.getParameter("action").equals("edit")){
						System.out.println("Do Editing:" + id);
						request.getRequestDispatcher("add-student.jsp").forward(request, response);
					}else if(request.getParameter("action").equals("assign-class-room")) {
						System.out.println("Do Assign:" + id);
						request.setAttribute("classRooms", new ClassRoomServiceImpl().getAll());
						request.getRequestDispatcher("class_room_student.jsp").forward(request, response);
					}
				}else if (request.getParameter("action").equals("delete")){ 
					deleteItem(request, response);
				}
			}else{
				this.listStudent(request, response);
			}
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("action")!= null) {
				
	       if(request.getParameter("action").equals("add") && request.getParameter("name") != null) {
	    	   this.addItem( request,  response);
		   }else if(request.getParameter("action").equals("edit") && request.getParameter("id") != null) {
			    updateItem(request, response);
		   }else if(request.getParameter("action").equals("assign-class-room") && request.getParameter("class_id") != null && request.getParameter("student_id") != null) {
			    assignClassRoom(request, response);
		   }else {
			 request.getRequestDispatcher("add-student.jsp").forward(request, response);
		   }
		}
		 RequestDispatcher dispatcher = request.getRequestDispatcher("add-student.jsp");
	     dispatcher.include(request, response);
		
	}
	
	private void listStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("data", new StudentServiceImpl().getAll());
		request.setAttribute("title", "Student");
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("student.jsp");
	     dispatcher.include(request, response);
	}
	private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("We are going to delete Student with ID:" + id);
			new StudentServiceImpl().delete(id);
			System.out.println(" Deleted Successfully");
			request.setAttribute("message", "Item with ID ["+ id +"] Deleted Successfully");
			request.setAttribute("data", new StudentServiceImpl().getAll());
			request.setAttribute("title", "Student");
			request.getRequestDispatcher("student.jsp").include(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private void updateItem(HttpServletRequest request, HttpServletResponse response) {
		try {

			int id = Integer.parseInt(request.getParameter("id"));
			int sid = Integer.parseInt(request.getParameter("studentId"));
			System.out.println("We are going to update student with ID:" + id);
			Student st = new StudentServiceImpl().getById(id);
			st.setName(request.getParameter("name"));
			//st.setName(request.getParameter("studentId"));
			new StudentServiceImpl().update(st);
			request.setAttribute("message", "Item with ID ["+ id +"] Updated Successfully");
			request.setAttribute("data", new StudentServiceImpl().getAll());
			request.setAttribute("title", "Class Room");
			RequestDispatcher dispatcher = request.getRequestDispatcher("student.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			if(new StudentServiceImpl().add(new Student(request.getParameter("studentId"),request.getParameter("name"))) > 0) {
				request.setAttribute("message", "Record Added Successfully");
		    }else {
		    	request.setAttribute("message", "Fail To Add new Record");
		    }
			request.setAttribute("data", new StudentServiceImpl().getAll());
			request.setAttribute("title", "Student");
			request.getRequestDispatcher("student.jsp").forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void assignClassRoom(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Starting Assign Subject");
		try {
			String classId = request.getParameter("class_id");
			int studentId = Integer.parseInt(request.getParameter("student_id"));
			System.out.println("Starting Assign ClassRoom with ID:" + classId + " to Student ID:" + studentId);
			
			Student st = new StudentServiceImpl().getById(studentId);
			System.out.println(st.toString());
			st.setClassId(classId);
			
			int affected = new StudentServiceImpl().add(st);
			
			if(affected > 0) {
				request.setAttribute("message", "Student with ID:" + studentId + " assigned class ID:" + classId +" successfully");
		    }else {
		    	request.setAttribute("message", "Failed to Assign Student with ID:" + studentId + " class ID:" + classId );
		    }
			request.setAttribute("data", new StudentServiceImpl().getAll());
			request.setAttribute("title", "Student");
			RequestDispatcher dispatcher = request.getRequestDispatcher("student.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


}
