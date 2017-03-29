package de.thaso.jd.db.it;

import de.thaso.jd.db.it.base.DbTestBase;
import de.thaso.jd.db.store.CustomerDAO;
import de.thaso.jd.db.store.CustomerEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * CustomerViewIT
 *
 * @author thaler
 * @since 2017-03-22
 */
public class CustomerIT extends DbTestBase {

    @InjectMocks
    private CustomerDAO underTest;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testFindByName() throws SQLException {
        // given
        final List<String> identList = new ArrayList<>();
        identList.add("45271;03;05/13/2016");
        identList.add("45271;03;-");
        // when
        final List<CustomerEntity> result = underTest.findByIdentList(identList);
        // then
        assertThat(result.size(), is(2));
    }
}
