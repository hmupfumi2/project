package com.codevirtus.membershipsystem.usermanagement.accesscontrol.groupauthorities.model;

import com.codevirtus.membershipsystem.commons.utilities.jpa.BaseEntity;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.authorities.model.Authority;
import com.codevirtus.membershipsystem.usermanagement.group.model.Group;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Audited
@EntityListeners(AuditingEntityListener.class)
public class GroupAuthority extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private Group group;

    @ManyToOne(optional = false)
    private Authority authority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupAuthority)) return false;
        if (!super.equals(o)) return false;
        GroupAuthority that = (GroupAuthority) o;
        return getId() == that.getId() &&
                getGroup().equals(that.getGroup()) &&
                getAuthority().equals(that.getAuthority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getGroup(), getAuthority());
    }
}
