package com.icap.icap.commons.utilities.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;


@Component
@Primary
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(accessDeniedException.getMessage())
                .path(request.getRequestURI())
                .exception(AccessDeniedHandler.class.getName())
                .status(String.valueOf(HttpStatus.FORBIDDEN.value()))
                .build();
        accessDeniedException.printStackTrace();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, errorDetails);
        out.flush();
    }
}
