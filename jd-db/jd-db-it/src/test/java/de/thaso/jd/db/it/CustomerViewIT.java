package de.thaso.jd.db.it;

import de.thaso.jd.db.it.base.DbTestBase;
import de.thaso.jd.db.store.NickNameDAO;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * CustomerViewIT
 *
 * @author thaler
 * @since 2017-03-22
 */
public class CustomerViewIT extends DbTestBase {

    @InjectMocks
    private NickNameDAO underTest;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        initMocks(this);
    }
}
