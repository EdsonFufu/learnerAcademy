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
import com.simplylern.model.Teacher;
import com.simplylern.model.TeacherClassRoom;
import com.simplylern.model.TeacherSubject;
import com.simplylern.service.ClassRoomServiceImpl;
import com.simplylern.service.SubjectServiceImpl;
import com.simplylern.service.TeacherClassRoomServiceImpl;
import com.simplylern.service.TeacherServiceImpl;
import com.simplylern.service.TeacherSubjectServiceImpl;
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

		try {
			if(request.getParameter("action") != null) {
				if (request.getParameter("action").equals("add")){ 
					RequestDispatcher dispatcher = request.getRequestDispatcher("add-teacher.jsp");
					dispatcher.forward(request, response);
				}else if ((request.getParameter("action").equals("edit") || request.getParameter("action").equals("assign-subjet")) && request.getParameter("id") != null){
					String id = request.getParameter("id");
					System.out.println("Display Form To Edit/Assign Item With ID:" + id);
					Teacher t = new TeacherServiceImpl().getById(Integer.parseInt(id));
					System.out.println(t.toString());
					request.setAttribute("item",t);
					if(request.getParameter("action").equals("edit")){
						System.out.println("Do Editing:" + id);
						request.getRequestDispatcher("add-teacher.jsp").forward(request, response);
					}else if(request.getParameter("action").equals("assign-subjet")) {
						System.out.println("Do Assign:" + id);
						request.setAttribute("subjects", new SubjectServiceImpl().getAll());
						request.getRequestDispatcher("subject_teacher.jsp").forward(request, response);
					}
				}else if (request.getParameter("action").equals("delete")){ 
					deleteItem(request, response);
				}
			}else{
				this.listTeacher(request, response);
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
		   }else if(request.getParameter("action").equals("assign-subject") && request.getParameter("subject_id") != null && request.getParameter("teacher_id") != null) {
			    assignSubject(request, response);
		   }else {
			 request.getRequestDispatcher("add-teacher.jsp").forward(request, response);
		   }
		}
		 RequestDispatcher dispatcher = request.getRequestDispatcher("add-teacher.jsp");
	     dispatcher.include(request, response);
		
	}
	
	private void listTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("data", new TeacherServiceImpl().getAll());
		request.setAttribute("title", "Teacher");
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("teacher.jsp");
	     dispatcher.include(request, response);
	}
	private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("We are going to delete teacher with ID:" + id);
			new TeacherServiceImpl().delete(id);
			System.out.println(" Deleted Successfully");
			request.setAttribute("message", "Item with ID ["+ id +"] Deleted Successfully");
			request.setAttribute("data", new TeacherServiceImpl().getAll());
			request.setAttribute("title", "Teacher");
			request.getRequestDispatcher("teacher.jsp").include(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private void updateItem(HttpServletRequest request, HttpServletResponse response) {
		try {

			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("We are going to update teacher with ID:" + id);
			Teacher t = new TeacherServiceImpl().getById(id);
			t.setName(request.getParameter("name"));
			new TeacherServiceImpl().add(t);
			request.setAttribute("message", "Item with ID ["+ id +"] Updated Successfully");
			request.setAttribute("data", new TeacherServiceImpl().getAll());
			request.setAttribute("title", "Class Room");
			RequestDispatcher dispatcher = request.getRequestDispatcher("teacher.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			if(new TeacherServiceImpl().add(new Teacher(request.getParameter("name")))) {
				request.setAttribute("message", "Record Added Successfully");
		    }else {
		    	request.setAttribute("message", "Fail To Add new Record");
		    }
			request.setAttribute("data", new TeacherServiceImpl().getAll());
			request.setAttribute("title", "Teacher");
			request.getRequestDispatcher("teacher.jsp").forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void assignSubject(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Starting Assign Subject");
		try {
			int subjectId = Integer.parseInt(request.getParameter("subject_id"));
			int teacherId = Integer.parseInt(request.getParameter("teacher_id"));
			System.out.println("Starting Assign Subject with ID:" + subjectId + " to Teacher ID:" + teacherId);
			
			TeacherSubject ts = new TeacherSubject(teacherId,subjectId);
			System.out.println(ts.toString());
			
			int affected = new TeacherSubjectServiceImpl().add(ts);
			
			if(affected > 0) {
				request.setAttribute("message", "Teacher with ID:" + teacherId + " assigned subject ID:" + subjectId +" successfully");
		    }else {
		    	request.setAttribute("message", "Failed to Assign Teacher with ID:" + teacherId + " subject ID:" + subjectId );
		    }
			request.setAttribute("data", new TeacherServiceImpl().getAll());
			request.setAttribute("title", "Teacher");
			RequestDispatcher dispatcher = request.getRequestDispatcher("teacher.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
