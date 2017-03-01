package de.thaso.jd.web.it.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * InjectableComponent
 *
 * @author thaler
 * @since 27.02.17
 */
public interface InjectableComponent {

    void injectElement(RemoteWebDriver webDriver, WebElement webElement);
}
