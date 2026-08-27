package vn.iotstar.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

@PersistenceContext
public class JpaConfig {
    private static EntityManagerFactory factory;
    private static Boolean isDbAvailable = null;

    public static boolean checkDbOnline() {
        if (isDbAvailable != null && !isDbAvailable) {
            return false;
        }
        try (java.net.Socket s = new java.net.Socket()) {
            s.connect(new java.net.InetSocketAddress("127.0.0.1", 1433), 200);
            isDbAvailable = true;
            return true;
        } catch (Exception e) {
            isDbAvailable = false;
            return false;
        }
    }

    public static EntityManager getEntityManager() {
        if (!checkDbOnline()) {
            return null;
        }
        try {
            if (factory == null || !factory.isOpen()) {
                factory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
            }
            return factory.createEntityManager();
        } catch (Exception e) {
            isDbAvailable = false;
            return null;
        }
    }
}
