package com.icap.icap.commons.utilities.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;


public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    public static final String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";


    public LocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime date, JsonGenerator generator, SerializerProvider arg2) throws IOException {

        if(isNull(date)){
            generator.writeNull();
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        final String dateString = date.format(formatter);
        generator.writeString(dateString);
    }
}
