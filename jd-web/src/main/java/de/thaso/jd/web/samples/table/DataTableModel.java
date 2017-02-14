package de.thaso.jd.web.samples.table;

import javax.enterprise.inject.Any;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataTableModel
 *
 * @author thaler
 * @since 07.02.17
 */
@Any
public class DataTableModel implements Serializable {

    private static final long serialVersionUID = 4244220880040121458L;

    private List<DataTableRow> rows = new ArrayList<>();
    private List<DataTableRow> filteredRows = new ArrayList<>();
    private Map<AttributeKeys, String> headers = new HashMap<>();
    private String descriptionHeader;
    private String filterText;

    public List<DataTableRow> getRows() {
        return rows;
    }

    public void setRows(final List<DataTableRow> rows) {
        this.rows = rows;
    }

    public void addRow(final DataTableRow row) {
        this.rows.add(row);
    }

    public List<DataTableRow> getFilteredRows() {
        return filteredRows;
    }

    public void setFilteredRows(final List<DataTableRow> filteredRows) {
        this.filteredRows = filteredRows;
    }

    public String getDescriptionHeader() {
        return descriptionHeader;
    }

    public void setDescriptionHeader(final String descriptionHeader) {
        this.descriptionHeader = descriptionHeader;
    }

    public Map<AttributeKeys, String> getHeaders() {
        return headers;
    }

    public void setHeaders(final Map<AttributeKeys, String> headers) {
        this.headers = headers;
    }

    public String getFilterText() {
        return filterText;
    }

    public void setFilterText(final String filterText) {
        this.filterText = filterText;
    }

    public List<AttributeKeys> getAttributes() {
        return new ArrayList<AttributeKeys>(Arrays.asList(AttributeKeys.values()));
    }
}
