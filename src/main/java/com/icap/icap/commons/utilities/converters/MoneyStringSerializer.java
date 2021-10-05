package com.icap.icap.commons.utilities.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;



@Slf4j
public class MoneyStringSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        try {
            val formatCurrency = NumberFormat.getCurrencyInstance(Locale.ENGLISH);

            val amount = formatCurrency.format(Double.valueOf(value));

            val charSequence = amount.toCharArray();
            int index = 0;
            for(char ch : charSequence){
                if(Character.isDigit(ch)){
                    break;
                }
                index++;
            }

            val result = amount.substring(index);

            log.trace("### Money Deserializer converted value : {}", result);

            gen.writeString(result);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
