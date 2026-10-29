package com.sveta.serialization.impl.test;


public interface ObjectSerializer {
    SerializerType getSerializerType();

    <T extends SuperSerializationDto> void save(T object, String filePath) throws Exception;
    <T extends SuperSerializationDto> T load(String filePath, Class<T> clazz) throws Exception;
}   