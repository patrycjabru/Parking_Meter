package database;

import entities.Ticket;
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

public class TicketDAO {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("DataSource");;
    private static EntityManager em = factory.createEntityManager();

    public static void add(Object o) {
        DatabaseOperationHelper.add(o, em);
    }

    public static Ticket getById(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = cb.createQuery(Ticket.class);
        Root<Ticket> hh = query.from(Ticket.class);
        List<Predicate> predicates = new LinkedList<Predicate>();

        predicates.add(cb.equal(hh.get("id"), id));

        query.where(predicates.toArray(new Predicate[] {}));

        List<Ticket> tickets = new LinkedList<Ticket>();
        try {
            TypedQuery<Ticket> q = em.createQuery(query);
            tickets = q.getResultList();
        }
        catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        if (tickets==null || tickets.size()!=1)
            return null;
        return tickets.get(0);
    }

    public static void deleteById(int id) {
        try {
            Ticket foundEmployee = em.find(Ticket.class, id);
            DatabaseOperationHelper.delete(foundEmployee, em);
        }
        catch (Exception e) {
            System.err.println("Error when trying to deleteById data from database: " + e);
            em.getTransaction().rollback();
        }
    }
}
