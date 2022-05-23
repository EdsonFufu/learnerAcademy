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

		try {
			if(request.getParameter("action") != null) {
				if (request.getParameter("action").equals("add")){ 
					RequestDispatcher dispatcher = request.getRequestDispatcher("add-class-room.jsp");
					dispatcher.forward(request, response);
				}else if (request.getParameter("action").equals("edit") && request.getParameter("id") != null){
					String id = request.getParameter("id");
					System.out.println("Display Form To Edit Item With ID:" + id);
					ClassRoom cr = service.getById(Integer.parseInt(id));
					System.out.println("ClassRoom:"+cr.toString());
					request.setAttribute("item",cr);
					request.getRequestDispatcher("add-class-room.jsp").forward(request, response);
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

}
