package com.codevirtus.membershipsystem.usermanagement.user.dao;

import com.codevirtus.membershipsystem.commons.utilities.jpa.BaseDao;
import com.codevirtus.membershipsystem.usermanagement.user.model.Token;

import java.util.Optional;

public interface TokenDao extends BaseDao<Token> {

    Optional<Token> findByUser_Id(long userId);

    Optional<Token> findByValue(String tokenValue);

    boolean existsByValue(String tokenValue);

}
