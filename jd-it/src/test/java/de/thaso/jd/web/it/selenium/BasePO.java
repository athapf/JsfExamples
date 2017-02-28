package de.thaso.jd.web.it.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * BasePageObject
 *
 * @author thaler
 * @since 27.02.17
 */
public abstract class BasePO extends BaseCO {

    private static Logger LOG = LoggerFactory.getLogger(BasePO.class);

    public static <T extends BasePO> T nextPage(final RemoteWebDriver driver, Class<T> pageClass) {
        try {
            final T page = pageClass.newInstance();
            page.waitForPage(driver);

            for (final Field field : pageClass.getDeclaredFields()) {
                if (field.getAnnotation(PageObjectComponent.class) != null) {

                    final FindBy findBy = field.getAnnotation(FindBy.class);
                    final WebElement webElement = driver.findElementByCssSelector(findBy.css());

                    final InjectableComponent instance = (InjectableComponent)field.getType().newInstance();
                    instance.injectElement(webElement);

                    field.setAccessible(true);
                    field.set(page, instance);
                }
            }
            return page;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract boolean isCurrentPage(final WebDriver driver);

    public boolean waitForPage(final RemoteWebDriver driver) {
        // waitForAjax();

        final ExpectedCondition<Boolean> waitForPageCondition = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(final WebDriver webDriver) {
                return isCurrentPage(webDriver);
            }
        };

        try {
            new WebDriverWait(driver, 5).until(waitForPageCondition);
        } catch (Exception e) {
            return false;
        }
        return isCurrentPage(driver);
    }
}
