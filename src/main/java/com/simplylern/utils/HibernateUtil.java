/**
 * 
 */
package com.simplylern.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.simplylern.model.ClassRoom;
import com.simplylern.model.Student;
import com.simplylern.model.Subject;
import com.simplylern.model.Teacher;
import com.simplylern.model.User;
import com.simplylern.model.TeacherClassRoom;
import com.simplylern.model.TeacherSubject;


/**
 * @author edson.fufu
 *
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        
    		
    		if(sessionFactory ==  null) {
    			try {
    				Configuration configuration = new Configuration();
    				
    				Properties hibernateProperties = new Properties();
    				hibernateProperties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
    				hibernateProperties.put(Environment.URL, "jdbc:mysql://localhost:3306/learners_academy_db?useSSL=false");
    				hibernateProperties.put(Environment.USER, "root");
    				hibernateProperties.put(Environment.PASS, "P@55w0rd");
    				hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
    				hibernateProperties.put(Environment.SHOW_SQL, "true");
    				hibernateProperties.put(Environment.FORMAT_SQL, "true");
    				hibernateProperties.put(Environment.HBM2DDL_AUTO, "update");
    				configuration.setProperties(hibernateProperties);
    				
    				configuration.addAnnotatedClass(User.class);
    				configuration.addAnnotatedClass(ClassRoom.class);
    				configuration.addAnnotatedClass(Student.class);
    				configuration.addAnnotatedClass(Teacher.class);
    				configuration.addAnnotatedClass(Subject.class);
    				configuration.addAnnotatedClass(TeacherClassRoom.class);
    				configuration.addAnnotatedClass(TeacherSubject.class);
    			
    				
    				
    				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
    						.applySettings(configuration.getProperties()).build();
    				
    				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    			}
    			catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		return sessionFactory;
    
    }
}