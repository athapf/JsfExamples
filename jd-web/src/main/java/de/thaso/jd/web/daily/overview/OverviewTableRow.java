package de.thaso.jd.web.daily.overview;

import de.thaso.jd.web.daily.entry.EntryKind;

import java.io.Serializable;
import java.util.Calendar;

/**
 * OverviewTableRow
 *
 * @author thaler
 * @since 27.02.17
 */
public class OverviewTableRow implements Serializable{

    private static final long serialVersionUID = -7428153339476291972L;

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
}
