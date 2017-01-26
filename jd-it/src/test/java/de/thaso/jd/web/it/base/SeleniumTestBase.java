package de.thaso.jd.web.it.base;

import de.thaso.jd.web.it.glassfish.GlassfishEmbeddedServer;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.Properties;

/**
 * SeleniumTest
 *
 * @author thaler
 * @since 26.09.16
 */
public class SeleniumTestBase {

    private static Properties properties;

    private static ApplicationServerBase appServer = new GlassfishEmbeddedServer();
    private FirefoxDriver driver;

    public static Properties readProperties() {
        return PropertiesManager.readDevelopProperties();
    }

    public static Properties getProperties() {
        if (properties == null) {
            properties = readProperties();
        }
        return properties;
    }

    @BeforeClass
    public static void initEmbeddedServer() throws Exception {
        appServer.startEmbeddedServer();
    }

    @AfterClass
    public static void closeEmbeddedServer() throws Exception {
        appServer.stopEmbeddedServer();
    }

    @Rule
    public ScreenShotRule screenShotRule = new ScreenShotRule();

    @Before
    public void setUpSeleniumDriver() {
        final FirefoxBinary binary = new FirefoxBinary(new File(getProperties().getProperty("firefox.executable")));

        final String display = getProperties().getProperty("browser.display");
        if (StringUtils.isNotEmpty(display)) {
            binary.setEnvironmentProperty("DISPLAY", display);
        }
        driver = new FirefoxDriver(binary, null);

        screenShotRule.setBrowser(driver);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDownSeleniumDriver() {
        driver.close();
    }

    protected FirefoxDriver getDriver() {
        return driver;
    }

    public String getAppUrl() {
        return appServer.getApplicationUrl();
    }
}
