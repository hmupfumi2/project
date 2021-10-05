package com.codevirtus.membershipsystem.auditlibrary.audit;

import org.springframework.context.ApplicationEvent;

/**
 * @author Wilson Chiviti
 * @created 10/11/2020 - 20:46
 * @project event-ticketing-engine
 */
public class CreateAuditEvent extends ApplicationEvent {
    public CreateAuditEvent(AuditCommand auditCommand) {
        super(auditCommand);
    }
}
