package de.thaso.jd.web.daily.overview;

import javax.enterprise.inject.Any;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * OverviewModel
 *
 * @author thaler
 * @since 27.02.17
 */
@Any
public class OverviewModel implements Serializable {

    private static final long serialVersionUID = 952219501453160715L;

    private List<OverviewTableRow> tableRowList = new ArrayList<>();

    public List<OverviewTableRow> getTableRowList() {
        return tableRowList;
    }

    public void setTableRowList(final List<OverviewTableRow> tableRowList) {
        this.tableRowList = tableRowList;
    }

    public void addTableRow(final OverviewTableRow tableRow) {
        this.tableRowList.add(tableRow);
    }
}
