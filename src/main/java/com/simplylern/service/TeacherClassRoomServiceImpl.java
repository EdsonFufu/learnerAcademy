package com.simplylern.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.simplylern.model.TeacherClassRoom;
import com.simplylern.utils.HibernateUtil;

public class TeacherClassRoomServiceImpl{
	Session session = null;
	public TeacherClassRoomServiceImpl() {
		super();
		this.session = HibernateUtil.getSessionFactory().openSession();
	}
	public int add(TeacherClassRoom tcr) {
		Transaction transaction = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            TeacherClassRoom tcr1 = (TeacherClassRoom) session.save(tcr);
            // commit transaction
            transaction.commit();
            return tcr1 != null ? 10 : 0;
      
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;
        }
        
	}
//	public void update(ClassRoom cRoom) {
//		Transaction transaction = null;
//        try {
//            // start a transaction
//            transaction = session.beginTransaction();
//            // save the student object
//             session.saveOrUpdate(cRoom);
//            // commit transaction
//            transaction.commit();
//      
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//        
//	}
//	public ClassRoom getById(int id) {
//		Transaction transaction = null;
//        ClassRoom classRoom = null;
//        try {
//        	// start a transaction
//            transaction = session.beginTransaction();
//        	classRoom = (ClassRoom) session.get(ClassRoom.class, id);
//            // commit transaction
//            transaction.commit();
//            return classRoom;
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//        return null;
//		
//	}
//	public List<ClassRoom> getAll(){
//		Transaction transaction = null;
//		 List<ClassRoom> classList  = null;
//        try {
//            // start a transaction
//            transaction = session.beginTransaction();
//            // get an user object
//            String hql = " FROM ClassRoom";
//            Query<ClassRoom> query =  session.createQuery(hql);
//
//            classList =  query.getResultList();
//            
////            for(ClassRoom class1:classList) {
////            	System.out.println(class1);
////            }
//            
//            // commit transaction
//            transaction.commit();
//            return classList;
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//        return null;
//		
//	}
//	public void delete(int id) {
//		System.out.println("Delete Class with ID:" + id);
//
//        Transaction transaction = null;
//        try {
//            // start a transaction
//            transaction = session.beginTransaction();
//
//            // Delete a user object
//            ClassRoom classRoom = session.get(ClassRoom.class, id);
//            if (classRoom != null) {
//                session.remove(classRoom);
//                System.out.println("ClassRoom is deleted");
//            }
//
//            // commit transaction
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//	}
}
