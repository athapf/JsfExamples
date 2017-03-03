package de.thaso.jd.web.it.pages;

import de.thaso.jd.web.it.components.ButtonCO;
import de.thaso.jd.web.it.components.InputCO;
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
    private ButtonCO saveButton;

    @FindBy(how = How.CSS, css = "*[id$='cancelButton']")
    private ButtonCO cancelButton;

    @FindBy(how = How.CSS, css = "*[id$='date']")
    private InputCO dateInput;

    @FindBy(how = How.CSS, css = "*[id$='title']")
    private InputCO titleInput;

    @FindBy(how = How.CSS, css = "*[id$='kind']")
    private InputCO kindInput;

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

    public InputCO getDateInput() {
        return dateInput;
    }

    public InputCO getTitleInput() {
        return titleInput;
    }

    public InputCO getKindInput() {
        return kindInput;
    }
}
