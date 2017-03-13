package de.thaso.jd.web.samples.table;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * DataTableControler
 *
 * @author thaler
 * @since 07.02.17
 */
@Named
@ApplicationScoped
public class DataTableController {

    private static final Logger LOG = LoggerFactory.getLogger(DataTableController.class);

    @Inject
    private DataTableModel dataTableModel;

    @Inject
    private LazyTableModel lazyTableModel;

    public void filterDataTabel() {
        LOG.info("filter data table with {}", dataTableModel.getFilterText());

        if (StringUtils.isEmpty(dataTableModel.getFilterText())) {
            resetFilteredRows();
        } else {
            dataTableModel.setFilteredRows(filterRows(dataTableModel.getRows(), dataTableModel.getFilterText()));
        }
    }

    private ArrayList<DataTableRow> filterRows(final List<DataTableRow> rows, final String filterText) {
        final ArrayList<DataTableRow> filteredRows = new ArrayList<>();
        for (DataTableRow row : rows) {
            if (StringUtils.contains(row.getDescription(), filterText)) {
                filteredRows.add(row);
            }
        }
        return filteredRows;
    }

    private void resetFilteredRows() {
        dataTableModel.setFilteredRows(dataTableModel.getRows());
    }

    public void sortTable() {
        LOG.info("sort table ...");
        lazyTableModel.reset();
    }

    public void resetDataTable() {
        LOG.info("====== >> reset data table ...");
        //lazyTableModel.reset();
    }
}
