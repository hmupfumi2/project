package com.icap.icap.commons.utilities.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.icap.icap.commons.utilities.converters.LocalDateDeserializer.DATE_FORMAT;
import static java.util.Objects.isNull;


public class LocalDateSerializer extends StdSerializer<LocalDate> {

    public LocalDateSerializer() {
        super(LocalDate.class);
    }

    @Override
    public void serialize(LocalDate date, JsonGenerator generator, SerializerProvider arg2) throws IOException {

        if(isNull(date)){
            generator.writeNull();
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        final String dateString = date.format(formatter);
        generator.writeString(dateString);
    }
}
