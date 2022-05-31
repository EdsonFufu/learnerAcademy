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
import com.simplylern.model.TeacherClassRoom;
import com.simplylern.service.TeacherClassRoomServiceImpl;
import com.simplylern.service.ClassRoomServiceImpl;
import com.simplylern.service.SubjectServiceImpl;
import com.simplylern.service.TeacherServiceImpl;
import com.simplylern.service.UserServiceImpl;

/**
 * Servlet implementation class HomeServlet
 */
public class ClassRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClassRoomServiceImpl classRoomServiceImpl = new ClassRoomServiceImpl();
	private ClassRoomServiceImpl service = new ClassRoomServiceImpl();
	RequestDispatcher dispatcher = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassRoomServlet() {
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

		try {
			if(request.getParameter("action") != null) {
				if (request.getParameter("action").equals("add")){ 
					RequestDispatcher dispatcher = request.getRequestDispatcher("add-class-room.jsp");
					dispatcher.forward(request, response);
				}else if ((request.getParameter("action").equals("edit") || request.getParameter("action").equals("assign-teacher")) && request.getParameter("id") != null){
					String id = request.getParameter("id");
					System.out.println("Display Form To Edit Item With ID:" + id);
					ClassRoom cr = service.getById(Integer.parseInt(id));
					System.out.println("ClassRoom:"+cr.toString());
					request.setAttribute("item",cr);
					if(request.getParameter("action").equals("edit")){
						request.getRequestDispatcher("add-class-room.jsp").forward(request, response);
					}else if(request.getParameter("action").equals("assign-teacher")) {
						request.setAttribute("teachers", new TeacherServiceImpl().getAll());
						request.getRequestDispatcher("teacher_class_room.jsp").forward(request, response);
					}else if(request.getParameter("action").equals("assign-subject")) {
						request.setAttribute("subjects", new SubjectServiceImpl().getAll());
						request.getRequestDispatcher("subject_teacher.jsp").forward(request, response);
					}
				}else if (request.getParameter("action").equals("delete")){ 
					deleteItem(request, response);
				}
			}else{
				this.listClassRoom(request, response);
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
		   }else if(request.getParameter("action").equals("assign-teacher") && request.getParameter("class_id") != null && request.getParameter("teacher_id") != null) {
			    assignTeacher(request, response);
		   }else {
			 request.getRequestDispatcher("add-class-room.jsp").forward(request, response);
		   }
		}
		 RequestDispatcher dispatcher = request.getRequestDispatcher("add-class-room.jsp");
	     dispatcher.include(request, response);
		
	}
	
	private void listClassRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("data", classRoomServiceImpl.getAll());
		request.setAttribute("title", "Class Room");
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("class-room.jsp");
	     dispatcher.include(request, response);
	}
	private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("We are going to delete class with ID:" + id);
			service.delete(id);
			System.out.println("message Deleted Successfully");
			request.setAttribute("message", "Item with ID ["+ id +"] Deleted Successfully");
			request.setAttribute("data", classRoomServiceImpl.getAll());
			request.setAttribute("title", "Class Room");
			request.getRequestDispatcher("class-room.jsp").include(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private void updateItem(HttpServletRequest request, HttpServletResponse response) {
		try {

			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("We are going to update class with ID:" + id);
			ClassRoom cr = service.getById(id);
			cr.setName(request.getParameter("name"));
			service.add(cr);
			request.setAttribute("message", "Item with ID ["+ id +"] Updated Successfully");
			request.setAttribute("data", new ClassRoomServiceImpl().getAll());
			request.setAttribute("title", "Class Room");
			RequestDispatcher dispatcher = request.getRequestDispatcher("class-room.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			int affected = classRoomServiceImpl.add(new ClassRoom(request.getParameter("name")));
			if(affected > 0) {
				request.setAttribute("message", "["+ affected +"] Record Added Successfully");
		    }else {
		    	request.setAttribute("message", "Fail To Add an Item");
		    }
			request.setAttribute("data", classRoomServiceImpl.getAll());
			request.setAttribute("title", "Class Room");
			request.getRequestDispatcher("class-room.jsp").forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void assignTeacher(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Starting Assign Teacher");
		try {
			int classId = Integer.parseInt(request.getParameter("class_id"));
			int teacherId = Integer.parseInt(request.getParameter("teacher_id"));
			System.out.println("Starting Assign Teacher with ID:" + teacherId + " to class ID:" + classId);
			
			TeacherClassRoom tcr = new TeacherClassRoom(teacherId,classId);
			System.out.println(tcr.toString());
			
			int affected = new TeacherClassRoomServiceImpl().add(tcr);
			
			
			if(affected > 0) {
				request.setAttribute("message", "Teacher with ID:" + teacherId + " assigned to class ID:" + classId +" successfully");
		    }else {
		    	request.setAttribute("message", "Failed to Assign Teacher with ID:" + teacherId + " to class ID:" + classId );
		    }
			request.setAttribute("data", new ClassRoomServiceImpl().getAll());
			request.setAttribute("title", "Class Room");
			RequestDispatcher dispatcher = request.getRequestDispatcher("class-room.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
