package com.codevirtus.membershipsystem.usermanagement.accesscontrol.groupuser;

import com.codevirtus.membershipsystem.commons.utilities.exceptions.InvalidRequestException;
import com.codevirtus.membershipsystem.commons.utilities.exceptions.RecordNotFoundException;
import com.codevirtus.membershipsystem.usermanagement.group.dao.GroupDao;
import com.codevirtus.membershipsystem.usermanagement.user.dao.UserDao;
import com.codevirtus.membershipsystem.usermanagement.user.model.User;
import lombok.val;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserGroupService {

    private final UserDao userDao;

    private final GroupDao groupDao;


    public UserGroupService(UserDao userDao, GroupDao groupDao) {
        this.userDao = userDao;
        this.groupDao = groupDao;
    }

    @PreAuthorize("hasAuthority('CREATE_GROUP_USER') or hasAnyRole('ROLE_ADMIN', 'ADMIN')")
    public User changeUserGroup(UserGroupCommand userGroupCommand) {

        val user = userDao.findById(userGroupCommand.getUserId())
                .orElseThrow(() -> new RecordNotFoundException("User record was not found"));

        val group = groupDao.findById(userGroupCommand.getGroupId())
                .orElseThrow(() -> new RecordNotFoundException("Group record was not found"));

        if (user.getGroup().equals(group)) {
            throw new InvalidRequestException("User already assigned to group");
        }

        user.setGroup(group);

        return userDao.save(user);

    }

}
