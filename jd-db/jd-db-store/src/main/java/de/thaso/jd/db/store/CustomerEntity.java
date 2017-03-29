package de.thaso.jd.db.store;

import de.thaso.jd.db.store.base.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * CustomerEntity
 *
 * @author thaler
 * @since 2017-03-28
 */
@Entity
@Table(name = "T_CUSTOMER")
@NamedQueries({
    @NamedQuery(name = CustomerEntity.FIND_BY_NAME, query = "select c from CustomerEntity c where c.name = :name order by c.opening desc")
})
public class CustomerEntity extends EntityBase {

    private static final long serialVersionUID = -4319045348350461073L;

    public static final String FIND_BY_NAME = "FIND_BY_NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CustomerSequence")
    @SequenceGenerator(name = "CustomerSequence", sequenceName = "SEQ_ID_CUSTOMER", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    @Size(min=1, max=30)
    @NotNull
    private String name;

    @Column(name = "NUMBER")
    private Integer number;

    @Column(name = "RANGE")
    private String range;

    @Temporal(TemporalType.DATE)
    @Column(name = "OPENING")
    private Date opening;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(final Integer number) {
        this.number = number;
    }

    public String getRange() {
        return range;
    }

    public void setRange(final String range) {
        this.range = range;
    }

    public Date getOpening() {
        return opening;
    }

    public void setOpening(final Date opening) {
        this.opening = opening;
    }
}
