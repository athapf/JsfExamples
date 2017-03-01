package de.thaso.jd.web.daily.overview;

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
    private final static Object obj = new Object();

    public String createEntry() {

        LOG.info("do some very long work ...");
        synchronized (obj) {
            try {
                obj.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOG.info("... long work ready.");
        return "entry.xhtml";
    }
}
