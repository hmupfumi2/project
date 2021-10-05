package com.codevirtus.membershipsystem.usermanagement.user.service;

import com.codevirtus.membershipsystem.commons.utilities.exceptions.RecordNotFoundException;
import com.codevirtus.membershipsystem.commons.utilities.service.AbstractReaderService;
import com.codevirtus.membershipsystem.usermanagement.user.dao.UserDao;
import com.codevirtus.membershipsystem.usermanagement.user.model.User;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class UserReaderService extends AbstractReaderService<User> {

    private final UserDao userDao;

    public UserReaderService(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    public User findUser(long userId, String username, String email) {

        if (nonNull(username)) {
            return userDao.findByUsername(username)
                    .orElseThrow(() -> new RecordNotFoundException("User record was not found"));
        }

        if (nonNull(email)) {
            return userDao.findByEmail(email)
                    .orElseThrow(() -> new RecordNotFoundException("User record was not found"));
        }

        return userDao.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User record was not found"));

    }

}
