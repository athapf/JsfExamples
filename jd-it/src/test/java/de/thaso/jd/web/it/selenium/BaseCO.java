package de.thaso.jd.web.it.selenium;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * BaseComponentObject
 *
 * @author thaler
 * @since 27.02.17
 */
public abstract class BaseCO implements InjectableComponent {

    private WebElement webElement;
    private RemoteWebDriver webDriver;

    @Override
    public void injectElement(final RemoteWebDriver webDriver, final WebElement webElement) {
        this.webDriver = webDriver;
        this.webElement = webElement;
    }

    protected WebElement getWebElement() {
        return webElement;
    }

    protected boolean isPresent(final WebElement proxiedElement) {
        if (proxiedElement == null || !isProxy(proxiedElement)) {
            return (proxiedElement != null);
        }

        try {
            // trigger search for proxied web elements
            proxiedElement.toString();
            return true;
        } catch (final NoSuchElementException nsee) {
            //do nothing
        } catch (StaleElementReferenceException e) {
            //do nothing
        }
        return false;
    }

    protected boolean isProxy(final WebElement webelement) {
        return webelement != null && webelement.getClass().getName().contains("Proxy");
    }

    public boolean waitForElement(final WebElement element) {
        return false;
    }

    protected void doClick(final WebElement webElement) {
        webElement.click();
        waitForElement(webElement);
    }

    protected void executeScript(final String script, final Object... args) {
        webDriver.executeScript(script, args);
    }

    protected void triggerEvent(final String eventName) {
        executeScript("var event = document.createEvent('Event');"
                + "event.initEvent('" + eventName + "', true, true); "
                + "return arguments[0].dispatchEvent(event)", webElement);
    }
}
