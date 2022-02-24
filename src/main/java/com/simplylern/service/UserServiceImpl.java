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

        Transaction transaction = null;
        User user = null;
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            String hql = " FROM User";
            Query<User> query =  session.createQuery(hql);

            List<User> userList =  query.getResultList();
            for(User user1:userList) {
            	System.out.println(user1);
            	if(user1.getUsername().equals(username) && user1.getPassword().equals(password)) {
            		return true;
            	}
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
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
