package de.thaso.jd.web.it.components;

import de.thaso.jd.web.it.base.BaseCO;

/**
 * ButtonCO
 *
 * @author thaler
 * @since 27.02.17
 */
public class ButtonCO extends BaseCO {

    public boolean isVisible() {
        return isPresent(getWebElement())&& getWebElement().isDisplayed();
    }

    public void click() {
        doClick(getWebElement());
    }
}
