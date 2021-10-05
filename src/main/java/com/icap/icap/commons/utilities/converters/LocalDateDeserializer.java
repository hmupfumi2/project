package com.icap.icap.commons.utilities.converters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.val;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import static java.util.Objects.isNull;


public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    static final String DATE_FORMAT = "MM/dd/yyyy";

    public LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern(DATE_FORMAT)
                .toFormatter(Locale.ENGLISH);
        val value = parser.readValueAs(String.class);
        if(isNull(value) || value.trim().isEmpty()){
            return null;
        }
        return LocalDate.parse(value, dateTimeFormatter);
    }

}
