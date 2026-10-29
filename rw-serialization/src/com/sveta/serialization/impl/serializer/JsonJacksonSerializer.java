package com.sveta.serialization.impl.serializer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sveta.serialization.impl.test.ObjectSerializer;
import com.sveta.serialization.impl.test.SerializerType;
import com.sveta.serialization.impl.test.SuperSerializationDto;
import com.sveta.serialization.impl.dto.carriagesDto.CoupeCarriageSerializationDto;
import com.sveta.serialization.impl.dto.carriagesDto.DiningCarriageSerializationDto;
import com.sveta.serialization.impl.dto.carriagesDto.EconomyCarriageSerializationDto;
import com.sveta.serialization.impl.dto.carriagesDto.SeatedCarriageSerializationDto;

import java.io.File;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CoupeCarriageSerializationDto.class, name = "coupe"),
        @JsonSubTypes.Type(value = SeatedCarriageSerializationDto.class, name = "seated"),
        @JsonSubTypes.Type(value = DiningCarriageSerializationDto.class, name = "dining"),
        @JsonSubTypes.Type(value = EconomyCarriageSerializationDto.class, name = "economy")
})

public class JsonJacksonSerializer implements ObjectSerializer {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .registerModule(new JavaTimeModule());

    @Override
    public SerializerType getSerializerType() {
        return SerializerType.JsonJacksonSerializer;
    }

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