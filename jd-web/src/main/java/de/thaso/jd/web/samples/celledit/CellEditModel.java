package de.thaso.jd.web.samples.celledit;

import javax.enterprise.inject.Any;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * CellEditModel
 *
 * @author thaler
 * @since 21.02.17
 */
@Any
public class CellEditModel implements Serializable {
    private static final long serialVersionUID = 2795689445766956818L;

    private List<CellEditRow> rows = new ArrayList<>();

    public List<CellEditRow> getRows() {
        return rows;
    }

    public void setRows(final List<CellEditRow> rows) {
        this.rows = rows;
    }

    public void addRow(final CellEditRow row) {
        this.rows.add(row);
    }
}
