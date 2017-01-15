package de.thaso.jd.web.producer;

import de.thaso.jd.web.model.SimpleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * ChatOverviewProducer
 *
 * @author thaler
 * @since 27.09.16
 */
public class SimpleProducer {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleProducer.class);

    @Produces
    @RequestScoped
    @Named("simpleModel")
    public SimpleModel produceSimpleModel(@New SimpleModel simpleModel) {
        LOG.info("produce simple model ...");
        simpleModel.setSimpleMessage("Hello, i'm the controller!");
        return simpleModel;
    }
}
