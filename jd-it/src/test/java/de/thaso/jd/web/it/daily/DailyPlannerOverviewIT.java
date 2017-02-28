package de.thaso.jd.web.it.daily;

import de.thaso.jd.web.it.base.SeleniumTestBase;
import de.thaso.jd.web.it.common.EntryPage;
import de.thaso.jd.web.it.common.OverviewPage;
import org.junit.Test;

/**
 * DailyPlannerOverviewIT
 *
 * @author thaler
 * @since 27.02.17
 */
public class DailyPlannerOverviewIT extends SeleniumTestBase {

    @Test
    public void testOverviewPage() {
        getDriver().get(getAppUrl() + "/daily/overview.xhtml");

        final OverviewPage overviewPage = OverviewPage.nextPage(getDriver(), OverviewPage.class);
        overviewPage.getCreateButton().click();

        final EntryPage entryPage = EntryPage.nextPage(getDriver(), EntryPage.class);
        entryPage.getSaveButton().click();

        final OverviewPage overviewPage1 = EntryPage.nextPage(getDriver(), OverviewPage.class);
        overviewPage1.getCreateButton().click();
    }
}
