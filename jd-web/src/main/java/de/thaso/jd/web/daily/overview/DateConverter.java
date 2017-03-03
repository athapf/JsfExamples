package de.thaso.jd.web.daily.overview;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * KindConverter
 *
 * @author thaler
 * @since 02.03.17
 */
@FacesConverter("dateConverter")
public class DateConverter implements Converter {

    private final static Logger LOG = LoggerFactory.getLogger(DateConverter.class);

    @Override
    public Calendar getAsObject(final FacesContext facesContext, final UIComponent uiComponent, final String s) {
        LOG.info("convert calendar to object");

        if(StringUtils.isNotBlank(s)) {
            final Calendar calendar = new GregorianCalendar();
            try {
                calendar.setTime(DateUtils.parseDate(s, "yyyy-MM-dd"));
            } catch (ParseException e) {
                return null;
            }
            return calendar;
        }
        return null;
    }

    @Override
    public String getAsString(final FacesContext facesContext, final UIComponent uiComponent, final Object o) {
        LOG.info("convert calendar to string");

        if (o instanceof Calendar) {
            final Calendar calendar = (Calendar) o;
            return DateFormatUtils.format(calendar, "yyyy-MM-dd");
        }
        return null;
    }
}
