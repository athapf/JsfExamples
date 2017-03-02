package de.thaso.jd.web.daily.entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * KindConverter
 *
 * @author thaler
 * @since 02.03.17
 */
@FacesConverter("kindConverter")
public class KindConverter implements Converter {

    private final static Logger LOG = LoggerFactory.getLogger(KindConverter.class);

    @Override
    public EntryKind getAsObject(final FacesContext facesContext, final UIComponent uiComponent, final String s) {
        LOG.info("convert EntryKind to object");

        if(StringUtils.isNotBlank(s)) {
            for (EntryKind kind : EntryKind.values()) {
                if (kind.name().equals(s.toUpperCase())) {
                    return kind;
                }
            }
        }
        return null;
    }

    @Override
    public String getAsString(final FacesContext facesContext, final UIComponent uiComponent, final Object o) {
        LOG.info("convert EntryKind to string");

        if (o instanceof EntryKind) {
            final EntryKind kind = (EntryKind) o;
            return kind.name();
        }
        return null;
    }
}
