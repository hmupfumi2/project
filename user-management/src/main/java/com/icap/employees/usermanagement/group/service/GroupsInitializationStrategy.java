package com.codevirtus.membershipsystem.usermanagement.group.service;

import com.codevirtus.membershipsystem.usermanagement.group.dao.GroupDao;
import com.codevirtus.membershipsystem.usermanagement.group.model.Group;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Collection;

@Slf4j
public abstract class GroupsInitializationStrategy {

    protected final GroupDao groupDao;

    protected GroupsInitializationStrategy(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    protected void persistGroups(Collection<String> groups) {
        groups.forEach(groupName -> {
            val exists = groupDao.existsByName(groupName);
            if (exists) {
                return;
            }
            val authority = new Group();
            authority.setName(groupName);
            groupDao.save(authority);
            log.debug("### Group created : {}", groupName);
        });
    }

}
