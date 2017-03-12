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
public class SimpleTableRow implements Serializable {

    private static final long serialVersionUID = 3266779206580651069L;

    private int index;
    private String description;
    private String name;
    private String location;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
