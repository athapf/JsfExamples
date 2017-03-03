package de.thaso.jd.web.it.pages;

import de.thaso.jd.web.it.components.LabelCO;
import de.thaso.jd.web.it.components.TableRowPart;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * OverviewTableRowPart
 *
 * @author thaler
 * @since 03.03.17
 */
public class OverviewTableRowPart implements TableRowPart {

    @FindBy(how = How.CSS, css = "*[id$='title']")
    private LabelCO titleLabel;

    public LabelCO getTitleLabel() {
        return titleLabel;
    }
}
