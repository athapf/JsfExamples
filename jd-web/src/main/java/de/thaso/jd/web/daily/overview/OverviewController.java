package de.thaso.jd.web.daily.overview;

import de.thaso.jd.web.daily.utils.WaitHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * OverviewController
 *
 * @author thaler
 * @since 27.02.17
 */
@ApplicationScoped
@Named("dpOverviewController")
public class OverviewController {

    private static final Logger LOG = LoggerFactory.getLogger(OverviewController.class);

    public String createEntry() {
        LOG.info("call createEntry action");
        WaitHelper.pause();
        return "entry.xhtml";
    }
}
