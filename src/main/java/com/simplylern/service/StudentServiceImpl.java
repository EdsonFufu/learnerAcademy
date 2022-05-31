package com.simplylern.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.simplylern.model.ClassRoom;
import com.simplylern.model.Student;
import com.simplylern.model.StudentList;
import com.simplylern.utils.HibernateUtil;

public class StudentServiceImpl{
	Session session = null;
	public StudentServiceImpl() {
		super();
		this.session = HibernateUtil.getSessionFactory().openSession();
	}
	public int add(Student student) {
		Transaction transaction = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            int affected =(Integer) session.save(student);
            // commit transaction
            transaction.commit();
            return affected;
      
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;
        }
        
	}
	public void update(Student st) {
		Transaction transaction = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
             session.saveOrUpdate(st);
            // commit transaction
            transaction.commit();
      
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
	}

	public Student getById(int id) {
		Transaction transaction = null;
		Student student = null;
	    try {
	    	// start a transaction
	        transaction = session.beginTransaction();
	    	student = (Student) session.get(Student.class, id);
	        // commit transaction
	        transaction.commit();
	        return student;
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return null;
		
	}
	public List<Student> getAll(){
		Transaction transaction = null;
		 List<Student> stList  = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            String hql = " FROM Student";
            Query<Student> query =  session.createQuery(hql);

            stList =  query.getResultList();
            
            transaction.commit();
            return stList;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
		
	}
	public List<StudentList> getStudentMasterList(){
		 List<StudentList> studentMasterList  = new ArrayList<>();
        try {
            String hql = " FROM Student";
            Query<Student> query =  session.createQuery(hql);

            List<Student> studentLst =  query.getResultList();
            if(studentLst.size() > 0) {
            	for(Student s:studentLst) {
            		if(s.getClassId() != null) {
            		ClassRoom cr = new ClassRoomServiceImpl().getById(Integer.parseInt(s.getClassId()));
            		if(cr != null) {
            			studentMasterList.add(new StudentList(s.getStudentId(),s.getName(),cr.getName()));
            		}else {
            			studentMasterList.add(new StudentList(s.getStudentId(),s.getName(),"Not Assigned"));
            		}
            		}else {
            			studentMasterList.add(new StudentList(s.getStudentId(),s.getName(),"Not Assigned"));
            		}
            	}
            	
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentMasterList;
		
	}
	public void delete(int id) {

        Transaction transaction = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a user object
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
                System.out.println("Student is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}
}
