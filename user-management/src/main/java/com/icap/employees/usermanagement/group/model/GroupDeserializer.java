package com.codevirtus.membershipsystem.usermanagement.group.model;

import com.codevirtus.membershipsystem.commons.utilities.beans.BeanUtil;
import com.codevirtus.membershipsystem.usermanagement.group.service.GroupService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.val;

import java.io.IOException;

import static java.util.Objects.isNull;

public class GroupDeserializer extends StdDeserializer<Group> {

    public GroupDeserializer() {
        super(Group.class);
    }

    @Override
    public Group deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        val groupId = jsonParser.readValueAs(Long.class);

        if (isNull(groupId)) {
            return null;
        }

        val groupService = BeanUtil.getBean(GroupService.class);

        return groupService.findById(groupId);
    }
}
