package com.codevirtus.membershipsystem.usermanagement.user.service.events;

import com.codevirtus.membershipsystem.notifications.service.EmailMessageFormatter;
import com.codevirtus.membershipsystem.notifications.service.EmailMessageNotifierTemplate;
import com.codevirtus.membershipsystem.notifications.service.EmailSender;
import com.codevirtus.membershipsystem.notifications.service.EmailUserImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class ForgotPasswordEventListener extends EmailMessageNotifierTemplate
        implements ApplicationListener<ForgotPasswordEvent> {

    @Value("${system.name}")
    private String systemName;

    public ForgotPasswordEventListener(EmailSender emailSender) {
        super(emailSender);
    }

    @Override
    public void onApplicationEvent(ForgotPasswordEvent forgotPasswordEvent) {

        val token = forgotPasswordEvent.getToken();

        val user = token.getUser();

        recipients.add(new EmailUserImpl(user.getUsername(), user.getEmail()));

        subject = systemName + " : Forgot password token";

        emailMessageFormatter.addParagraph("Please use the following token to proceed in resetting " +
                "your password");

        emailMessageFormatter.addParagraph(EmailMessageFormatter.boldText("Token : ") + token.getValue());
        emailMessageFormatter.addParagraph(EmailMessageFormatter.boldText("Token expired on : ") +
                DateTimeFormatter.RFC_1123_DATE_TIME.format(token.getExpiryDate().toInstant()));

        sendEmail();

    }
}
