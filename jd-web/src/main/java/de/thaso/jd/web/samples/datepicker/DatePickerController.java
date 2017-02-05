package de.thaso.jd.web.samples.datepicker;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;

/**
 * DatePickerController
 *
 * @author thaler
 * @since 26.01.17
 */
@Named
@ApplicationScoped
public class DatePickerController {

    private static final Logger LOG = LoggerFactory.getLogger(DatePickerController.class);

    @Inject
    private DatePickerModel datePickerModel;

    public void ok() {
        LOG.info("Button 'OK' is pushed on this page. Current date is '{}'.",
                DateFormatUtils.format(datePickerModel.getDate(),
                        DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()));
    }

    public void onBlur() {
        LOG.info("on blur {}", datePickerModel.getDate());

    }


    public void onDateSelect() {
        LOG.info("on date select {}", datePickerModel.getDate());
        if (datePickerModel.getDate().get(Calendar.YEAR) == 1000) {
            LOG.info("set to null");
            datePickerModel.setDate(null);
        }
    }
}
