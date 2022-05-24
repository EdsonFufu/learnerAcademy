package com.simplylern.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.simplylern.model.ClassRoom;
import com.simplylern.model.Teacher;
import com.simplylern.utils.HibernateUtil;

public class TeacherServiceImpl{
	Session session = null;
	public TeacherServiceImpl() {
		super();
		this.session = HibernateUtil.getSessionFactory().openSession();
	}
	public boolean add(Teacher teacher) {
		Transaction transaction = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            int affected =(Integer) session.save(teacher);
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
	public Teacher getById(int id) {
		Transaction transaction = null;
        Teacher teacher = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            teacher = (Teacher) session.get(Teacher.class, id);
            // commit transaction
            transaction.commit();
            return teacher;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
		
	}
	public List<Teacher> getAll(){
		Transaction transaction = null;
		 List<Teacher> classList  = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            String hql = " FROM Teacher";
            Query<Teacher> query =  session.createQuery(hql);

            classList =  query.getResultList();
            
//            for(ClassRoom class1:classList) {
//            	System.out.println(class1);
//            }
            
            // commit transaction
            transaction.commit();
            return classList;
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
            Teacher teacher = session.get(Teacher.class, id);
            if (teacher != null) {
                session.delete(teacher);
                System.out.println("Teacher is deleted");
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
