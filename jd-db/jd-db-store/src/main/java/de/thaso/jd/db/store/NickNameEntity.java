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
 * NickNameEntity
 *
 * @author thaler
 * @since 2017-03-22
 */
@Entity
@Table(name = "T_NICK_NAME")
@NamedQueries({
    @NamedQuery(name = NickNameEntity.FIND_BY_NAMES, query = "select nn from NickNameEntity nn where nn.name = :name order by nn.timestamp desc"),
    @NamedQuery(name = NickNameEntity.FIND_BY_NICK_NAME, query = "select nn from NickNameEntity nn where nn.name = :name and nn.nick = :nick order by nn.timestamp desc")
})
public class NickNameEntity extends EntityBase {

    private static final long serialVersionUID = -4319045348350461073L;

    public static final String FIND_BY_NAMES = "FIND_BY_NAMES";
    public static final String FIND_BY_NICK_NAME = "FIND_BY_NICK_NAMES";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NickNameSequence")
    @SequenceGenerator(name = "NickNameSequence", sequenceName = "SEQ_ID_NICK_NAME", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP")
    @NotNull
    private Date timestamp;

    @Column(name = "NAME")
    @Size(min=1, max=30)
    @NotNull
    private String name;

    @Column(name = "NICK")
    @Size(min=1, max=20)
    private String nick;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
