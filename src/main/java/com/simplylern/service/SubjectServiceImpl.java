package com.simplylern.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.simplylern.model.Subject;
import com.simplylern.model.Teacher;
import com.simplylern.utils.HibernateUtil;

public class SubjectServiceImpl{
	Session session = null;
	public SubjectServiceImpl() {
		super();
		this.session = HibernateUtil.getSessionFactory().openSession();
	}
	public boolean add(Subject sub) {
		Transaction transaction = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            int affected =(Integer) session.save(sub);
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
	public Subject getById(int id) {
		Transaction transaction = null;
		Subject sub = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            sub = (Subject) session.get(Subject.class, id);
            // commit transaction
            transaction.commit();
            return sub;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
		
	}
	public List<Subject> getAll(){
		Transaction transaction = null;
		 List<Subject> subjectList  = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            String hql = " FROM Subject";
            Query<Subject> query =  session.createQuery(hql);

            subjectList =  query.getResultList();
            
            for(Subject subject:subjectList) {
            	System.out.println(subject);
            }
            
            // commit transaction
            transaction.commit();
            return subjectList;
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
            Subject subject = session.get(Subject.class, id);
            if (subject != null) {
                session.delete(subject);
                System.out.println("Subject is deleted");
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
