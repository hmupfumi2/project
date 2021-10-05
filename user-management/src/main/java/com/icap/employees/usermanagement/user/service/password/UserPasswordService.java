package com.codevirtus.membershipsystem.usermanagement.user.service.password;

import com.codevirtus.membershipsystem.commons.utilities.exceptions.InvalidRequestException;
import com.codevirtus.membershipsystem.commons.utilities.exceptions.RecordNotFoundException;
import com.codevirtus.membershipsystem.usermanagement.user.dao.TokenDao;
import com.codevirtus.membershipsystem.usermanagement.user.dao.UserDao;
import com.codevirtus.membershipsystem.usermanagement.user.model.Token;
import com.codevirtus.membershipsystem.usermanagement.user.service.UserReaderService;
import com.codevirtus.membershipsystem.usermanagement.user.service.events.ForgotPasswordEvent;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import static com.codevirtus.membershipsystem.commons.utilities.validations.Validations.validate;

@Service
@Transactional
@Slf4j
public class UserPasswordService {

    private final UserReaderService userReaderService;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final TokenDao tokenDao;

    private final PasswordGenerator passwordGenerator;

    private final PasswordEncoder passwordEncoder;

    private final UserDao userDao;

    public UserPasswordService(UserReaderService userReaderService,
                               ApplicationEventPublisher applicationEventPublisher,
                               TokenDao tokenDao,
                               PasswordGenerator passwordGenerator, PasswordEncoder passwordEncoder, UserDao userDao) {
        this.userReaderService = userReaderService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.tokenDao = tokenDao;
        this.passwordGenerator = passwordGenerator;
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    public void forgotPassword(ForgotPasswordContext forgotPasswordContext) {

        val user = userReaderService.findUser(0, forgotPasswordContext.getUsername(),
                forgotPasswordContext.getEmail());

        val token = tokenDao.findByUser_Id(user.getId())
                .orElse(new Token());

        token.setExpiryDate(Timestamp.valueOf(LocalDateTime.now().plusHours(6)));
        token.setUser(user);

        String tokenValue = passwordGenerator.generate();

        while (tokenDao.existsByValue(tokenValue)) {

            tokenValue = passwordGenerator.generate();

        }

        token.setValue(tokenValue);

        val persistedToken = tokenDao.save(token);

        val forgotPasswordEvent = new ForgotPasswordEvent(this, persistedToken);

        applicationEventPublisher.publishEvent(forgotPasswordEvent);

    }

    public void resetPassword(ResetPasswordContext resetPasswordContext) {

        validate(resetPasswordContext);

        if (!resetPasswordContext.getPassword().equals(resetPasswordContext.getConfirmPassword())) {
            throw new InvalidRequestException("Password and confirm password do not match");
        }

        val tokenValue = resetPasswordContext.getToken();

        val token = tokenDao.findByValue(tokenValue)
                .orElseThrow(() -> new RecordNotFoundException("Token record was not found"));

        val user = token.getUser();

        user.setPassword(passwordEncoder.encode(resetPasswordContext.getPassword()));

        userDao.save(user);

        token.setExpiryDate(new Date());

        token.setExpired(true);

        tokenDao.save(token);

    }

    public void changePassword(UpdatePasswordContext updatePasswordContext) {

        validate(updatePasswordContext);

        if (!updatePasswordContext.getPassword().equals(updatePasswordContext.getConfirmPassword())) {
            throw new InvalidRequestException("Password and confirm password do not match");
        }

        val oldPassword = updatePasswordContext.getOldPassword();

        val user = userReaderService.findUser(0, updatePasswordContext.getUsername(), null);

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new InvalidRequestException("Invalid old password");
        }

        user.setPassword(passwordEncoder.encode(updatePasswordContext.getPassword()));

        userDao.save(user);

    }

}
