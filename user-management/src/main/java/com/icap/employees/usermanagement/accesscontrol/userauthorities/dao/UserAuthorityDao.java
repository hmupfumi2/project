package com.codevirtus.membershipsystem.usermanagement.accesscontrol.userauthorities.dao;

import com.codevirtus.membershipsystem.commons.utilities.jpa.BaseDao;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.authorities.model.Authority;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.userauthorities.model.UserAuthority;
import com.codevirtus.membershipsystem.usermanagement.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface UserAuthorityDao extends BaseDao<UserAuthority> {

    Collection<UserAuthority> findByUser(User user);

    Collection<UserAuthority> findByUser_Id(long userId);

    boolean existsByAuthorityAndUser(Authority authority, User user);

    Page<UserAuthority> findByUser_Id(long userId, Pageable pageable);
}
