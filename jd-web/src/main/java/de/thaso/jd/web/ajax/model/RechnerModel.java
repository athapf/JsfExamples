package de.thaso.jd.web.ajax.model;

import javax.enterprise.inject.Any;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * RechnerModel
 *
 * @author thaler
 * @since 16.01.17
 */
@Any
public class RechnerModel implements Serializable {

    private static final long serialVersionUID = 2409560466200339987L;

    private BigDecimal brutto;
    private BigDecimal prozent;
    private BigDecimal netto;

    public BigDecimal getBrutto() {
        return brutto;
    }

    public void setBrutto(final BigDecimal brutto) {
        this.brutto = brutto;
    }

    public BigDecimal getProzent() {
        return prozent;
    }

    public void setProzent(final BigDecimal prozent) {
        this.prozent = prozent;
    }

    public BigDecimal getNetto() {
        return netto;
    }

    public void setNetto(final BigDecimal netto) {
        this.netto = netto;
    }
}
