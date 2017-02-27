package de.thaso.jd.web.daily.overview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
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
    @SessionScoped
    @Named("dpOverviewModel")
    public OverviewModel produceOverviewModel(@New OverviewModel overviewModel) {

        return overviewModel;
    }
}
