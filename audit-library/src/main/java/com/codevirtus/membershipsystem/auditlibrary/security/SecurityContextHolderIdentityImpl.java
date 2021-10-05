package com.codevirtus.membershipsystem.auditlibrary.security;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.TimeZone;

import static java.util.Objects.nonNull;


/**
 * @author Wilson Chiviti
 */
@Slf4j
@Component
public class SecurityContextHolderIdentityImpl implements SecurityContextHolderIdentity {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public String getUsername() {
        val principal = httpServletRequest.getUserPrincipal();
        if (nonNull(principal)) {
            log.debug("### Security Context Holder obtained, the holder's name is {}", principal.getName());
            return principal.getName();
        } else
            return "Not Authenticated";
    }

    @Override
    public String getRemoteIp() {
        String ipAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = httpServletRequest.getRemoteAddr();
        }
        return ipAddress;
    }

    @Override
    public String getRemoteHost() {
        String remoteHost = httpServletRequest.getHeader("X-Host-Header");
        if (remoteHost == null) {
            remoteHost = httpServletRequest.getRemoteHost();
        }
        return remoteHost;
    }

    @Override
    public String getRemoteUser() {
        return httpServletRequest.getRemoteUser();
    }

    @Override
    public String getCountry() {

        val tempLocale = RequestContextUtils.getLocale(httpServletRequest);
        return tempLocale.getCountry();
    }

    @Override
    public String getTimeZone() {
        val tempTimeZone = RequestContextUtils.getTimeZone(httpServletRequest);
        return tempTimeZone != null ? tempTimeZone.getDisplayName() : TimeZone.getDefault().getDisplayName();

    }

    @Override
    public String getUserAgent() {
        return httpServletRequest.getHeader("User-Agent");
    }

    @Override
    public String getOperatingSystem() {
        return getClientOS(httpServletRequest);
    }

    @Override
    public String getBrowser() {
        return httpServletRequest.getHeader("X-Browser-Header");
    }

    public String getClientOS(HttpServletRequest request) {
        final String browserDetails = request.getHeader("User-Agent");

        final String lowerCaseBrowser = browserDetails.toLowerCase();
        if (lowerCaseBrowser.contains("windows")) {
            return "Windows";
        } else if (lowerCaseBrowser.contains("mac")) {
            return "Mac";
        } else if (lowerCaseBrowser.contains("x11")) {
            return "Unix";
        } else if (lowerCaseBrowser.contains("android")) {
            return "Android";
        } else if (lowerCaseBrowser.contains("iphone")) {
            return "IPhone";
        } else {
            return "UnKnown, More-Info: " + browserDetails;
        }
    }

}
