package de.thaso.jd.web.it.selenium;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

/**
 * BaseComponentObject
 *
 * @author thaler
 * @since 27.02.17
 */
public class BaseCO implements InjectableComponent {

    private WebElement webElement;

    @Override
    public void injectElement(final WebElement webElement) {
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
}
