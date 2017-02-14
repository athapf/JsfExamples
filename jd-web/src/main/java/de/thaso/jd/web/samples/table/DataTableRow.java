package de.thaso.jd.web.samples.table;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * DataTableRow
 *
 * @author thaler
 * @since 07.02.17
 */
public class DataTableRow implements Serializable {

    private static final long serialVersionUID = 3266779206580651069L;

    private String description;
    private Map<AttributeKeys, String> attributes = new HashMap<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Map<AttributeKeys, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(final Map<AttributeKeys, String> attributes) {
        this.attributes = attributes;
    }
}
