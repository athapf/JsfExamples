package de.thaso.jd.db.store.utils;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EntityManagerProducer
 *
 * @author thaler
 * @since 2017-03-22
 */
public class EntityManagerProducer {

    @PersistenceContext(unitName = "jddb")
    private EntityManager entityManager;

    @Produces
    public EntityManager createEntityManager() {
        return entityManager;
    }

    protected void closeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
