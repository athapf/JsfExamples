package de.thaso.jd.web.it.pages;

import de.thaso.jd.web.it.components.ButtonCO;
import de.thaso.jd.web.it.components.TableCO;
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
    private ButtonCO createButton;

    //@FindBy(how = How.CSS, css = "*[id$='entrylist']")
    private TableCO<OverviewTableRowPart> entrylistTable;

    @Override
    public String getPageId() {
        return "overviewPage";
    }

    public ButtonCO getCreateButton() {
        return createButton;
    }

    public TableCO<OverviewTableRowPart> getEntrylistTable() {
        return entrylistTable;
    }
}
