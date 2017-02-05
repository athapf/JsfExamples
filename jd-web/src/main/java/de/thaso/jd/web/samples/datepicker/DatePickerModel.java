package de.thaso.jd.web.samples.datepicker;

import javax.enterprise.inject.Any;
import java.io.Serializable;
import java.util.Calendar;

/**
 * DatePickerModel
 *
 * @author thaler
 * @since 26.01.17
 */
@Any
public class DatePickerModel implements Serializable {

    private static final long serialVersionUID = 6819858716439510593L;

    private Calendar date;

    public Calendar getDate() {
        return date;
    }

    public void setDate(final Calendar date) {
        this.date = date;
    }
}
