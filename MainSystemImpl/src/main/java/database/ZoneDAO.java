package database;

import entities.Zone;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class ZoneDAO {
    private static EntityManager em = ConnectionHelper.getFactory().createEntityManager();

    public static void add(Object o) {
        DatabaseOperationHelper.add(o, em);
    }

    public static void update(Object o){
        DatabaseOperationHelper.update(o, em);
    }

    public static Zone getById(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Zone> query = cb.createQuery(Zone.class);
        Root<Zone> hh = query.from(Zone.class);
        List<Predicate> predicates = new LinkedList<Predicate>();

        predicates.add(cb.equal(hh.get("id"), id));

        query.where(predicates.toArray(new Predicate[] {}));

        List<Zone> zones = new LinkedList<Zone>();
        try {
            TypedQuery<Zone> q = em.createQuery(query);
            zones = q.getResultList();
        }
        catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        if (zones==null || zones.size()!=1)
            return null;
        return zones.get(0);
    }

    public static void deleteById(int id) {
        try {
            Zone foundEmployee = em.find(Zone.class, id);
            DatabaseOperationHelper.delete(foundEmployee, em);
        }
        catch (Exception e) {
            System.err.println("Error when trying to deleteById data from database: " + e);
            em.getTransaction().rollback();
        }
    }

    public static List<Zone> getWithoutEmployeeID() {
        List<Zone> result = null;
        try {
            TypedQuery<Zone> q = em.createQuery("SELECT z FROM zone z WHERE z.employee = NULL", Zone.class);
            result = q.getResultList();
        }
        catch (Exception e) {
            System.err.println("Error when trying to deleteById data from database: " + e);
            em.getTransaction().rollback();
        }
        return result;
    }
}
