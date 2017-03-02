package de.thaso.jd.web.daily.overview;

import de.thaso.jd.web.daily.utils.WaitHelper;
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

    @Produces
    @RequestScoped
    @Named("dpOverviewModel")
    public OverviewModel produceOverviewModel(@New OverviewModel overviewModel) {
        LOG.info("produce OverviewModel");
        WaitHelper.pause();
        return overviewModel;
    }
}
