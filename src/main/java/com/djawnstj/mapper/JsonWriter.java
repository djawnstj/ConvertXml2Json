package com.djawnstj.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonWriter extends ObjectMapper {

    public static JsonWriter INSTANCE = new JsonWriter();

    private final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

    @Override
    public String writeValueAsString(final Object value) throws JsonProcessingException {
        return new String(this.mapper.writeValueAsBytes(value), StandardCharsets.UTF_8);
    }
}
