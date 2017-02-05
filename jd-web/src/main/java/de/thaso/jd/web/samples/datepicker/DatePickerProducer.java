package de.thaso.jd.web.samples.datepicker;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * DatePickerProducer
 *
 * @author thaler
 * @since 26.01.17
 */
public class DatePickerProducer {

    @Produces
    @RequestScoped
    @Named("datePickerModel")
    public DatePickerModel produceDatePickerModel(@New DatePickerModel datePickerModel) {
        datePickerModel.setDate(null);
        return datePickerModel;
    }
}
