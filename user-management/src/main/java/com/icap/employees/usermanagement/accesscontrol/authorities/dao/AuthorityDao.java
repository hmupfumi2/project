package com.codevirtus.membershipsystem.usermanagement.accesscontrol.authorities.dao;

import com.codevirtus.membershipsystem.commons.utilities.jpa.BaseDao;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.authorities.model.Authority;

public interface AuthorityDao extends BaseDao<Authority> {

    boolean existsByName(String name);

}
