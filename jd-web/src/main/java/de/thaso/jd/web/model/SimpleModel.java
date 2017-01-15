package de.thaso.jd.web.model;

import javax.enterprise.inject.Any;
import java.io.Serializable;

/**
 * ChatOverviewModel
 *
 * @author thaler
 * @since 27.09.16
 */
@Any
public class SimpleModel implements Serializable {
    private static final long serialVersionUID = 3990370126693292191L;
    private String simpleMessage;

    public String getSimpleMessage() {
        return simpleMessage;
    }

    public void setSimpleMessage(final String simpleMessage) {
        this.simpleMessage = simpleMessage;
    }
}
