package de.thaso.jd.db.store;

import de.thaso.jd.db.common.DatabaseError;
import de.thaso.jd.db.common.DatabaseException;
import de.thaso.jd.db.store.utils.DatabaseExceptionCodeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.persistence.EntityManager;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * CustomerDAOTest
 *
 * @author thaler
 * @since 2017-03-22
 */
public class CustomerDAOTest {

    public static final DatabaseExceptionCodeMatcher EXCEPTION_MATCHER_ENTITY_NOT_FOUND
            = new DatabaseExceptionCodeMatcher(DatabaseError.ENTITY_NOT_FOUND);

    @InjectMocks
    private CustomerDAO underTest;

    @Mock
    private EntityManager entityManager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Long primaryKey;
    private CustomerEntity customerEntity;

    @Before
    public void setUp() {
        initMocks(this);

        primaryKey = 1L;
        customerEntity = new CustomerEntity();
        when(entityManager.find(CustomerEntity.class, primaryKey)).thenReturn(customerEntity);
    }

    @Test
    public void storeCustomer() {
        // when
        final CustomerEntity result = underTest.storeCustomer(customerEntity);
        // then
        verify(entityManager).persist(customerEntity);
        assertThat(result, is(customerEntity));
    }

    @Test
    public void findCustomer() {
        // when
        final CustomerEntity result = underTest.findCustomerById(primaryKey);
        // then
        assertThat(result, is(customerEntity));
    }

    @Test
    public void findCustomer_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(CustomerEntity.class, primaryKey)).thenReturn(null);
        // when
        final CustomerEntity result = underTest.findCustomerById(primaryKey);
        // then
        assertThat(result, is(nullValue()));
    }

    @Test
    public void loadCustomer() {
        // when
        final CustomerEntity result = underTest.loadCustomerById(primaryKey);
        // then
        assertThat(result, is(customerEntity));
    }

    @Test
    public void loadCusomer_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(CustomerEntity.class, primaryKey)).thenReturn(null);
        exception.expect(DatabaseException.class);
        exception.expectMessage(containsString(" " + primaryKey.toString() + " "));
        exception.expect(EXCEPTION_MATCHER_ENTITY_NOT_FOUND);
        // when
        final CustomerEntity result = underTest.loadCustomerById(primaryKey);
    }
}