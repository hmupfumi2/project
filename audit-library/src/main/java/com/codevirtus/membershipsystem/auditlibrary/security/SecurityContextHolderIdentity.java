package com.codevirtus.membershipsystem.auditlibrary.security;

/**
 * @author Wilson Chiviti
 * Provides the api for getting the details required in an audit which include username, remote ip address,
 * remote host and remote user
 */
public interface SecurityContextHolderIdentity {

    /**
     * Provides the username of the current Security context holder
     *
     * @return String value which the username or name of the current security holder
     */
    String getUsername();

    /**
     * Provides the remote Internet Protocol (IP) address of the origin of the request or action
     *
     * @return String representation of the IP address
     */
    String getRemoteIp();

    /**
     * Provides the remote host name of the origin of the request or action
     *
     * @return String value of the remote host name
     */
    String getRemoteHost();

    /**
     * Provides the name of the remote user
     *
     * @return String value of the remote user
     */
    String getRemoteUser();

    String getCountry();

    String getTimeZone();

    String getUserAgent();

    String getOperatingSystem();

    String getBrowser();

}
