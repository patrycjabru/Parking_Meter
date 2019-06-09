package database;

import entities.Employee;
import entities.ParkingSpot;
import entities.Ticket;
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
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ParkingSpotDAO {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("DataSource");;
    private static EntityManager em = factory.createEntityManager();

    public static void add(Object o, int zone_id) {
        try {
            Zone foundEmployee = em.find(Zone.class, zone_id);
        } catch (Exception e) {
            System.err.println("Error when trying to deleteById data from database: " + e);
            em.getTransaction().rollback();
        }
    }

    public static List<ParkingSpot> getAll() {
        List<ParkingSpot> spots = new LinkedList<ParkingSpot>();
        try {
            TypedQuery<ParkingSpot> q = em.createQuery("SELECT s FROM parking_spot s", ParkingSpot.class);
            spots = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return spots;
    }

    public static ParkingSpot getById(int id) {
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

    public static void deleteById(int id) {
        try {
            ParkingSpot foundSpot = em.find(ParkingSpot.class, id);
            DatabaseOperationHelper.delete(foundSpot, em);
        }
        catch (Exception e) {
            System.err.println("Error when trying to deleteById data from database: " + e);
            em.getTransaction().rollback();
        }
    }

    public static void setSpotAsOccupied(int spot_id) {
        try{
            ParkingSpot foundSpot = em.find(ParkingSpot.class, spot_id);
            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);

            em.getTransaction().begin();
            foundSpot.setArrivalTime(ts);
            foundSpot.setFree(false);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
        }
    }

    public static void setSpotAsFree(int spot_id) {
        try{
            ParkingSpot foundSpot = em.find(ParkingSpot.class, spot_id);

            em.getTransaction().begin();
            foundSpot.setArrivalTime(null);
            foundSpot.setFree(true);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
        }
    }


    public static List<ParkingSpot> getSpotsByEmployeeId(int employeeId) {
        Employee employee = EmployeeDAO.getById(employeeId);
        Zone zone = employee.getZone();

        List<ParkingSpot> spots = new LinkedList<ParkingSpot>();
        try {
            TypedQuery<ParkingSpot> q = em
                    .createQuery("SELECT p FROM parking_spot p WHERE p.zone = :zone",ParkingSpot.class)
                    .setParameter("zone", zone);
            spots = q.getResultList();
        }
        catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return spots;
    }

    public static List<ParkingSpot> getAllSpots(){
        List<ParkingSpot> spots = new LinkedList<ParkingSpot>();
        try {
            TypedQuery<ParkingSpot> q = em.createQuery("SELECT p FROM parking_spot p",ParkingSpot.class);
            spots = q.getResultList();
        }
        catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return spots;
    }

    public static boolean checkIfPaid(ParkingSpot spot) {
        List<Ticket> tickets = spot.getTickets();
        Date now = new Date();
        for (Ticket t : tickets) {
            if (t.getEndTime().after(now))
                return true;
        }
        return false;
    }

    public static Double getPercentOfEmptySpots(){
        Double res = 0.0;
        try {
            TypedQuery<Double> q = em.createQuery("SELECT COUNT(p) FROM parking_spot p", Double.class);
            Double all = q.getSingleResult();
            q = em.createQuery("SELECT COUNT(p) FROM parking_spot p WHERE p.isFree = true",Double.class);
            res = q.getSingleResult();
            res = res/all;
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return res;
    }

    public static Double getPercentOfEmptySpotsForZone(Zone zone) {
        Double res = 0.0;
        try {
            TypedQuery<Double> q = em.createQuery("SELECT COUNT(p) FROM parking_spot p WHERE zone = :zone", Double.class).setParameter("zone", zone);
            Double all = q.getSingleResult();
            q = em.createQuery("SELECT COUNT(p) FROM parking_spot p WHERE p.isFree = true AND zone = :zone",Double.class).setParameter("zone", zone);
            res = q.getSingleResult();
            res = res/all;
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return res;
    }
}
