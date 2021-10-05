package com.codevirtus.membershipsystem.auditlibrary.audit;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Embeddable
@Data
@Table(name = "actions")
public class Action {

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column
    private String performedBy;

    private String performedByFullName;

    @Enumerated(value = EnumType.STRING)
    @Column
    private ActionType type;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ActionStatus actionStatus;

    public static void validateAction(Action action) {
        requireNonNull(action, "Action details should be provided");
        checkArgument(nonNull(action.getPerformedBy()),
                "Valid action performer is required");
        checkArgument(nonNull(action.getType()), "Action type should be specified");
        checkArgument(nonNull(action.getDate()),
                "Date of action should be specified");
    }


}
