package de.thaso.jd.web.daily.overview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

/**
 * OverviewController
 *
 * @author thaler
 * @since 27.02.17
 */
@Named("dpOverviewController")
public class OverviewController {

    private static final Logger LOG = LoggerFactory.getLogger(OverviewController.class);

    public String createEntry() {
        return "entry.xhtml";
    }
}
