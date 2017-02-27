package de.thaso.jd.web.it.selenium;

import org.openqa.selenium.WebElement;

/**
 * InjectableComponent
 *
 * @author thaler
 * @since 27.02.17
 */
public interface InjectableComponent {

    void injectElement(WebElement webElement);
}
