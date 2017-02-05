package de.thaso.jd.web.samples.datepicker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * DatePickerValidator
 *
 * @author thaler
 * @since 31.01.17
 */
@FacesValidator("thaso.sample.DatePickerValidator")
public class DatePickerValidator implements Validator {

    private static final Logger LOG = LoggerFactory.getLogger(DatePickerValidator.class);

    @Override
    public void validate(final FacesContext facesContext, final UIComponent uiComponent, final Object o) throws ValidatorException {
        LOG.info("Validator: {}", o.toString());
    }
}
