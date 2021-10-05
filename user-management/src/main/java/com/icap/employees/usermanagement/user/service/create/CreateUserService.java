package com.codevirtus.membershipsystem.usermanagement.user.service.create;

import com.codevirtus.membershipsystem.commons.utilities.exceptions.InvalidRequestException;
import com.codevirtus.membershipsystem.commons.utilities.service.SingleResponsibilityActioningService;
import com.codevirtus.membershipsystem.usermanagement.group.service.GroupService;
import com.codevirtus.membershipsystem.usermanagement.user.dao.UserDao;
import com.codevirtus.membershipsystem.usermanagement.user.model.User;
import com.codevirtus.membershipsystem.usermanagement.user.service.events.NewUserEvent;
import com.codevirtus.membershipsystem.usermanagement.user.service.password.PasswordGenerator;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codevirtus.membershipsystem.commons.utilities.validations.Validations.validate;

@Service
@Transactional
public class CreateUserService implements SingleResponsibilityActioningService<User, CreateUserCommand> {

    private final PasswordEncoder passwordEncoder;

    private final PasswordGenerator passwordGenerator;

    private final UserDao userDao;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final GroupService groupService;

    public CreateUserService(PasswordEncoder passwordEncoder,
                             PasswordGenerator passwordGenerator,
                             UserDao userDao, ApplicationEventPublisher applicationEventPublisher,
                             GroupService groupService) {
        this.passwordEncoder = passwordEncoder;
        this.passwordGenerator = passwordGenerator;
        this.userDao = userDao;
        this.applicationEventPublisher = applicationEventPublisher;
        this.groupService = groupService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('CREATE_USER') or hasAnyRole('ROLE_ADMIN', 'ADMIN')")
    public User execute(CreateUserCommand createUserCommand) {

        validate(createUserCommand);

        if (userDao.existsByUsername(createUserCommand.getUsername())) {
            throw new InvalidRequestException("User with the same username [" + createUserCommand.getUsername() + "] " +
                    "already exist");
        }

        if (userDao.existsByEmail(createUserCommand.getEmail())) {
            throw new InvalidRequestException("User with the same email [" + createUserCommand.getEmail() + "] " +
                    "already exist");
        }

        val group = groupService.findById(createUserCommand.getGroupId());

        val user = User.fromCommand(createUserCommand);

        val password = passwordGenerator.generate();

        user.setPassword(passwordEncoder.encode(password));

        user.setGroup(group);

        val persistedUser = userDao.save(user);

        val newUserEvent = new NewUserEvent(this, persistedUser, password);

        applicationEventPublisher.publishEvent(newUserEvent);

        return persistedUser;
    }
}
