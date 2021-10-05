package com.codevirtus.membershipsystem.usermanagement.group.service;

import com.codevirtus.membershipsystem.commons.utilities.exceptions.InvalidRequestException;
import com.codevirtus.membershipsystem.commons.utilities.service.DomainServiceImpl;
import com.codevirtus.membershipsystem.usermanagement.group.dao.GroupDao;
import com.codevirtus.membershipsystem.usermanagement.group.model.Group;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codevirtus.membershipsystem.commons.utilities.validations.Validations.validate;

@Service
@Transactional
@Slf4j
public class GroupService extends DomainServiceImpl<Group, CreateGroupCommand, UpdateGroupCommand> {

    private final GroupDao groupDao;

    public GroupService(GroupDao groupDao) {
        super(groupDao);
        this.groupDao = groupDao;
    }

    @Override
    @PreAuthorize("hasAuthority('CREATE_GROUP')")
    public Group create(CreateGroupCommand createDto) {

        validate(createDto);

        if (groupDao.existsByName(createDto.getName())) {
            throw new InvalidRequestException("Group with same already exist");
        }

        val group = new Group();

        group.setName(createDto.getName());

        return groupDao.save(group);

    }

    @Override
    @PreAuthorize("hasAuthority('UPDATE_GROUP')")
    public Group update(UpdateGroupCommand updateDto) {

        validate(updateDto);

        groupDao.findByName(updateDto.getName())
                .filter(group -> group.getId() != updateDto.getId())
                .ifPresent(group -> {
                    throw new InvalidRequestException("Group with same name already exist");
                });

        val group = findById(updateDto.getId());

        group.setName(updateDto.getName());

        return groupDao.save(group);
    }

    @Override
    @PreAuthorize("hasAuthority('DELETE_GROUP')")
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    protected Class<Group> getEntityClass() {
        return Group.class;
    }
}
