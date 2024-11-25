
package com.learn.mycard.dao;

import com.learn.mycard.entities.User;
import static java.lang.System.out;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


public class UserDao {
    private SessionFactory factory;

    public UserDao(SessionFactory factory) {
        this.factory = factory;
    }

   // get user by email and password
    public User getUserByEmailAndPassword(String email,String password){
        User user=null;
        
         try{
           String q=" from User where userEmail =:e and userPassword =:p";
            Session openSession = this.factory.openSession();
            Query qr = openSession.createQuery(q);
            qr.setParameter("e", email);
            qr.setParameter("p", password);
            user =(User) qr.uniqueResult();
            
            
            openSession.close();
            
          }catch(Exception e){
           e.printStackTrace(); 
            }
         return user;
    }
            
    
    
}
