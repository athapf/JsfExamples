package de.thaso.jd.web.daily.entry;

import javax.enterprise.inject.Any;
import java.io.Serializable;
import java.util.Calendar;

/**
 * EntryModel
 *
 * @author thaler
 * @since 27.02.17
 */
@Any
public class EntryModel implements Serializable {

    private static final long serialVersionUID = 1286000912072435595L;

    private Calendar date;
    private String title;
    private EntryKind kind;

    public Calendar getDate() {
        return date;
    }

    public void setDate(final Calendar date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public EntryKind getKind() {
        return kind;
    }

    public void setKind(final EntryKind kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "EntryModel{" +
                "date=" + date +
                ", title='" + title + '\'' +
                ", kind=" + kind +
                '}';
    }
}
