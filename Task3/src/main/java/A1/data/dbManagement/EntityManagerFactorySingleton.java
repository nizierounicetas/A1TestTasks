package A1.data.dbManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static final String PERSISTENCE_UNIT_NAME = "company_unit";
    private static EntityManagerFactory instance;

    public static synchronized EntityManagerFactory getInstance() {
        if (instance == null)
            instance = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        return instance;
    }

    public static synchronized void close() {
        if (instance != null && instance.isOpen())
            instance.close();

        instance = null;
    }
}
