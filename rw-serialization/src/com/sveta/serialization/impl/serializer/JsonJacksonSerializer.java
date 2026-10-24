package com.sveta.serialization.impl.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sveta.serialization.ObjectSerializer;
import com.sveta.serialization.SuperSerializationDto;

import java.io.File;

public class JsonJacksonSerializer implements ObjectSerializer {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .registerModule(new JavaTimeModule());

    @Override
    public <T extends SuperSerializationDto> void save(T object, String filePath) throws Exception {
        if (object == null) {
            throw new IllegalArgumentException("Object to save cannot be null");
        }
        objectMapper.writeValue(new File(filePath), object);
    }

    @Override
    public <T extends SuperSerializationDto> T load(String filePath, Class<T> clazz) throws Exception {
        return objectMapper.readValue(new File(filePath), clazz);
    }
}