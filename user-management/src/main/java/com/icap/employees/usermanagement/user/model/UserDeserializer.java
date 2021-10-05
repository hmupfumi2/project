package com.codevirtus.membershipsystem.usermanagement.user.model;

import com.codevirtus.membershipsystem.commons.utilities.beans.BeanUtil;
import com.codevirtus.membershipsystem.usermanagement.user.service.UserReaderService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.val;

import java.io.IOException;

import static java.util.Objects.isNull;

public class UserDeserializer extends StdDeserializer<User> {

    protected UserDeserializer() {
        super(User.class);
    }

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        val userId = jsonParser.readValueAs(Long.class);

        if(isNull(userId)){
            return null;
        }

        val userService = BeanUtil.getBean(UserReaderService.class);

        return userService.findById(userId);
    }
}
