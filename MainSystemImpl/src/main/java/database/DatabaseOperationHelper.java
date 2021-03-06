package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class DatabaseOperationHelper {
    public static void add (Object obj, EntityManager em) {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            System.out.println("Added to database" + obj);
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to add data to database: " + e);
        }
    }

    public static void update (Object obj, EntityManager em){
        try{
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data to database: " + e);
        }
    }

    public static void delete (Object obj, EntityManager em) {
        em.getTransaction().begin();
        em.remove(obj);
        em.getTransaction().commit();
    }
}
