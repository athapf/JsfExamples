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
public class EntryPage extends StandardPage {

    @FindBy(how = How.CSS, css = "*[id$='saveButton']")
    @PageObjectComponent
    private ButtonCO saveButton;

    @FindBy(how = How.CSS, css = "*[id$='cancelButton']")
    @PageObjectComponent
    private ButtonCO cancelButton;

    @Override
    public String getPageId() {
        return "entryPage";
    }

    public ButtonCO getSaveButton() {
        return saveButton;
    }

    public ButtonCO getCancelButton() {
        return cancelButton;
    }
}
