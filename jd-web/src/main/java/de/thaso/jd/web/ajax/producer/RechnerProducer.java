package de.thaso.jd.web.ajax.producer;

import de.thaso.jd.web.ajax.model.RechnerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.math.BigDecimal;

/**
 * RechnerProducer
 *
 * @author thaler
 * @since 16.01.17
 */
public class RechnerProducer {

    private static final Logger LOG = LoggerFactory.getLogger(RechnerProducer.class);

    @Produces
    @SessionScoped
    @Named("rechnerModel")
    public RechnerModel produceRechnerModel(@New RechnerModel rechnerModel) {
        LOG.info("produce an object of RechnerModel: {} {} {}", rechnerModel.getBrutto(), rechnerModel.getProzent(), rechnerModel.getNetto());
        rechnerModel.setBrutto(null);
        rechnerModel.setProzent(BigDecimal.ZERO);
        rechnerModel.setNetto(null);
        return rechnerModel;
    }

}
