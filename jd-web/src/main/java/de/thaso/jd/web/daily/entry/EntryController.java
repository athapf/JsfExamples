package de.thaso.jd.web.daily.entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

/**
 * EntryController
 *
 * @author thaler
 * @since 27.02.17
 */
@Named("dpEntryController")
public class EntryController {

    private static final Logger LOG = LoggerFactory.getLogger(EntryController.class);

    public String saveEntry() {
        LOG.info("got a save event.");

        return "overview.xhtml";
    }

    public String cancelEntry() {
        LOG.info("got a cancel event.");

        return "overview.xhtml";
    }
}