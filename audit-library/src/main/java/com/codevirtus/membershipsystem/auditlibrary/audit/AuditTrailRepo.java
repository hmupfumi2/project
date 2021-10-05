package com.codevirtus.membershipsystem.auditlibrary.audit;

import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Wilson Chiviti
 */
public interface AuditTrailRepo {

    void create(@RequestBody AuditCommand auditCommand);

}
