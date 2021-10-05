package com.codevirtus.membershipsystem.auditlibrary.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author Wilson Chiviti
 */

@Slf4j
@Component
class AuditTrailRepoImpl implements AuditTrailRepo {

    private final ApplicationEventPublisher applicationEventPublisher;

    AuditTrailRepoImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void create(AuditCommand auditCommand) {
        log.debug("### creating audit");
        applicationEventPublisher.publishEvent(new CreateAuditEvent(auditCommand));

    }
}
