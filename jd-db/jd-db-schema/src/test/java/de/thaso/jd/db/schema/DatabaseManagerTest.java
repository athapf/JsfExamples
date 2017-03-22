package de.thaso.jd.db.schema;

import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

/**
 * DatabaseManagerTest
 *
 * @author thaler
 * @since 2017-03-22
 */
public class DatabaseManagerTest {

    private DatabaseManager underTest;

    private Properties properties;

    @Before
    public void setUp() {
        underTest = new DatabaseManager();

        properties = PropertiesManager.readDevelopProperties();
    }

    @Test
    public void testCreateDatabase() {
        // when
        underTest.createDatabase(properties);
    }
}
