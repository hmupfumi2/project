package com.codevirtus.membershipsystem.usermanagement.accesscontrol.userauthorities.service;

import com.codevirtus.membershipsystem.commons.utilities.exceptions.InvalidRequestException;
import com.codevirtus.membershipsystem.commons.utilities.exceptions.RecordNotFoundException;
import com.codevirtus.membershipsystem.commons.utilities.service.DomainServiceImpl;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.authorities.dao.AuthorityDao;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.userauthorities.dao.UserAuthorityDao;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.userauthorities.model.UserAuthority;
import com.codevirtus.membershipsystem.usermanagement.user.dao.UserDao;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;

import static com.codevirtus.membershipsystem.commons.utilities.validations.Validations.validate;

@Service
@Transactional
public class UserAuthorityService extends DomainServiceImpl<UserAuthority, CreateUserAuthorityCommand,
        UserAuthority> {

    private final AuthorityDao authorityDao;

    private final UserDao userDao;

    private final UserAuthorityDao userAuthorityDao;

    public UserAuthorityService(UserAuthorityDao userAuthorityDao, AuthorityDao authorityDao, UserDao userDao) {
        super(userAuthorityDao);
        this.authorityDao = authorityDao;
        this.userDao = userDao;
        this.userAuthorityDao = userAuthorityDao;
    }

    @Override
    @PreAuthorize("hasAuthority('CREATE_USER_AUTHORITY') or hasAnyRole('ROLE_ADMIN', 'ADMIN')")
    public UserAuthority create(CreateUserAuthorityCommand createDto) {

        validate(createDto);

        val user = userDao.findById(createDto.getUserId())
                .orElseThrow(() -> new RecordNotFoundException("User record was not found"));

        val authority = authorityDao.findById(createDto.getAuthorityId())
                .orElseThrow(() -> new RecordNotFoundException("Authority record was not found"));

        if (userAuthorityDao.existsByAuthorityAndUser(authority, user)) {
            throw new InvalidRequestException("Authority already assigned to the user");
        }

        val userAuthority = new UserAuthority();

        userAuthority.setAuthority(authority);

        userAuthority.setUser(user);

        return userAuthorityDao.save(userAuthority);
    }

    @Override
    @PreAuthorize("hasAuthority('DELETE_USER_AUTHORITY') or hasAnyRole('ROLE_ADMIN', 'ADMIN')")
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public UserAuthority update(UserAuthority updateDto) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    public Collection<UserAuthority> findByUser(long userId) {
        return userAuthorityDao.findByUser_Id(userId);
    }

    @Override
    protected Class<UserAuthority> getEntityClass() {
        return UserAuthority.class;
    }


    public Collection<UserAuthority> createAuthorities(CreateUserAuthorityCommand createUserAuthorityCommand) {

        validate(createUserAuthorityCommand);

        val user = userDao.findById(createUserAuthorityCommand.getUserId())
                .orElseThrow(() -> new RecordNotFoundException("User record was not found"));

        val userAuthorities = new HashSet<UserAuthority>();

        createUserAuthorityCommand.getAuthorityIds().forEach(id -> {

            val authority = authorityDao.findById(id)
                    .orElseThrow(() -> new RecordNotFoundException("Authority record was not found for id " + id));

            if (userAuthorityDao.existsByAuthorityAndUser(authority, user)) {
                return;
            }

            val userAuthority = new UserAuthority();

            userAuthority.setAuthority(authority);

            userAuthority.setUser(user);

            val persistedUserAuthority = userAuthorityDao.save(userAuthority);

            userAuthorities.add(persistedUserAuthority);

        });

        return userAuthorities;

    }

    public Page<UserAuthority> findByUser(long userId, Pageable pageable) {
        return userAuthorityDao.findByUser_Id(userId, pageable);
    }

    public void delete(Collection<Long> userAuthorityIds) {
        val authoritiesToBeDeleted = userAuthorityDao.findAllById(userAuthorityIds);
        userAuthorityDao.deleteAll(authoritiesToBeDeleted);
    }
}
