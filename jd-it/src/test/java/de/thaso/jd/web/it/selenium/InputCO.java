package de.thaso.jd.web.it.selenium;

/**
 * ButtonCO
 *
 * @author thaler
 * @since 27.02.17
 */
public class InputCO extends BaseCO {

    public boolean isVisible() {
        return isPresent(getWebElement())&& getWebElement().isDisplayed();
    }

    public void setValue() {
        
    }
}
