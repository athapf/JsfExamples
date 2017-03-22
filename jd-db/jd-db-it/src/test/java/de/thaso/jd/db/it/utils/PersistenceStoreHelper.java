package de.thaso.jd.db.it.utils;

import de.thaso.jd.db.schema.PropertiesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

/**
 * PersistenceStoreHelper
 *
 * @author thaler
 * @since 2017-03-22
 */
public class PersistenceStoreHelper {

    public static final Logger LOG = LoggerFactory.getLogger(PersistenceStoreHelper.class);

    private static final EntityManagerFactory entityManagerFactory;
    private static final EntityManager entityManagerAud;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("testdb", getConnectionProperties());
        entityManagerAud = entityManagerFactory.createEntityManager();
    }

    public static Properties getConnectionProperties() {
      return PropertiesManager.readDevelopProperties();
  }

    public static EntityManager getEntityManager() {
    return entityManagerAud;
  }
}
