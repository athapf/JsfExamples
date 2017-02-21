package de.thaso.jd.web.it.samples;

import de.thaso.jd.web.it.base.SeleniumTestBase;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * CellEditIT
 *
 * @author thaler
 * @since 21.02.17
 */
public class CellEditIT extends SeleniumTestBase {

    @Test
    public void testCellEditPage() {
        getDriver().get(getAppUrl() + "/samples/celledit.xhtml");
        final WebElement valueElement = getDriver().findElementById("form:editColumns:0:value");
        valueElement.click();
        final WebElement valueTextarea = getDriver().findElementById("form:editColumns:0:textareaEdit");
        setValue(valueTextarea, "Dies ist ein Text viel zu ");
        valueTextarea.sendKeys("langer");
        
        assertThat(valueTextarea.getAttribute("value"), is("Dies ist ein Text viel zu lang"));
    }

    private void setValue(final WebElement element, final String value) {
        getDriver().executeScript("arguments[0].value = arguments[1];", element, value);
    }

    private void triggerEvent(final WebElement element, final String eventName) {
        getDriver().executeScript("var event = document.createEvent('Event');"
                + "event.initEvent('" + eventName + "', true, true); "
                + "return arguments[0].dispatchEvent(event)", element);
    }
}
