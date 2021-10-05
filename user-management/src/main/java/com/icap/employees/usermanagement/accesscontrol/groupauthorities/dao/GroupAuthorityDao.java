package com.codevirtus.membershipsystem.usermanagement.accesscontrol.groupauthorities.dao;

import com.codevirtus.membershipsystem.commons.utilities.jpa.BaseDao;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.authorities.model.Authority;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.groupauthorities.model.GroupAuthority;
import com.codevirtus.membershipsystem.usermanagement.group.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface GroupAuthorityDao extends BaseDao<GroupAuthority> {

    Collection<GroupAuthority> findByGroup(Group group);

    Collection<GroupAuthority> findByGroup_Id(long groupId);

    Page<GroupAuthority> findByGroup_Id(long groupId, Pageable pageable);

    boolean existsByAuthorityAndGroup(Authority authority, Group group);

}
