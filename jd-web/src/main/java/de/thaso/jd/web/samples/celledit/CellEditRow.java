package de.thaso.jd.web.samples.celledit;

import java.io.Serializable;

/**
 * EditCellRow
 *
 * @author thaler
 * @since 21.02.17
 */
public class CellEditRow implements Serializable {
    private static final long serialVersionUID = -607893359300665632L;

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
