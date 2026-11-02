package com.sveta.serialization.impl.serializer;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sveta.serialization.ObjectSerializer;
import com.sveta.serialization.SerializerType;
import com.sveta.serialization.SuperSerializationDto;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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