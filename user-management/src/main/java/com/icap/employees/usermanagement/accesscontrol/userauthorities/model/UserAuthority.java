package com.codevirtus.membershipsystem.usermanagement.accesscontrol.userauthorities.model;

import com.codevirtus.membershipsystem.commons.utilities.jpa.BaseEntity;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.authorities.model.Authority;
import com.codevirtus.membershipsystem.usermanagement.user.model.User;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Audited
@EntityListeners(AuditingEntityListener.class)
public class UserAuthority extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Authority authority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAuthority)) return false;
        if (!super.equals(o)) return false;
        UserAuthority that = (UserAuthority) o;
        return getId() == that.getId() &&
                getUser().equals(that.getUser()) &&
                getAuthority().equals(that.getAuthority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getUser(), getAuthority());
    }
}
