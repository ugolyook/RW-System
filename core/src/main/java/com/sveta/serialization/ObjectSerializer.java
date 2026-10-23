package com.sveta.serialization;

public interface ObjectSerializer {
    <T extends SuperSerializationDto> void save(T object, String filePath) throws Exception;
    <T extends SuperSerializationDto> T load(String filePath, Class<T> clazz) throws Exception;
}