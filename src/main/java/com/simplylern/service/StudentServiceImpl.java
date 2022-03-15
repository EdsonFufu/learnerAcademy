package com.simplylern.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.simplylern.model.Student;
import com.simplylern.utils.HibernateUtil;

public class StudentServiceImpl{
	Session session = null;
	public StudentServiceImpl() {
		super();
		this.session = HibernateUtil.getSessionFactory().openSession();
	}
	public boolean add(Student student) {
		Transaction transaction = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            int affected =(Integer) session.save(student);
            // commit transaction
            transaction.commit();
            return affected > 0;
      
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        
	}
	public Student getById(int id) {
		Transaction transaction = null;
        Student sdt = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            String hql = " FROM Student";
            Query<Student> query =  session.createQuery(hql);

            List<Student> classList =  query.getResultList();
            
            for(Student student:classList) {
            	System.out.println(student);
            	sdt = student;
            }
            // commit transaction
            transaction.commit();
            return sdt;
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
		 List<Student> studentList  = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            String hql = " FROM Student";
            Query<Student> query =  session.createQuery(hql);

            studentList =  query.getResultList();
            
            for(Student student:studentList) {
            	System.out.println(student);
            }
            
            // commit transaction
            transaction.commit();
            return studentList;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
		
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
