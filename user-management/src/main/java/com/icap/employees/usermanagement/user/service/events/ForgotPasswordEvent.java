package com.codevirtus.membershipsystem.usermanagement.user.service.events;

import com.codevirtus.membershipsystem.usermanagement.user.model.Token;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ForgotPasswordEvent extends ApplicationEvent {

    private final Token token;

    public ForgotPasswordEvent(Object source, Token token) {
        super(source);
        this.token = token;
    }
}
