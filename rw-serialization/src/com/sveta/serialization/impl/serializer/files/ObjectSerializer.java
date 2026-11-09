package com.sveta.serialization.impl.serializer.files;

import com.sveta.serialization.impl.serializer.core.SuperSerializationDto;

public interface ObjectSerializer {
    SerializerType getSerializerName();

    <T extends SuperSerializationDto> void save(T object, String filePath) throws Exception;
    <T extends SuperSerializationDto> T load(String filePath, Class<T> clazz) throws Exception;
}   