package com.simplylern.service;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.simplylern.model.User;
import com.simplylern.utils.HibernateUtil;


public class UserServiceImpl {
	
	Session session = null;
	
	
	public UserServiceImpl() {
		super();
		this.session = HibernateUtil.getSessionFactory().openSession();
	}
	void add(User user) {
		Transaction transaction = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(user);
            // commit transaction
            transaction.commit();
      
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}
	public boolean validate(String username, String password) {

        try {
        	System.out.println("Start validating username:" + username + " and passord:"+password);
            Query q = session.createQuery("select count(s) from User s where username = :un AND password = :pw");
            q.setParameter("un",username);
            q.setParameter("pw",password);
            Long count = (long)q.getSingleResult();
            System.out.println("Number Of Rows :" + count);
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	
	public User getUser(int id) {
		Transaction transaction = null;
		User user = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			user = session.get(User.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return user;
	}

	public void saveUser(User user) {
		Transaction transaction = null;
		User createdUser = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			// start a transaction
			transaction = session.beginTransaction();
			// save the classRoom object
			session.save(user);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<User> getAllClassRooms() {
		Transaction transaction = null;
		List<User> listOfClassRooms = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an classRoom object
			listOfClassRooms = session.createQuery("from User").getResultList();

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfClassRooms;
	}


}
