package com.codevirtus.membershipsystem.usermanagement.user.dao;

import com.codevirtus.membershipsystem.commons.utilities.jpa.BaseDao;
import com.codevirtus.membershipsystem.usermanagement.user.model.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
