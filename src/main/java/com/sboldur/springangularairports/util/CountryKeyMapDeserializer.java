package com.sboldur.springangularairports.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sboldur.springangularairports.model.Country;

import java.io.IOException;

public class CountryKeyMapDeserializer extends KeyDeserializer {
    static ObjectMapper mapper = new ObjectMapper();

    @Override
    public Object deserializeKey(String key, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return mapper.readValue(key, Country.class);
    }
}
