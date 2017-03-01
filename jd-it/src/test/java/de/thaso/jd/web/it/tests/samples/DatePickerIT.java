package de.thaso.jd.web.it.tests.samples;

import de.thaso.jd.web.it.base.SeleniumTestBase;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * DatePickerTest
 *
 * @author thaler
 * @since 26.01.17
 */
public class DatePickerIT extends SeleniumTestBase {

    @Test
    public void testOverviewPage() {
        getDriver().get(getAppUrl() + "/samples/datepicker.xhtml");
        final WebElement startdate = getDriver().findElementById("form:startdate_input");
        assertThat(startdate.getText(),is(""));
    }
}
