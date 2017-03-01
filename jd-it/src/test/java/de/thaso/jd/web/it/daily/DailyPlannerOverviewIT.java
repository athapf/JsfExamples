package de.thaso.jd.web.it.daily;

import de.thaso.jd.web.it.base.SeleniumTestBase;
import de.thaso.jd.web.it.common.EntryPage;
import de.thaso.jd.web.it.common.OverviewPage;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * DailyPlannerOverviewIT
 *
 * @author thaler
 * @since 27.02.17
 */
public class DailyPlannerOverviewIT extends SeleniumTestBase {

    @Test
    public void testOverviewPage() {
        final OverviewPage overviewPage = startBrowser("daily/overview", OverviewPage.class);
        overviewPage.getCreateButton().click();

        final EntryPage entryPage = overviewPage.nextPage(EntryPage.class);
        entryPage.getTitleInput().setValue("Auto waschen");
        entryPage.getKindInput().setValue("HOMEWORK");
        assertThat(entryPage.getTitleInput().getValue(), is("Auto waschen"));
        entryPage.getSaveButton().click();

        final OverviewPage overviewPage1 = entryPage.nextPage(OverviewPage.class);
        overviewPage1.getCreateButton().click();

        final EntryPage entryPage1 = overviewPage.nextPage(EntryPage.class);
        assertThat(entryPage1.getTitleInput().getValue(), is(StringUtils.EMPTY));
    }
}
