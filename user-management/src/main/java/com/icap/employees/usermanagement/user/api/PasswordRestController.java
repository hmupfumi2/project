package com.codevirtus.membershipsystem.usermanagement.user.api;

import com.codevirtus.membershipsystem.usermanagement.user.service.password.ForgotPasswordContext;
import com.codevirtus.membershipsystem.usermanagement.user.service.password.ResetPasswordContext;
import com.codevirtus.membershipsystem.usermanagement.user.service.password.UpdatePasswordContext;
import com.codevirtus.membershipsystem.usermanagement.user.service.password.UserPasswordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Api(tags = "Password Management")
public class PasswordRestController {

    private final UserPasswordService userPasswordService;

    public PasswordRestController(UserPasswordService userPasswordService) {
        this.userPasswordService = userPasswordService;
    }

    @PostMapping("/v1/users/forgot-password")
    @ApiOperation("Forgot Password")
    public void forgotPassword(@RequestBody ForgotPasswordContext forgotPasswordContext) {
        userPasswordService.forgotPassword(forgotPasswordContext);
    }

    @PostMapping("/v1/users/reset-password")
    @ApiOperation("Reset Password")
    public void resetPassword(@RequestBody ResetPasswordContext resetPasswordContext) {
        userPasswordService.resetPassword(resetPasswordContext);
    }

    @PostMapping("/v1/users/update-password")
    @ApiOperation("Update Password")
    public void forgotPassword(@RequestBody UpdatePasswordContext updatePasswordContext, Principal principal) {
        updatePasswordContext.setUsername(principal.getName());
        userPasswordService.changePassword(updatePasswordContext);
    }

}
