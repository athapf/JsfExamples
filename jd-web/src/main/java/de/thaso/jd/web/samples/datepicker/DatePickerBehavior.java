package de.thaso.jd.web.samples.datepicker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;

/**
 * DatePickerBehavior
 *
 * @author thaler
 * @since 30.01.17
 */
public class DatePickerBehavior implements AjaxBehaviorListener {

    private static final Logger LOG = LoggerFactory.getLogger(DatePickerBehavior.class);

    @Override
    public void processAjaxBehavior(final AjaxBehaviorEvent ajaxBehaviorEvent) throws AbortProcessingException {
        LOG.info("ajax behavior ...");
    }
}
