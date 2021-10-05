package com.codevirtus.membershipsystem.auditlibrary.audit;

import lombok.Data;

import javax.persistence.Embeddable;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Data
@Embeddable
public class Origin {

    private String serviceName;

    private String sourceIP;

    private String host;

    private String remoteUser;

    private String country;

    private String timeZone;

    private String userAgent;

    private String operatingSystem;

    private String browser;

    public static void validateOrigin(Origin origin) {
        requireNonNull(origin, "Origin details are required");
        checkArgument(nonNull(origin.getSourceIP()),
                "Source ip should be provided");
        checkArgument(nonNull(origin.getServiceName()),
                "Origin service name should be provided");
    }

}
