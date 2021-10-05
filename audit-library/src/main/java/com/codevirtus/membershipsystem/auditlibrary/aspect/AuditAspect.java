package com.codevirtus.membershipsystem.auditlibrary.aspect;

import com.codevirtus.membershipsystem.auditlibrary.annotations.Auditable;
import com.codevirtus.membershipsystem.auditlibrary.audit.*;
import com.codevirtus.membershipsystem.auditlibrary.security.SecurityContextHolderIdentity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Wilson Chiviti
 */

@Aspect
@Slf4j
@Component
public class AuditAspect {

    private final SecurityContextHolderIdentity securityContextHolderIdentity;

    private final AuditTrailRepo auditTrailRepo;


    public AuditAspect(SecurityContextHolderIdentity securityContextHolderIdentity, AuditTrailRepo auditTrailRepo) {
        this.securityContextHolderIdentity = securityContextHolderIdentity;
        this.auditTrailRepo = auditTrailRepo;
    }

    @AfterReturning(value = "@annotation(com.codevirtus.membershipsystem.auditlibrary.annotations.Auditable)", returning = "retVal")
    public Object doAuditOnAction(JoinPoint joinPoint, Object retVal) {

        log.debug("### Auditing after action done");

        val methodSignature = (MethodSignature) joinPoint.getSignature();
        val method = methodSignature.getMethod();
        Auditable auditable = method.getAnnotation(Auditable.class);

        val audit = new AuditCommand();

        audit.setAction(createAction(auditable, ActionStatus.COMPLETE));

        audit.setOrigin(createOrigin(auditable));

        val resource = new Resource();
        try {
            resource.setDescription(new ObjectMapper().writeValueAsString(retVal));
        } catch (JsonProcessingException e) {
            log.error("### Failed to converting to string", e);
            resource.setDescription(auditable.resource().description());
        }
        resource.setName(auditable.resource().name());
        audit.setResource(resource);

        auditTrailRepo.create(audit);

        return retVal;

    }

    @Before(value = "@annotation(com.codevirtus.membershipsystem.auditlibrary.annotations.Auditable)")
    public void doAuditOnAction(JoinPoint joinPoint) {

        log.debug("### Auditing before action");

        val methodSignature = (MethodSignature) joinPoint.getSignature();
        val method = methodSignature.getMethod();
        Auditable auditable = method.getAnnotation(Auditable.class);

        val audit = new AuditCommand();

        audit.setAction(createAction(auditable, ActionStatus.STARTED));

        audit.setOrigin(createOrigin(auditable));

        val resource = new Resource();
        resource.setDescription(auditable.resource().description());
        resource.setName(auditable.resource().name());
        audit.setResource(resource);

        auditTrailRepo.create(audit);

    }

    private Action createAction(Auditable auditable, ActionStatus actionStatus) {
        val action = new Action();
        action.setDate(Timestamp.valueOf(LocalDateTime.now()));
        action.setPerformedBy(securityContextHolderIdentity.getUsername());
        action.setType(auditable.action().type());
        action.setActionStatus(actionStatus);
        return action;
    }

    private Origin createOrigin(Auditable auditable) {
        val origin = new Origin();
        origin.setHost(securityContextHolderIdentity.getRemoteHost());
        origin.setSourceIP(securityContextHolderIdentity.getRemoteIp());
        origin.setServiceName(auditable.origin().serviceName());
        origin.setRemoteUser(securityContextHolderIdentity.getRemoteUser());
        origin.setCountry(securityContextHolderIdentity.getCountry());
        origin.setBrowser(securityContextHolderIdentity.getBrowser());
        origin.setOperatingSystem(securityContextHolderIdentity.getOperatingSystem());
        origin.setTimeZone(securityContextHolderIdentity.getTimeZone());
        origin.setUserAgent(securityContextHolderIdentity.getUserAgent());
        return origin;
    }


}
