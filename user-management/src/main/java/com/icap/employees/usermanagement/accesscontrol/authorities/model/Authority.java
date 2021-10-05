package com.codevirtus.membershipsystem.usermanagement.accesscontrol.authorities.model;

import com.codevirtus.membershipsystem.commons.utilities.jpa.BaseEntity;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Audited
@Data
public class Authority extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

}
