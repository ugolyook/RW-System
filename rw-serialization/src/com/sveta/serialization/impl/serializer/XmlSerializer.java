package com.sveta.serialization.impl.serializer;

import com.sveta.serialization.impl.test.ObjectSerializer;
import com.sveta.serialization.impl.test.SerializerType;
import com.sveta.serialization.impl.test.SuperSerializationDto;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XmlSerializer implements ObjectSerializer {
    @Override
    public SerializerType getSerializerType() {
        return SerializerType.XmlSerializer;
    }

    @Override
    public <T extends SuperSerializationDto> void save(T object, String filePath) throws Exception {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            encoder.writeObject(object);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends SuperSerializationDto> T load(String filePath, Class<T> clazz) throws Exception {
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)))) {
            return (T) decoder.readObject();
        }
    }
}