package de.thaso.jd.web.daily.entry;

import de.thaso.jd.web.daily.utils.WaitHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * EntryController
 *
 * @author thaler
 * @since 27.02.17
 */
@ApplicationScoped
@Named("dpEntryController")
public class EntryController {

    private static final Logger LOG = LoggerFactory.getLogger(EntryController.class);

    @Inject
    private EntryModel entryModel;

    public String saveEntry() {
        LOG.info("got a save event.");
        LOG.info("Data: " + entryModel.toString());

        WaitHelper.pause();
        return "overview.xhtml";
    }

    public String cancelEntry() {
        LOG.info("got a cancel event.");

        for (int i = 0; i < 100000000; i++) {
            int a = i + 1;
        }
        return "overview.xhtml";
    }
}
