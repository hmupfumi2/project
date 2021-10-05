package com.icap.app.api;

import com.icap.app.service.AuthenticationRequest;
import com.icap.app.service.AuthenticationResponse;
import com.icap.app.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Api(tags = "Authentication/Login")
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;

    public AuthenticationRestController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/authenticate")
    @ApiOperation("Create an Authentication token or Login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return authenticationService.authenticate(authenticationRequest);
    }

    @PostMapping(value = "/authenticate/member")
    @ApiOperation("Create an Authentication token or Login for member")
    public AuthenticationResponse createMemberAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return authenticationService.authenticateMember(authenticationRequest);
    }

}
