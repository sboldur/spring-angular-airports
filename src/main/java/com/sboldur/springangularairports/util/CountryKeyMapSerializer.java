package com.sboldur.springangularairports.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sboldur.springangularairports.model.Country;

import java.io.IOException;

public class CountryKeyMapSerializer extends JsonSerializer<Country> {
    static ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(Country country, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String json = mapper.writeValueAsString( country );
        jsonGenerator.writeFieldName( json );

    }
}
