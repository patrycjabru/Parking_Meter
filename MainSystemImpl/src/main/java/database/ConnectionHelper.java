package database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionHelper {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("DataSource");;

    public static EntityManagerFactory getFactory() {
        return factory;
    }
}
