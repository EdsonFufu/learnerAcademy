package com.simplylern.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.simplylern.model.ClassRoom;
import com.simplylern.utils.HibernateUtil;

public class ClassRoomServiceImpl{
	Session session = null;
	public ClassRoomServiceImpl() {
		super();
		this.session = HibernateUtil.getSessionFactory().openSession();
	}
	public boolean add(ClassRoom cRoom) {
		Transaction transaction = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            int affected =(Integer) session.save(cRoom);
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
	public ClassRoom getById(int id) {
		Transaction transaction = null;
        ClassRoom classRoom = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            String hql = " FROM ClassRoom";
            Query<ClassRoom> query =  session.createQuery(hql);

            List<ClassRoom> classList =  query.getResultList();
            
            for(ClassRoom class1:classList) {
            	System.out.println(class1);
            	classRoom = class1;
            }
            // commit transaction
            transaction.commit();
            return classRoom;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
		
	}
	public List<ClassRoom> getAll(){
		Transaction transaction = null;
		 List<ClassRoom> classList  = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            String hql = " FROM ClassRoom";
            Query<ClassRoom> query =  session.createQuery(hql);

            classList =  query.getResultList();
            
            for(ClassRoom class1:classList) {
            	System.out.println(class1);
            }
            
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
            ClassRoom classRoom = session.get(ClassRoom.class, id);
            if (classRoom != null) {
                session.delete(classRoom);
                System.out.println("ClassRoom is deleted");
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
