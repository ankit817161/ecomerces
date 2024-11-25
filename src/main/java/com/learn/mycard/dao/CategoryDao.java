package com.learn.mycard.dao;

import com.learn.mycard.entities.Category;
import static java.lang.System.out;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

//  this is dao class in category
public class CategoryDao {

    private SessionFactory factory;

    public CategoryDao(SessionFactory factory) {
        this.factory = factory;
    }

    // save the category in db
    public int saveCategory(Category cat) {
        Session session = this.factory.openSession();
        Transaction tx = session.beginTransaction();

        int catId = (int) session.save(cat);
        tx.commit();
        session.close();
        return catId;
    }

    public List<Category> getCategorise() {
        Session s = this.factory.openSession();
        Query q = s.createQuery("from Category");
        List<Category> list = q.list();
        return list;

    }

    public Category getCategoryById(int cid) {
        Category cat=null;
        try {
            Session session = this.factory.openSession();
            cat = session.get(Category.class, cid);
            session.close();
        } catch (Exception e) {
            out.println(e); 
        }
        return cat;
    }

}
