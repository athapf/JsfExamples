package de.thaso.jd.db.it;

import de.thaso.jd.db.it.base.DbTestBase;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * CustomerViewIT
 *
 * @author thaler
 * @since 2017-03-22
 */
public class ForeignKeyGraphIT extends DbTestBase {

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void readMetaData() throws SQLException {
        final Connection connection = getConnection();
        final DatabaseMetaData metaData = connection.getMetaData();

        final ResultSet tables = metaData.getTables(connection.getCatalog(), "DB2INST1", "T_%", null);
        final List<String> tableNames = extractTableNames(tables);

        for (String tabelName : tableNames) {
            final ResultSet columns = metaData.getColumns(connection.getCatalog(), "DB2INST1", tabelName, null);
            final Map<String, Boolean> columnNotNullInfo = extractColumnNotNullInfo(columns);

            final ResultSet importedKeys = metaData.getImportedKeys(connection.getCatalog(), null, tabelName);
            final Map<String, String> foreignKeys = extractForeignKeys(importedKeys);

            createInfo(tabelName, foreignKeys, columnNotNullInfo);
        }
    }

    private List<String> extractTableNames(final ResultSet tables) throws SQLException {
        final List<String> result = new ArrayList<>();
        while (tables.next()) {
            result.add(tables.getString(3));
        }
        return result;
    }

    private Map<String, Boolean> extractColumnNotNullInfo(final ResultSet columns) throws SQLException {
        final Map<String, Boolean> result = new HashMap<>();
        while (columns.next()) {
            result.put(columns.getString(4),columns.getBoolean(11) == false);
        }
        return result;
    }

    private Map<String, String> extractForeignKeys(final ResultSet importedKeys) throws SQLException {
        final Map<String, String> result = new HashMap<>();
        while (importedKeys.next()) {
            result.put(importedKeys.getString(8),importedKeys.getString(3));
        }
        return result;
    }

    private void createInfo(final String tabelName, final Map<String, String> foreignKeys, final Map<String, Boolean> columnNotNullInfo) {
        for (Map.Entry<String, String> foreignKey : foreignKeys.entrySet()) {
            System.out.println(" " + tabelName + " -> " + foreignKey.getValue() + " [label=\""
                    + (columnNotNullInfo.get(foreignKey.getKey()) ? "! " : "")
                    + foreignKey.getKey() + "\"];");
        }
    }


    private void printResultSet(final ResultSet columns) throws SQLException {
        int index = 0;
        final ResultSetMetaData columnsMetaData = columns.getMetaData();
        while (columns.next()) {
            final int count = columns.getMetaData().getColumnCount();
            System.out.print(index);
            for (int i = 0; i < count; i++) {
                System.out.print(" ; ");
                System.out.print(columnsMetaData.getColumnLabel(i+1));
                System.out.print(" = ");
                System.out.print(columns.getString(i+1));
            }
            System.out.println("");
            index++;
        }
    }
}
