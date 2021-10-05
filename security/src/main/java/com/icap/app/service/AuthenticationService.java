package com.icap.app.service;

import com.icap.app.acesscontrol.SystemGroups;
import com.icap.app.commons.utilities.exceptions.RecordNotFoundException;
import com.icap.app.jwt.JwtTokenUtil;
import com.icap.app.jwt.JwtUserDetailsService;
import com.icap.app.member.profile.context.MemberProfileService;
import com.icap.app.usermanagement.user.model.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    private final MemberProfileService memberProfileService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                                 JwtUserDetailsService userDetailsService, MemberProfileService memberProfileService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.memberProfileService = memberProfileService;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(token);

    }

    private void authenticate(String username, String password) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {

            throw new Exception("USER_DISABLED", e);

        } catch (BadCredentialsException e) {

            throw new Exception("INVALID_CREDENTIALS", e);

        }
    }

    public AuthenticationResponse authenticateMember(AuthenticationRequest authenticationRequest) throws Exception {

        final User userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        if (!userDetails.getGroup().getName().equalsIgnoreCase(SystemGroups.MEMBER.name())) {
            throw new AccessDeniedException("Only a member can login to this portal.");
        }

        Optional.ofNullable(memberProfileService.findByUsername(authenticationRequest.getUsername()))
                .orElseThrow(() -> new RecordNotFoundException("Member record was not found for the given user."));

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(token);
    }
}
