package com.codevirtus.membershipsystem.usermanagement.group.dao;

import com.codevirtus.membershipsystem.commons.utilities.jpa.BaseDao;
import com.codevirtus.membershipsystem.usermanagement.group.model.Group;

import java.util.Optional;

public interface GroupDao extends BaseDao<Group> {

    Optional<Group> findByName(String name);

    boolean existsByName(String name);

}
