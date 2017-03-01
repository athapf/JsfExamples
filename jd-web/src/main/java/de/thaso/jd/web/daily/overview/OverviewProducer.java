package de.thaso.jd.web.daily.overview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * OverviewProducer
 *
 * @author thaler
 * @since 27.02.17
 */
public class OverviewProducer {

    private static final Logger LOG = LoggerFactory.getLogger(OverviewProducer.class);
    private final static Object obj = new Object();

    @Produces
    @RequestScoped
    @Named("dpOverviewModel")
    public OverviewModel produceOverviewModel(@New OverviewModel overviewModel) {

        LOG.info("do some very long work ...");
        synchronized (obj) {
            try {
                obj.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LOG.info("... long work ready.");
        return overviewModel;
    }
}
