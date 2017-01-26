package de.thaso.jd.web.it.common;

import de.thaso.jd.web.it.base.SeleniumTestBase;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * OverviewPageTest
 *
 * @author thaler
 * @since 26.09.16
 */
public class OverviewPageTest extends SeleniumTestBase {

    @Test
    public void testOverviewPage() {
        getDriver().get(getAppUrl() + "/example.xhtml");
        final WebElement title = getDriver().findElementById("jd_title");
        assertThat(title.getText(),is("Component - Dies ist eine Nachricht"));
        final WebElement message = getDriver().findElementById("jd_message");
        assertThat(message.getText(),is("Message: Hello, i'm the controller!"));
    }
}
