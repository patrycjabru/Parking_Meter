package database;

import entities.Employee;
import entities.Zone;

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

public class EmployeeDAO {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("DataSource");;
    private static EntityManager em = factory.createEntityManager();

    public static void add(Object o) {
        DatabaseOperationHelper.add(o, em);
    }

    public static Employee getById(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> hh = query.from(Employee.class);
        List<Predicate> predicates = new LinkedList<Predicate>();

        predicates.add(cb.equal(hh.get("id"), id));

        query.where(predicates.toArray(new Predicate[] {}));

        List<Employee> employees = new LinkedList<Employee>();
        try {
            TypedQuery<Employee> q = em.createQuery(query);
            employees = q.getResultList();
        }
        catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        if (employees==null || employees.size()!=1)
            return null;
        return employees.get(0);
    }

    public static void deleteById(int id) {
        try {
            Employee foundEmployee = em.find(Employee.class, id);
            DatabaseOperationHelper.delete(foundEmployee, em);
        }
        catch (Exception e) {
            System.err.println("Error when trying to deleteById data from database: " + e);
            em.getTransaction().rollback();
        }
    }

    public static void updateZone(int id, int zone_id) {
        try {
            Employee foundEmployee = em.find(Employee.class, id);
            Zone foundZone = em.find(Zone.class, zone_id);

            em.getTransaction().begin();
            foundEmployee.setZone(foundZone);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
        }
    }
}
