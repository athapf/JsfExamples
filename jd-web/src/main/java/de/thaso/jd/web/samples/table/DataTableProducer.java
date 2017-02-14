package de.thaso.jd.web.samples.table;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * DataTableProducer
 *
 * @author thaler
 * @since 07.02.17
 */
public class DataTableProducer {

    @Produces
    @SessionScoped
    @Named("dataTable")
    public DataTableModel produceDataTableModel(@New DataTableModel dataTableModel) {

        final int rowCount = RandomUtils.nextInt(5, 10);
        for(int i = 0; i < rowCount; i++) {
            dataTableModel.addRow(createRandomRow());
        }
        dataTableModel.setDescriptionHeader(RandomStringUtils.randomAlphabetic(15));
        for (AttributeKeys key : AttributeKeys.values()) {
            dataTableModel.getHeaders().put(key, RandomStringUtils.randomAlphabetic(10));
        }
        dataTableModel.setFilteredRows(dataTableModel.getRows());
        return dataTableModel;
    }

    private DataTableRow createRandomRow() {
        final DataTableRow result = new DataTableRow();
        result.setDescription(RandomStringUtils.randomAlphabetic(20));
        for (AttributeKeys key : AttributeKeys.values()) {
            result.getAttributes().put(key, RandomStringUtils.randomAlphanumeric(10));
        }
        return result;
    }
}
