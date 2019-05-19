package database;

import entities.ParkingSpot;
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

public class ParkingSpotDAO {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("DataSource");;
    private static EntityManager em = factory.createEntityManager();

    public void add(Object o, int zone_id) {
        try {
            Zone foundEmployee = em.find(Zone.class, zone_id);
        } catch (Exception e) {
            System.err.println("Error when trying to deleteById data from database: " + e);
            em.getTransaction().rollback();
        }
    }

    public ParkingSpot getById(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ParkingSpot> query = cb.createQuery(ParkingSpot.class);
        Root<ParkingSpot> hh = query.from(ParkingSpot.class);
        List<Predicate> predicates = new LinkedList<Predicate>();

        predicates.add(cb.equal(hh.get("id"), id));

        query.where(predicates.toArray(new Predicate[] {}));

        List<ParkingSpot> spots = new LinkedList<ParkingSpot>();
        try {
            TypedQuery<ParkingSpot> q = em.createQuery(query);
            spots = q.getResultList();
        }
        catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        if (spots==null || spots.size()!=1)
            return null;
        return spots.get(0);
    }

    public void deleteById(int id) {
        try {
            ParkingSpot foundSpot = em.find(ParkingSpot.class, id);
            DatabaseOperationHelper.delete(foundSpot, em);
        }
        catch (Exception e) {
            System.err.println("Error when trying to deleteById data from database: " + e);
            em.getTransaction().rollback();
        }
    }
}
