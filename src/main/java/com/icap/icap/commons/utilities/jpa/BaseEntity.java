package com.icap.icap.commons.utilities.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    @CreatedBy
    @Column
    private String createdBy;

    @LastModifiedBy
    @Column
    private String lastModifiedBy;

    @UpdateTimestamp
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "Africa/Harare", locale = "en_ZW", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDate;

    @CreationTimestamp
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "Africa/Harare", locale = "en_ZW", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    @Version
    private Integer version;

    @Column(columnDefinition = "TINYINT default 0")
    private boolean deleted;

    public void pseudoDelete() {
        this.deleted = true;
    }


}
