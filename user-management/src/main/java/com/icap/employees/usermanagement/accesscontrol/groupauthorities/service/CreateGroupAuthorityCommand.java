package com.codevirtus.membershipsystem.usermanagement.accesscontrol.groupauthorities.service;

import lombok.Data;

import java.util.Collection;

@Data
public class CreateGroupAuthorityCommand {

    private Collection<Long> authorityIds;

    private Long groupId;

    private long authorityId;

}
