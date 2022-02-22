package com.simplylern.service;



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
            String hql = " FROM User U WHERE U.username = :userName";
            Query query =  session.createQuery(hql);
            query.setParameter("userName", username);

            user = (User) query.getSingleResult();
            
            if (user != null && user.getPassword().equals(password)) {
                return true;
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


}
