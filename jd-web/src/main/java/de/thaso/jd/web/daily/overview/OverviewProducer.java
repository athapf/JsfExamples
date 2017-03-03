package de.thaso.jd.web.daily.overview;

import de.thaso.jd.web.daily.entry.EntryKind;
import de.thaso.jd.web.daily.utils.WaitHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * OverviewProducer
 *
 * @author thaler
 * @since 27.02.17
 */
public class OverviewProducer {

    private static final Logger LOG = LoggerFactory.getLogger(OverviewProducer.class);

    @Produces
    @RequestScoped
    @Named("dpOverviewModel")
    public OverviewModel produceOverviewModel(@New OverviewModel overviewModel) {
        LOG.info("produce OverviewModel");

        if (overviewModel.getTableRowList().isEmpty()) {
            for (int i = 0; i < 5; i++) {
                overviewModel.addTableRow(createRandomTableRow());
            }
        }
        WaitHelper.pause();
        return overviewModel;
    }

    private OverviewTableRow createRandomTableRow() {
        final OverviewTableRow tableRow = new OverviewTableRow();
        tableRow.setDate(createRandomDate());
        tableRow.setTitle(RandomStringUtils.randomAlphanumeric(10));
        tableRow.setKind(createRandomKind());
        return tableRow;
    }

    private EntryKind createRandomKind() {
        final EntryKind kind = EntryKind.values()[RandomUtils.nextInt(0,EntryKind.values().length)];
        return kind;
    }

    private Calendar createRandomDate() {
        final Calendar calendar = new GregorianCalendar();
        final int amount = RandomUtils.nextInt(0, 2000) - 1000;
        calendar.add(Calendar.DATE, amount);
        return calendar;
    }
}
