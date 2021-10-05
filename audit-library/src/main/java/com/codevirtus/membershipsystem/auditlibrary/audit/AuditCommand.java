package com.codevirtus.membershipsystem.auditlibrary.audit;

import lombok.Data;

/**
 * @author Wilson Chiviti
 */
@Data
public class AuditCommand {

    private Action action;

    private Resource resource;

    private Origin origin;

}
