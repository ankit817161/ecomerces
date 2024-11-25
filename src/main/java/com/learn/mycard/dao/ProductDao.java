package com.learn.mycard.dao;

import com.learn.mycard.entities.Product;
import static java.lang.System.out;
import java.util.List;
import static org.hibernate.Hibernate.list;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ProductDao {

    private SessionFactory factory;

    public ProductDao(SessionFactory factory) {
        this.factory = factory;
    }

    public boolean saveProduct(Product product) {
        boolean f = false;
        try {
            Session session = this.factory.openSession();
            Transaction tx = session.beginTransaction();

            session.save(product);
            tx.commit();
            session.close();
            f = true;
        } catch (Exception e) {
            out.println(e);
            f = false;
        }
        return f;

    }

    // get all  product 
    public List<Product> getAllProducts() {
        Session s = this.factory.openSession();
        Query query = s.createQuery("from Product");
        List<Product> list = query.list();
        return list;
    }
    // get all  product  of given category

    public List<Product> getAllProductsById(int cid) {
        List<Product> list = null;
        try {
            Session s = this.factory.openSession();
            //Query query = s.createQuery("from Product as p where p.category.categoryId=: id");
            //query.setParameter("id", cid);
            Query query = s.createQuery("from Product p inner join fetch p.category where p.category.categoryId = :id");
            query.setParameter("id", cid);
            list = query.list();
            s.close();
        } catch (Exception e) {
            out.println(e);
        }
        
        return list;
    }

}
