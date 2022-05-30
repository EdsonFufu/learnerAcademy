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

/**
 * Servlet implementation class HomeServlet
 */
public class SubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			if(request.getParameter("action") != null) {
				if (request.getParameter("action").equals("add")){ 
					RequestDispatcher dispatcher = request.getRequestDispatcher("add-subject.jsp");
					dispatcher.forward(request, response);
				}else if (request.getParameter("action").equals("edit") && request.getParameter("id") != null){
					String id = request.getParameter("id");
					System.out.println("Display Form To Edit Item With ID:" + id);
					Subject sub = new SubjectServiceImpl().getById(Integer.parseInt(id));
					System.out.println("Subject:"+sub.toString());
					request.setAttribute("item",sub);
					if(request.getParameter("action").equals("edit")){
						request.getRequestDispatcher("add-subject.jsp").forward(request, response);
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
		   }else {
			 request.getRequestDispatcher("add-subject.jsp").forward(request, response);
		   }
		}
		 RequestDispatcher dispatcher = request.getRequestDispatcher("add-subject.jsp");
	     dispatcher.include(request, response);
		
	}
	
	private void listClassRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("data", new SubjectServiceImpl().getAll());
		request.setAttribute("title", "Subject");
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("subject.jsp");
	     dispatcher.include(request, response);
	}
	private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("We are going to delete item with ID:" + id);
			new SubjectServiceImpl().delete(id);
			System.out.println("message Deleted Successfully");
			request.setAttribute("message", "Item with ID ["+ id +"] Deleted Successfully");
			request.setAttribute("data", new SubjectServiceImpl().getAll());
			request.setAttribute("title", "Subject");
			request.getRequestDispatcher("subject.jsp").include(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private void updateItem(HttpServletRequest request, HttpServletResponse response) {
		try {

			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("We are going to update subject with ID:" + id);
			Subject sub = new SubjectServiceImpl().getById(id);
			sub.setName(request.getParameter("name"));
			new SubjectServiceImpl().add(sub);
			request.setAttribute("message", "Item with ID ["+ id +"] Updated Successfully");
			request.setAttribute("data", new SubjectServiceImpl().getAll());
			request.setAttribute("title", "Subject");
			RequestDispatcher dispatcher = request.getRequestDispatcher("subject.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			if(new SubjectServiceImpl().add(new Subject(request.getParameter("name")))) {
				request.setAttribute("message", "Record Added Successfully");
		    }else {
		    	request.setAttribute("message", "Fail To Add an Item");
		    }
			request.setAttribute("data", new SubjectServiceImpl().getAll());
			request.setAttribute("title", "Subject");
			request.getRequestDispatcher("subject.jsp").forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
