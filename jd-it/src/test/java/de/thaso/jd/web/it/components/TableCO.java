package de.thaso.jd.web.it.components;

import de.thaso.jd.web.it.base.BaseCO;

import java.util.ArrayList;
import java.util.List;

/**
 * TableCO
 *
 * @author thaler
 * @since 03.03.17
 */
public class TableCO<T extends TableRowPart> extends BaseCO {

    private List<T> tableRows = new ArrayList<>();

    public List<T> getTableRows() {
        return tableRows;
    }
}
