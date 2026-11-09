package com.sveta.serialization.impl.serializer.javaIo;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sveta.serialization.impl.serializer.files.AbstractFileSerializer;
import com.sveta.serialization.impl.serializer.files.SerializerType;
import com.sveta.serialization.impl.serializer.core.SuperSerializationDto;

import java.nio.file.Path;

public class XmlSerializer extends AbstractFileSerializer {
    private final XmlMapper mapper = (XmlMapper) new XmlMapper()
            .registerModule(new JavaTimeModule())
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Override
    public SerializerType getSerializerName() { return SerializerType.XML_SERIALIZER; }

    @Override
    protected void write(SuperSerializationDto object, Path path) throws Exception {
        mapper.writeValue(path.toFile(), object);
    }

    @Override
    protected Object read(Path path, Class<? extends SuperSerializationDto> clazz) throws Exception {
        return mapper.readValue(path.toFile(), clazz);
    }
}