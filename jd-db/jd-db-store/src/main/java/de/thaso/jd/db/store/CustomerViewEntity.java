package de.thaso.jd.db.store;

import de.thaso.jd.db.store.base.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * CustomerEntity
 *
 * @author thaler
 * @since 2017-03-22
 */
@Entity
@Table(name = "V_CUSTOMER")
@NamedQueries({
    @NamedQuery(name = CustomerViewEntity.FIND_BY_IDENT, query = "select cv from CustomerViewEntity cv where cv.ident = :ident")
})
public class CustomerViewEntity extends EntityBase {

    private static final long serialVersionUID = -4319045348350461073L;

    public static final String FIND_BY_IDENT = "FIND_BY_IDENT";

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "IDENT")
    @NotNull
    private String ident;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(final String ident) {
        this.ident = ident;
    }
}
