package de.thaso.jd.web.it.common;

import de.thaso.jd.web.it.selenium.ButtonCO;
import de.thaso.jd.web.it.selenium.PageObjectComponent;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * OverviewPage
 *
 * @author thaler
 * @since 27.02.17
 */
public class OverviewPage extends StandardPage {

    @FindBy(how = How.CSS, css = "*[id$='createButton']")
    @PageObjectComponent
    private ButtonCO createButton;

    @Override
    public String getPageId() {
        return "overviewPage";
    }

    public ButtonCO getCreateButton() {
        return createButton;
    }
}
