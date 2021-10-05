package com.codevirtus.membershipsystem.usermanagement.group.service;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateGroupCommand {

    @NotBlank(message = "Group name should be provided")
    private String name;

}
