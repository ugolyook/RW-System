package com.sveta.serialization.impl.serializer;

import com.sveta.serialization.ObjectSerializer;

import java.util.List;

public class SerializerFactory {
    private SerializerFactory() {}

    public static List<ObjectSerializer> createAll() {
        return List.of(
                new JsonJacksonSerializer(),
                new JavaIoSerializer(),
                new KryoSerializer(),
                new XmlSerializer());
    }
}
