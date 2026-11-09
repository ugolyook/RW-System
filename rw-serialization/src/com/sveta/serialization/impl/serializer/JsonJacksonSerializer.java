package com.sveta.serialization.impl.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.nio.file.Path;


public class JsonJacksonSerializer extends AbstractFileSerializer {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Override
    public SerializerType getSerializerName() {
        return SerializerType.JSON_JACKSON_SERIALIZER;
    }

    @Override
    protected void write(SuperSerializationDto object, Path path) throws Exception {
        objectMapper.writeValue(path.toFile(), object);
    }

    @Override
    protected Object read(Path path, Class<? extends SuperSerializationDto> clazz) throws Exception {
        return objectMapper.readValue(path.toFile(), clazz);
    }
}