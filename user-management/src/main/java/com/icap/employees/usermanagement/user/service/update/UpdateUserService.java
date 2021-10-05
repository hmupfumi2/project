package com.codevirtus.membershipsystem.usermanagement.user.service.update;

import com.codevirtus.membershipsystem.commons.utilities.exceptions.InvalidRequestException;
import com.codevirtus.membershipsystem.usermanagement.user.dao.UserDao;
import com.codevirtus.membershipsystem.usermanagement.user.model.User;
import com.codevirtus.membershipsystem.usermanagement.user.service.UserReaderService;
import com.codevirtus.membershipsystem.usermanagement.user.service.events.UserAccountStatusChangeEvent;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codevirtus.membershipsystem.commons.utilities.validations.Validations.validate;

@Service
@Slf4j
@Transactional
public class UpdateUserService {

    private final UserDao userDao;

    private final UserReaderService userReaderService;

    private final ApplicationEventPublisher applicationEventPublisher;

    public UpdateUserService(UserDao userDao, UserReaderService userReaderService,
                             ApplicationEventPublisher applicationEventPublisher) {
        this.userDao = userDao;
        this.userReaderService = userReaderService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PreAuthorize("hasAnyAuthority('UPDATE_USER') or hasAnyRole('ROLE_ADMIN', 'ADMIN')")
    public User update(UpdateUserCommand updateUserCommand) {

        return updateUser(updateUserCommand);

    }

    @PreAuthorize("hasAnyAuthority('DISABLE_USER', 'ENABLE_USER') or hasAnyRole('ROLE_ADMIN', 'ADMIN')")
    public User changeUserStatus(long userId, boolean status) {

        val user = userReaderService.findUser(userId, null, null);

        user.setEnabled(status);

        val updatedUser = userDao.save(user);

        val userAccountStatusChangeEvent = new UserAccountStatusChangeEvent(this, updatedUser);

        applicationEventPublisher.publishEvent(userAccountStatusChangeEvent);

        return updatedUser;

    }

    @PreAuthorize("#updateUserCommand.username == authentication.principal.username")
    public User updateMyAccount(UpdateUserCommand updateUserCommand) {
        return updateUser(updateUserCommand);
    }

    public User updateUser(UpdateUserCommand updateUserCommand) {

        validate(updateUserCommand);

        userDao.findByEmail(updateUserCommand.getEmail())
                .filter(user ->
                        !user.getUsername().equals(updateUserCommand.getUsername()) || (user.getId() != updateUserCommand.getId()))
                .ifPresent(user -> {
                    throw new InvalidRequestException("Email is already being used by another account");
                });

        val user = userReaderService.findUser(updateUserCommand.getId(), updateUserCommand.getUsername(), null);

        user.setEmail(updateUserCommand.getEmail());
        user.setFirstName(updateUserCommand.getFirstName());
        user.setInitials(updateUserCommand.getInitials());
        user.setTitle(updateUserCommand.getTitle());
        user.setGender(updateUserCommand.getGender());
        user.setLastName(updateUserCommand.getLastName());
        user.setNationalIdentificationNumber(updateUserCommand.getNationalIdentificationNumber());
        user.setPassportNumber(updateUserCommand.getPassportNumber());
        user.setDriverLicenseNumber(updateUserCommand.getDriverLicenseNumber());
        user.setDateOfBirth(updateUserCommand.getDateOfBirth());
        user.setNationality(updateUserCommand.getNationality());
        user.setPhoneNumber(updateUserCommand.getPhoneNumber());

        return userDao.save(user);
    }
}
