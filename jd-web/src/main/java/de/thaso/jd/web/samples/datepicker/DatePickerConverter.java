package de.thaso.jd.web.samples.datepicker;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * DatePickerConverter
 *
 * @author thaler
 * @since 26.01.17
 */
@ManagedBean(name="dateConverter")
@ApplicationScoped
public class DatePickerConverter implements Converter, Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(DatePickerConverter.class);

    @Override
    public Object getAsObject(final FacesContext facesContext, final UIComponent uiComponent, final String s) {

        LOG.info("going to convert: {}", s);

//        if(StringUtils.equals(s,"01.01.1000")) {
//            LOG.info("convert to null!");
//            return new GregorianCalendar();
//        }

        GregorianCalendar calendar = new GregorianCalendar();
        final String date;

        if(s != null && s.length() == 8) {
            date = s.substring(0,2) + "." + s.substring(2,4) + "." + s.substring(4,8);
        } else if (s != null && s.length() == 6) {
            date = s.substring(0,2) + "." + s.substring(2,4) + "." + "20" + s.substring(4,6);
        } else if (s != null && s.length() == 4) {
            date = s.substring(0,2) + "." + s.substring(2,4) + "." + "2017";
        } else {
            date = s;
        }
        try {
            calendar.setTime(DateUtils.parseDate(date, "dd.MM.yyyy"));
        } catch (ParseException e) {
            calendar = null;
            //throw new RuntimeException(e);
        }
        return calendar;
    }

    @Override
    public String getAsString(final FacesContext facesContext, final UIComponent uiComponent, final Object o) {
        return DateFormatUtils.format((Calendar) o, "dd.MM.yyyy");
    }
}
