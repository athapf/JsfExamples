package de.thaso.jd.web.samples.celledit;

import org.apache.commons.lang3.RandomStringUtils;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * CellEditProducer
 *
 * @author thaler
 * @since 21.02.17
 */
public class CellEditProducer {
    @Produces
    @SessionScoped
    @Named("cellEditModel")
    public CellEditModel produceCellEditModel(@New CellEditModel cellEditModel) {

        for(int i = 0; i < 3; i++) {
            final CellEditRow cellEditRow = new CellEditRow();
            cellEditRow.setKey(RandomStringUtils.randomAlphabetic(5));
            cellEditRow.setValue(RandomStringUtils.randomAlphanumeric(20));
            cellEditModel.addRow(cellEditRow);
        }
        return cellEditModel;
    }
}
