package com.codevirtus.membershipsystem.usermanagement.group.service;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateGroupCommand extends CreateGroupCommand {

    private long id;

}
