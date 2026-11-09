package com.sveta.serialization.impl.serializer.javaIo;

import com.sveta.serialization.impl.serializer.files.ObjectSerializer;

import java.util.List;

public class SerializerFactory {
    private SerializerFactory() {
    }

    public static List<ObjectSerializer> createAll() {
        return List.of(
                new JsonJacksonSerializer(),
                new JavaIoSerializer(),
                new KryoSerializer(),
                new XmlSerializer());
    }
}