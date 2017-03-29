package de.thaso.jd.db.store;

import de.thaso.jd.db.common.DatabaseError;
import de.thaso.jd.db.common.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;

/**
 * CustomerDAO
 *
 * @author thaler
 * @since 2017-03-22
 */
public class CustomerDAO {

    private final static Logger LOG = LoggerFactory.getLogger(CustomerDAO.class);

    @Inject
    private EntityManager entityManager;

    public CustomerEntity storeCustomer(final CustomerEntity customerEntity) {
        LOG.info("storeCustomer with id {} in name {}", customerEntity.getId(), customerEntity.getName());

        entityManager.persist(customerEntity);
        return customerEntity;
    }

    public CustomerEntity findCustomerById(final Long id) {
        LOG.info("findCustomerById {}", id);

        final CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, id);
        return customerEntity;
    }

    public CustomerEntity loadCustomerById(final Long id) throws DatabaseException {
        LOG.info("loadCustomerById {}", id);

        final CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, id);
        if (customerEntity == null) {
            throw new DatabaseException(DatabaseError.ENTITY_NOT_FOUND, "Customer with id " + id + " not found!");
        }
        return customerEntity;
    }

    public List<CustomerEntity> findByName(final String name) {
        LOG.info("findByName( {} )", name);

        final TypedQuery<CustomerEntity> query
                = entityManager.createNamedQuery(CustomerEntity.FIND_BY_NAME, CustomerEntity.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<CustomerEntity> findByIdentList(final List<String> identList) {
        LOG.info("findByIdentList( {} )", identList);

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<CustomerEntity> query = criteriaBuilder.createQuery(CustomerEntity.class);
        final Root<CustomerEntity> root = query.from(CustomerEntity.class);

        final Subquery<Object> subquery = query.subquery(Object.class);
        final Root<CustomerViewEntity> viewRoot = subquery.from(CustomerViewEntity.class);
        subquery.select(viewRoot.get("id"))
                .where(viewRoot.get("ident").in(identList));

        query.where(root.get("id").in(subquery));
        final List<CustomerEntity> resultList = entityManager.createQuery(query).getResultList();
        return resultList;
    }
}
