package de.thaso.jd.web.ajax;

import de.thaso.jd.web.ajax.model.RechnerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * RechnerController
 *
 * @author thaler
 * @since 16.01.17
 */
@Named
@SessionScoped
public class RechnerController implements Serializable {

    private static final long serialVersionUID = -4857728699603721948L;
    private static final Logger LOG = LoggerFactory.getLogger(RechnerController.class);

    @Inject
    private RechnerModel rechnerModel;

    public void save() {
        LOG.info("going to save values ... {} {} {}", rechnerModel.getBrutto(), rechnerModel.getProzent(), rechnerModel.getNetto());
    }

    public void clear() {
        rechnerModel.setBrutto(null);
        rechnerModel.setProzent(null);
        rechnerModel.setNetto(null);
    }

    public void calculate() {
        LOG.info("going to calculate new values ... {} {} {}", rechnerModel.getBrutto(), rechnerModel.getProzent(), rechnerModel.getNetto());
        if (rechnerModel.getBrutto() != null
                && rechnerModel.getProzent() != null
                && rechnerModel.getProzent() != BigDecimal.ZERO) {
            LOG.info("... input values ok - calculate");

            // very long calculation
            sleep(2000);

            rechnerModel.setNetto(
                    rechnerModel.getBrutto().multiply(
                            rechnerModel.getProzent().divide(new BigDecimal(100))
                    ));
            LOG.info("... result is: {}", rechnerModel.getNetto());
        }
    }

    public void incrementProzent() {
        if (rechnerModel.getProzent() == null) {
            rechnerModel.setProzent(BigDecimal.ZERO);
        }
        rechnerModel.setProzent(rechnerModel.getProzent().add(BigDecimal.ONE));
        calculate();
    }

    private void sleep(final int millis) {
        try {
            Thread.currentThread().sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
